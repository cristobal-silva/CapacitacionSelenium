package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.*;
import pages.GooglePage;
import utils.ReportGenerator;

public class GoogleTest {
    private WebDriver driver;
    private GooglePage googlePage;
    private ReportGenerator report;

    @BeforeClass
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver();
        googlePage = new GooglePage(driver);

        // Usar un solo archivo de reporte
        report = new ReportGenerator("Reporte_Unificado.html");
    }

    @Test
    @Parameters("buscarG")
    public void openGoogleTest(@Optional String buscarG) throws InterruptedException {
        report.logTest("Google Test", "Abrir Google y buscar algo");

        googlePage.openGoogle();
        report.captureScreenshot(driver, "Google Home");

        googlePage.escribirGoogle(buscarG);
        report.captureScreenshot(driver, "Google Search");
    }

    @AfterClass
    public void tearDown() {
        report.flushReport(); // Asegúrate de llamar a este método
        if (driver != null) {
            driver.quit();
        }
    }

}
