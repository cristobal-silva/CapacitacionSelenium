package pages;

import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.Collection;

import static org.apache.commons.io.file.PathUtils.waitFor;
import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;

public class GooglePage {
    private WebDriver driver;

    public GooglePage(WebDriver driver) {

        this.driver = driver;
        PageFactory.initElements(driver, this);

    }

    public void openGoogle() {
        driver.get("https://www.google.com");
    }


    @FindBy(xpath = "//*[@id=\"APjFqb\"]")
    private WebElement buscar;
    private static final Duration DEFAULT_WAIT_FOR_ELEMENT_TIMEOUT = Duration.ofSeconds(15);

    private WebElement waitFor(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, DEFAULT_WAIT_FOR_ELEMENT_TIMEOUT);
        wait.ignoring(StaleElementReferenceException.class);
        return wait.until(elementToBeClickable(element));
    }

    public void escribirGoogle(String buscarG) {

        waitFor(buscar).clear();
        buscar.sendKeys(buscarG);
        Assert.assertEquals(buscar.getAttribute("value"), buscarG, "El texto en el campo de búsqueda no es correcto.");

    }
}

