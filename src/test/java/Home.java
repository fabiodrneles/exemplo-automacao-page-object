import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * Page Object da página inicial da loja: só a busca de produtos.
 * Seletores curtos e esperas explícitas (constituição, princípios 4 e 5).
 */
public class Home {

    private static final By CAMPO_PESQUISA = By.cssSelector(".header-search-widget form input.header-search-input");
    private static final By BOTAO_PESQUISA = By.cssSelector(".header-search-widget form button");

    private final WebDriver driver;
    private final WebDriverWait espera;

    public Home(WebDriver driver) {
        this.driver = driver;
        this.espera = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public Home abrir(String baseUrl) {
        driver.get(baseUrl);
        espera.until(ExpectedConditions.visibilityOfElementLocated(CAMPO_PESQUISA));
        return this;
    }

    // Insere o nome do produto que eu quero pesquisar
    public Home insereNomeCampoPesquisa(String nomeDoProdutoParaBuscar) {
        WebElement campo = espera.until(ExpectedConditions.elementToBeClickable(CAMPO_PESQUISA));
        campo.clear();
        campo.sendKeys(nomeDoProdutoParaBuscar);
        return this;
    }

    // Deixa o campo de pesquisa vazio antes de pesquisar
    public Home naoInsereNadaNaBarraDePesquisa() {
        espera.until(ExpectedConditions.elementToBeClickable(CAMPO_PESQUISA)).clear();
        return this;
    }

    // Clica no botão pesquisa e espera a navegação terminar
    public void clicaBotaoPesquisar() {
        String antes = driver.getCurrentUrl();
        espera.until(ExpectedConditions.elementToBeClickable(BOTAO_PESQUISA)).click();
        espera.until(d -> !d.getCurrentUrl().equals(antes));
    }
}
