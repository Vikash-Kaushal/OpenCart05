package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

public class TC_003_LoginDataDrivenTest extends BaseClass {
    @Test(dataProvider = "LoginData", dataProviderClass = DataProviders.class)
    public void verify_LoginDataDrivenTest(String email, String password, String expResult){
        logger.info("***** Start LoginDataDrivenTest ******* ");

        try {
            //HomePage
            HomePage hp = new HomePage(driver);
            hp.clickMyAccount();
            hp.clickLogin();

            //LoginPage
            LoginPage lp = new LoginPage(driver);
            lp.setLnkEmail(email);
            lp.setLnkPassword(password);
            lp.clickLogin();

            //MyAccountPage
            MyAccountPage macco = new MyAccountPage(driver);
            boolean targetPage = macco.isMyAccountPage();

        /*
		 Data is Valid : Login Success - Test pass = logout
		                 Login failed - Test Fail

		 Data is Invalid : Login Success - Test fail  = logout
		                   Login failed - Test Pass
		 */
            if (expResult.equalsIgnoreCase("Valid")) {
                if (targetPage == true) {
                    macco.clickLogoutbtn();
                    Assert.assertTrue(true);
                } else {
                    Assert.assertTrue(false);
                }
            } else if (expResult.equalsIgnoreCase("Invalid")) {
                if (targetPage == true) {
                    macco.clickLogoutbtn();
                    Assert.assertTrue(false);
                } else {
                    Assert.assertTrue(true);
                }
            }
        }catch(Exception e){
            Assert.fail();
        }
        logger.info("***** Finished  LoginDataDrivenTest ******* ");
    }
}
