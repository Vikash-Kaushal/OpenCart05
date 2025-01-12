package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;


public class TC_001_AccountRegistrationTest extends BaseClass {

    @Test(groups = {"Regression", "Master"})
    public void verify_account_registration(){
        logger.info("****** Starting TC_001_AccountRegistrationTest ****** ");
        try {

            HomePage hp = new HomePage(driver);
            hp.clickMyAccount();
            logger.info("Clicked on MyAccount Link ");
            hp.clickRegister();
            logger.info("Clicked on Register Link ");

            AccountRegistrationPage regpage = new AccountRegistrationPage(driver);
            logger.info("Providing Customer Details... ");

            //regpage.setTxtFirstname("Mahek");
            regpage.setTxtFirstname(randomString().toUpperCase());// I am pass the firstname is dynamically

            //regpage.setTxtLastname("Dilawar");
            regpage.setTxtLastname(randomString().toUpperCase()); // I am pass the lastname is dynamically

            regpage.setTxtEmail(randomString() + "@gmail.com"); //randomly generated the email

            //regpage.setTxtTelephone("2323232323");
            regpage.setTxtTelephone(randomNumber());// I am pass the telephone is dynamically

            //regpage.setTxtPassword("xyz123456");
            String password = randomAlphaNumeric();
            regpage.setTxtPassword(password); // I am pass the password is dynamically

            //regpage.setTxtConfirmPassword("xyz123456");
            regpage.setTxtConfirmPassword(password);//I am pass conPass dynamically

            regpage.setChkPolicy();
            regpage.clickContinue();

            logger.info("Validating expected Message... ");
            String confmsg = regpage.getConfirmationMsg();
            if(confmsg.equals("Your Account Has Been Created!")){
                Assert.assertTrue(true);
            }else{
                logger.error("Test Failed... ");
                logger.debug("Debugs logs... ");
                Assert.assertTrue(false);
            }

            //Validation
            //Assert.assertEquals(confmsg, "Your Account Has Been Created!");
            //Your Account Has Been Created!
        }catch(Exception e){
            Assert.fail();
        }
        finally {
            System.out.println("Program successful run");
        }
        logger.info("****** Finished TC_001_AccountRegistrationTest ******* ");

    }

}


