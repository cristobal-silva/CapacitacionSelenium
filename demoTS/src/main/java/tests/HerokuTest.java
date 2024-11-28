package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.*;
import pages.DemoTsPage;
import utils.ReportGenerator;

import java.util.ArrayList;

public class HerokuTest {
    private WebDriver driver;
    private static DemoTsPage demoTsPage;
    private ReportGenerator report;

    @BeforeClass
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver();
        demoTsPage = new DemoTsPage(driver);

        // Usar el mismo archivo de reporte
        report = new ReportGenerator("Reporte_Unificado.html");
    }

    @Test
    @Parameters("numeros")
    public void heroku(@Optional String numeros) throws InterruptedException {
        report.logTest("Heroku Test", "Abrir Heroku y realizar acciones");

        demoTsPage.abrirGoogle();
        report.captureScreenshot(driver, "Google Opened");

        ((JavascriptExecutor) driver).executeScript("window.open('https://the-internet.herokuapp.com/', '_blank');");
        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));
        demoTsPage.clickKey();
        report.captureScreenshot(driver, "Key Press Page");

        demoTsPage.ingNumeros(numeros);
        driver.switchTo().window(tabs.get(0));
    }

    @AfterClass
    public void tearDown() {
        report.flushReport(); // Asegúrate de llamar a este método
        if (driver != null) {
            driver.quit();
        }
    }

}
