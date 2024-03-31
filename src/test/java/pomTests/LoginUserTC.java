package pomTests;

import base.Driver;
import base.PlaywrightConnection;
import com.ltpages.HeaderSection;
import com.ltpages.LoginPage;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.LoadState;
import config.Config;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginUserTC extends PlaywrightConnection {
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
    public void loginUser() {
        String username = Config.getUsername();
        String password = Config.getPassword();

        Page page = driver.getPage();
        HeaderSection header = new HeaderSection(page);
        LoginPage login = new LoginPage(page);
        try {
            page.navigate("https://ecommerce-playground.lambdatest.io/");
            page.waitForLoadState(LoadState.LOAD);
            header.clickLogin();
            login.loginAsuser(username, password);

            String title = page.title();

            if (title.equals("My Account")) {
                super.setTestStatus("passed", "Login success", page);
            } else {
                super.setTestStatus("failed", "Login failed", page);
            }

        } catch (Exception err) {
            super.setTestStatus("failed", err.getMessage(), page);
            err.printStackTrace();
        }
    }
}
