package pomTests;

import base.Driver;
import base.PlaywrightConnection;
import com.ltpages.HeaderSection;
import com.ltpages.RegisterAccountPage;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.LoadState;
import config.Config;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Date;

public class RegisterUserTC extends PlaywrightConnection {
    Driver driver;

    @BeforeMethod
        public void setUp() throws Exception {
        driver = super.createConnection();
    }
    @AfterMethod
    public void tearDown() {
        super.closeConnection(driver);
    }

    @Test
    public void registerUser() {
        Page page = driver.getPage();
        HeaderSection header = new HeaderSection(page);
        RegisterAccountPage register = new RegisterAccountPage(page);
        try {
            page.navigate("https://ecommerce-playground.lambdatest.io/");
            page.waitForLoadState(LoadState.LOAD);
            header.clickRegister();
            register.clickContinue();
            boolean warningVisible = register.isWarningVisible();
            if (warningVisible) {
                super.setTestStatus("passed", "Warning is visible", page);
            } else {
                super.setTestStatus("failed", "Warning is not visible", page);
            }
            int mandatoryMessageVisible = register.isMandatoryWarningMessageVisible();
            if(mandatoryMessageVisible > 0) {
                super.setTestStatus("passed", "All mandatory fields are visible", page);
            }
            String email = "driuzzo" + new Date().getTime() + "@mail.com";
            String password = Config.getPassword();

            register.registerUserAccount("Adriano", "Driuzzo", email,
                    "19999999999", password);

            String registerSuccess = register.isRegisterSuccess();

            if (registerSuccess.equals("Your Account Has Been Created!")) {
                super.setTestStatus("passed", "Register success", page);
            } else {
                super.setTestStatus("failed", "Register failed", page);
            }

        } catch (Exception err) {
            super.setTestStatus("failed", err.getMessage(), page);
            err.printStackTrace();
        }
    }
}
