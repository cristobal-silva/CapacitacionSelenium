package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HerokuCheckBoxPage {
    private WebDriver driver;
    public HerokuCheckBoxPage (WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    @FindBy(xpath ="//input[contains(@type, 'check')]")
    private WebElement checkBox1;

    @FindBy(xpath ="(//input[contains(@type, 'check')])[2]")
    private WebElement checkBox2;

    public void clickCheckBox1(){
        checkBox1.click();
    }

    public void clickCheckBox2(){
        checkBox2.click();
    }
}
