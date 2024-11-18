package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class HerokuPage {
    private WebDriver driver;

    public HerokuPage (WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath ="//a[contains(@href, 'check')]")
    private WebElement checkBoxes;

    @FindBy(xpath ="//a[contains(@href, 'dropdo')]")
    private WebElement dropdown;


    /*METODOS*/
    public void abrirHeroku(){
        driver.get("https://the-internet.herokuapp.com/");
    }

    public void clickCheckBoxes(){
        checkBoxes.click();
    }

    public void clickDropdown(){
        dropdown.click();
    }


}
