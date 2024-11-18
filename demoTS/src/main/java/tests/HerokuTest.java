package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pages.HerokuDropdownPage;
import pages.HerokuPage;
import pages.HerokuCheckBoxPage;


public class HerokuTest {
    private WebDriver driver;
    private HerokuPage herokuPage;
    private HerokuCheckBoxPage herokuCheckBoxPage;
    private HerokuDropdownPage herokuDropdownPage;

    @BeforeClass
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        herokuPage = new HerokuPage(driver);
        herokuCheckBoxPage = new HerokuCheckBoxPage(driver);
        herokuDropdownPage = new HerokuDropdownPage(driver);

    }

    @Test
    public void openHeroku() throws InterruptedException {
        herokuPage.abrirHeroku();
        Thread.sleep(2000);
        herokuPage.clickCheckBoxes();
        Thread.sleep(5000);
        herokuCheckBoxPage.clickCheckBox1();
        Thread.sleep(2000);
        herokuCheckBoxPage.clickCheckBox2();
        Thread.sleep(2000);
        driver.navigate().back();
        Thread.sleep(2000);
        herokuPage.clickDropdown();
        Thread.sleep(2000);
        herokuDropdownPage.clickDropdown();
        Thread.sleep(2000);
        herokuDropdownPage.clickOpcion1();
        Thread.sleep(2000);
        herokuDropdownPage.clickDropdown();
        Thread.sleep(2000);
        herokuDropdownPage.clickOpcion2();
        Thread.sleep(2000);
        driver.navigate().back();

    }

    @AfterClass
    public void tearDown() {
        // Cierra el navegador
        if (driver != null) {
            driver.quit();
        }
    }
}
