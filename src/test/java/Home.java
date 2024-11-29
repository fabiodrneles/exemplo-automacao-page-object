import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Home {

    private WebDriver driver;

    // Campo de pesquisa
    By campoPesquisa = By.cssSelector("#blog > div.page-wrap > div.site-header.container-fluid.woo-heading > div > div > div.header-search-widget > div.head-form.hidden-xs > div > form > input.header-search-input");

    // Botão Pesquisa
    By botaoPesquisa = By.cssSelector("#blog > div.page-wrap > div.site-header.container-fluid.woo-heading > div > div > div.header-search-widget > div.head-form.hidden-xs > div > form > button");

    // Inserir o nome do produto que eu quero pesquisar
    public void insereNomeCampoPesquisa (String nomeDoProdutoParaBuscar) {
        driver.findElement(campoPesquisa).sendKeys(nomeDoProdutoParaBuscar);
    }


    // Clicar no botão pesquisa para realizar a ação
    public void clicaBotaoPesquisar () {
        driver.findElement(botaoPesquisa).click();
    }

    //Não inserir nada no campo de pesquisa
    public void naoInsereNadaNaBarraDePesquisa () {
        driver.findElement(campoPesquisa).sendKeys("");
    }





    public Home(WebDriver driver) {
        this.driver = driver;
    }
}
