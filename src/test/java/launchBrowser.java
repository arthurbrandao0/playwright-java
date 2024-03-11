import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;

public class launchBrowser {

    public static void main(String[] args) {
        Playwright playwright = Playwright.create();
        Browser browser = playwright.firefox().launch(
                new BrowserType.LaunchOptions().setHeadless(false)
        );

        Page page = browser.newPage();

        page.navigate("https://ecommerce-playground.lambdatest.io/");
        Locator myAccount = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("My account"));
        myAccount.hover();
        Locator login = page.locator("css=span.title:has-text(\"Login\")");
        login.click();
        assertThat(page).hasTitle("Account Login");
        page.getByLabel("E-Mail Address").fill("adriano.driuzzo@hotmail.com");
        page.locator("#input-password").fill("123456");
        page.locator("input[value='Login']").click();
        assertThat(page).hasTitle("My Account");
        page.close();
        playwright.close();
    }
}
