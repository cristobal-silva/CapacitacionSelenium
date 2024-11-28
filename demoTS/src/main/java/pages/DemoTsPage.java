package pages;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class DemoTsPage {
    private WebDriver driver;
    
    @FindBy(xpath = "//a[contains(@href,'key_presses')]")
    private WebElement keyP;

    @FindBy(id = "target")
    private WebElement target;


    public DemoTsPage (WebDriver driver){

        this.driver = driver;
        PageFactory.initElements(driver, this);

    }


    public void abrirGoogle(){
        driver.get("https://the-internet.herokuapp.com/");
    }


    public void clickKey(){

        keyP.click();

    }

    public void ingNumeros (String numeros){

        target.sendKeys(numeros);


    }

}
