package tests;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.GooglePage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class GoogleTest {
    private WebDriver driver;
    private GooglePage googlePage;
    private ExtentReports extent;
    private ExtentTest test;

    @BeforeClass
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();

        // Inicialización de la página
        googlePage = new GooglePage(driver);

        // Configuración de ExtentReports
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter("reporte.html");
        sparkReporter.config().setDocumentTitle("Reporte de Pruebas");
        sparkReporter.config().setReportName("Reporte Google Test");
        sparkReporter.config().setTheme(Theme.STANDARD);

        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);

    }

    @Test
    public void openGoogleTest() throws InterruptedException {
        test = extent.createTest("Abrir Google y capturar pantalla");
        googlePage.openGoogle();
        Thread.sleep(5000);
        // Verificación (puedes agregar alguna verificación básica, como el título)
        Assert.assertTrue(driver.getTitle().contains("Google"));
        // Captura de pantalla
        String screenshotPath = takeScreenshot("GoogleHomePage");
        test.addScreenCaptureFromPath(screenshotPath, "Captura de la página de inicio de Google");

        googlePage.escribirGoogle("gatitos");
        String screenshotPath2 = takeScreenshot("busqueda");
        test.addScreenCaptureFromPath(screenshotPath2, "Captura de la página de inicio de Google y escribir");



    }


    public String takeScreenshot(String fileName) {
        TakesScreenshot ts = (TakesScreenshot) driver;
        File srcFile = ts.getScreenshotAs(OutputType.FILE);
        String destPath = System.getProperty("user.dir") + "/screenshots/" + fileName + ".png";
        try {
            Files.createDirectories(Paths.get("screenshots"));
            Files.copy(srcFile.toPath(), Paths.get(destPath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return destPath;
    }

    @AfterClass
    public void tearDown() {
//         Finaliza el reporte
        extent.flush();

        // Cierra el navegador
        if (driver != null) {
            driver.quit();
        }
    }
}
