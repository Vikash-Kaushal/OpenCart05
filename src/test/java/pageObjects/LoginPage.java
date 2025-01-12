package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage{

    public LoginPage(WebDriver driver){
        super(driver);
    }

    @FindBy(xpath="//input[@id='input-email']")
    WebElement lnkEmail;

    @FindBy(xpath="//input[@id='input-password']")
    WebElement lnkPassword;

    @FindBy(xpath = "//input[@type='submit']")
    WebElement clickLogin;

    //Action Method

    public void setLnkEmail(String email) {
        lnkEmail.sendKeys(email);
    }

    public void setLnkPassword(String pass){
        lnkPassword.sendKeys(pass);
    }

    public void clickLogin(){
        clickLogin.click();
    }
}