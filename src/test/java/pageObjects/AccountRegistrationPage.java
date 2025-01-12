package pageObjects;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AccountRegistrationPage extends BasePage{
    //Constructor
    public AccountRegistrationPage(WebDriver driver){
        super(driver);
    }

    //Locators
    @FindBy(id="input-firstname")
    WebElement txtFirstname;

    @FindBy(id="input-lastname")
    WebElement txtLastname;

    @FindBy(xpath = "//input[@id='input-email']")
    WebElement txtEmail;

    @FindBy(id="input-telephone")
    WebElement txtTelephone;

    @FindBy(id="input-password")
    WebElement txtPassword;

    @FindBy(id="input-confirm")
    WebElement txtConfirmPassword;

    @FindBy(xpath = "//input[@type='checkbox']")
    WebElement chkPolicy;

    @FindBy(xpath = "//input[@value='Continue']")
    WebElement btnContinue;

    @FindBy(xpath = "//h1[text()='Your Account Has Been Created!']")
    WebElement msgConfirmation;

    //Action Method
    public void setTxtFirstname(String fname){
        txtFirstname.sendKeys(fname);
    }

    public void setTxtLastname(String lname){
        txtLastname.sendKeys(lname);
    }

    public void setTxtEmail(String email){
        txtEmail.sendKeys(email);
    }

    public void setTxtTelephone(String tel){
        txtTelephone.sendKeys(tel);
    }

    public void setTxtPassword(String pwd){
        txtPassword.sendKeys(pwd);
    }

    public void setTxtConfirmPassword(String conPass){
        txtConfirmPassword.sendKeys(conPass);
    }

    public void setChkPolicy(){
        chkPolicy.click();
    }

    public void clickContinue(){
        //Approach 1:
        btnContinue.click();

        //Approach 2:
        //btnContinue.submit();

        //Approach 3:
        //Actions act = new Actions(driver);
        //act.moveToElement(btnContinue).click().perform();

        //Approach 4:
        //JavascriptExecutor js = (JavascriptExecutor) driver;

        //Approach 5:
        //btnContinue.sendKeys(Keys.RETURN);

        //Approah 6:
        //WebDriverWait myWait = new WebDriverWait(driver, Duration.ofSeconds(10));
        //myWait.until(ExpectedConditions.elementToBeClickable(btnContinue)).click();
    }

    public String getConfirmationMsg(){
        try{
            return (msgConfirmation.getText());
        }catch(Exception e){
            return (e.getMessage());
        }
    }


}
