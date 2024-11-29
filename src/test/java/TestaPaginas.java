import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.v129.page.model.WebAppManifest;

import java.time.Duration;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TestaPaginas {

    private WebDriver driver;
    private static final String baseURL = "https://automacao.testerglobal.com/";



    @Test
    @DisplayName("Ao Clicar no ícone de pesquisa a busca é feita sem preencher o campo de pesquisa")
    public void pesquisarCampoVazio () {

        // Entrar na página de testes

        // Não inseriu nada no campo de pesquisa

        // Clicar no botão pesquisa


    }

    //Método para preparação, tudo o que irá rodar antes dos testes.
    @BeforeAll
    public void setup(){
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(10));

    }

    //Método para limpeza, tudo que irá rodar depois dos testes.
    @AfterAll
    public void tearDown () {
        driver.close();

    }









}
