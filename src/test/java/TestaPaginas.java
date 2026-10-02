import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.net.URI;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.LinkedHashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Busca de produtos. Por padrão roda contra a fixture local; com
 * -DbaseUrl=https://automacao.testerglobal.com/ roda contra o site real
 * (spec 001 FR-2, AC-4).
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TestaPaginas {

    private WebDriver driver;
    private FixtureServer fixture;
    private String baseUrl;

    @BeforeAll
    public void setup() throws Exception {
        // 001 AC-4: -DbaseUrl escolhe o site real; sem ele, a fixture local.
        baseUrl = resolveBaseUrl(System.getProperty("baseUrl"), () -> {
            fixture = new FixtureServer();
            return fixture.baseUrl();
        });
        ChromeOptions options = new ChromeOptions();
        String binario = System.getProperty("chrome.binary", "");
        if (!binario.isBlank()) {
            options.setBinary(binario);
        }
        // 001 FR-1, 001 AC-1: headless no CI (sem display) ou com -Dheadless=true.
        if (System.getenv("CI") != null || Boolean.getBoolean("headless")) {
            options.addArguments("--headless=new", "--no-sandbox", "--disable-dev-shm-usage", "--window-size=1366,900");
        }
        driver = new ChromeDriver(options);
    }

    // 001 AC-2
    @Test
    @DisplayName("Ao clicar em pesquisar com o campo vazio, a busca é feita sem termo")
    public void pesquisarCampoVazio() {
        Home home = new Home(driver).abrir(baseUrl);
        home.naoInsereNadaNaBarraDePesquisa().clicaBotaoPesquisar();

        Map<String, String> query = query(driver.getCurrentUrl());
        assertEquals("product", query.get("post_type"));
        assertEquals("", query.get("s"));
    }

    // 001 AC-3
    @Test
    @DisplayName("Pesquisar \"Camera\" abre a página de detalhes do produto Camera")
    public void pesquisarProduto() {
        new Home(driver).abrir(baseUrl).insereNomeCampoPesquisa("Camera").clicaBotaoPesquisar();

        assertEquals(URI.create(baseUrl).resolve("/product/camera/").toString(), driver.getCurrentUrl());
        assertEquals("Camera", new PaginaProduto(driver).titulo());
    }

    // 001 FR-4: quit() encerra o navegador e o processo do driver.
    @AfterAll
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
        if (fixture != null) {
            fixture.close();
        }
    }

    interface BaseUrlSource {
        String get() throws Exception;
    }

    static String resolveBaseUrl(String property, BaseUrlSource fixture) throws Exception {
        return property == null || property.isBlank() ? fixture.get() : property;
    }

    private static Map<String, String> query(String url) {
        Map<String, String> params = new LinkedHashMap<>();
        String raw = URI.create(url).getRawQuery();
        if (raw == null) {
            return params;
        }
        for (String pair : raw.split("&")) {
            String[] kv = pair.split("=", 2);
            params.put(kv[0], kv.length > 1 ? URLDecoder.decode(kv[1], StandardCharsets.UTF_8) : "");
        }
        return params;
    }
}
