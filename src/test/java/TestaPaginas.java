import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.v129.page.model.WebAppManifest;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TestaPaginas {

    private WebDriver driver;
    private static final String baseURL = "https://automacao.testerglobal.com/";
    Home homePage;



    @Test
    @DisplayName("Ao Clicar no ícone de pesquisa a busca é feita sem preencher o campo de pesquisa")
    public void pesquisarCampoVazio () {

        // Entrar na página de testes
        driver.get(baseURL);

        homePage = new Home(driver);

        // Não inseriu nada no campo de pesquisa
        homePage.naoInsereNadaNaBarraDePesquisa();

        // Clicar no botão pesquisa
        homePage.clicaBotaoPesquisar();


    }

    @Test
    @DisplayName("Digita camera na barra de pesquisa de produto e depois clica em pesquisar. Abre a pagina do produto com os detalhes do produto.")
    public void pesquisarProduto () {
        // Entrar na página de testes
        driver.get(baseURL);
        homePage = new Home(driver);

        //Insere o nome do produto que eu desejo, camera
        homePage.insereNomeCampoPesquisa("Camera");

        //Clica no botão pesquisar
        homePage.clicaBotaoPesquisar();

        String tituloPaginaEsperada = "https://automacao.testerglobal.com/product/camera/";
        String paginaEncontrada = driver.getCurrentUrl();

        assertEquals (tituloPaginaEsperada, paginaEncontrada);
    }

    // metodo de preparação, roda tudo que for antes do teste.
    @BeforeAll
    public void setup(){

        // instanciando o chromedriver
        driver = new ChromeDriver();
        // definindo um tempo de espera
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(10));

    }

    // metodo para limpeza, roda tudo o que for depois do teste.
    @AfterAll
    public void tearDown () {
        driver.close();

    }









}
