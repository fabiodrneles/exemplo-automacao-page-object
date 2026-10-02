import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/** Page Object da página de detalhes de um produto. */
public class PaginaProduto {

    private static final By TITULO = By.cssSelector("h1.product_title");

    private final WebDriverWait espera;

    public PaginaProduto(WebDriver driver) {
        this.espera = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public String titulo() {
        return espera.until(ExpectedConditions.visibilityOfElementLocated(TITULO)).getText().trim();
    }
}
