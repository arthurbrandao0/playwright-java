import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import com.microsoft.playwright.*;
import com.microsoft.playwright.options.*;
import com.microsoft.playwright.Browser.*;

import java.nio.file.Paths;
import java.util.*;

public class codeGen {
    public static void main(String[] args) {
        Playwright playwright = Playwright.create();
            Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions()
                    .setHeadless(false));
            BrowserContext context = browser.newContext(
                    new NewContextOptions().setRecordVideoDir(Paths.get("videos")).setRecordVideoSize(1280, 720)
            );
            Page page = context.newPage();
            page.navigate("https://ecommerce-playground.lambdatest.io/index.php");
            Locator myAccount = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("My account"));

            myAccount.click();
            page.getByPlaceholder("E-Mail Address").click();
            page.getByPlaceholder("E-Mail Address").fill("adriano.driuzzo@hotmail.com");
            page.getByPlaceholder("E-Mail Address").press("Tab");
            page.getByPlaceholder("Password").fill("123456");
            page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Login")).click();
            page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Edit your account")).click();
            page.getByPlaceholder("Last Name").click();
            page.getByPlaceholder("Last Name").fill("Driuzzoo");
            page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Continue")).click();
            Locator successMessage = page.getByText("Success: Your account has been successfully updated.");
            assertThat(successMessage).isVisible();

            myAccount.hover();
            page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Logout").setExact(true)).click();
            Locator logoutHeader = page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName("Account Logout"));
            assertThat(logoutHeader).isVisible();

            page.close();
            browser.close();
            playwright.close();
    }
}
