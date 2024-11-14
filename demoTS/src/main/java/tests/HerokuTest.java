package tests;


import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.DemoTsPage;




public class HerokuTest {

    private WebDriver driver;
    private static DemoTsPage demoTsPage;
    @BeforeClass
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        demoTsPage = new DemoTsPage(driver);

    }


    @Test
    @Parameters("numeros")
    public void heroku(@Optional String numeros) throws InterruptedException{



        demoTsPage.abrirGoogle();
        demoTsPage.clickKey();
        demoTsPage.ingNumeros(numeros);
        Thread.sleep(3000);





    }

    @AfterClass
    public void tearDown() {

        // Cierra el navegador
        if (driver != null) {
            driver.quit();
        }
    }

}
