package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC_002_LoginTest extends BaseClass {
    @Test(groups={"Sanity", "Master"})
    public void verify_Login(){
        logger.info("***** Starting TC_002_LoginTest *****");
        try {
            HomePage hp = new HomePage(driver);
            hp.clickMyAccount();
            hp.clickLogin();

            LoginPage lp = new LoginPage(driver);
            lp.setLnkEmail(p.getProperty("email"));
            lp.setLnkPassword(p.getProperty("password"));
            lp.clickLogin();

            MyAccountPage macco = new MyAccountPage(driver);
            boolean targetPage = macco.isMyAccountPage();
            Assert.assertTrue(targetPage);
        }
        catch(Exception e){
            Assert.fail();
        }
        logger.info("***** TC_002_Login is Finished ****** ");
    }
}
