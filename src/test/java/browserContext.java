import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;
import config.Config;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class browserContext {
    public static void main(String[] args) {
        String username = Config.getUsername();
        String password = Config.getPassword();
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));

        //cria um novo contexto
        BrowserContext context = browser.newContext();
        //cria uma aba usando o contexto criado acima
        Page page = context.newPage();
        page.navigate("https://ecommerce-playground.lambdatest.io/index.php?route=account/login");
        page.getByLabel("E-Mail Address").fill(username);
        page.getByLabel("Password").fill(password);
        page.getByRole(AriaRole.BUTTON,
                new Page.GetByRoleOptions().setName("Login")).click();
        Locator myAccount = page.getByText("Edit your account information");
        assertThat(myAccount).isVisible();

        //cria uma nova aba usando o contexto existente
        Page newTab = page.context().newPage();
        newTab.navigate("https://ecommerce-playground.lambdatest.io/index.php?route=account/account");
        assertThat(myAccount).isVisible();
        newTab.close();
        context.close();

        //cria um novo contexto
        BrowserContext context2 = browser.newContext();
        //cria uma nova janela usando um novo contexto
        Page userPage = context2.newPage();
        userPage.navigate("https://ecommerce-playground.lambdatest.io/index.php?route=account/account");

        BrowserType firefox = playwright.firefox();
        Page fp = firefox.launch(new BrowserType.LaunchOptions().setHeadless(false)).newPage();

        playwright.close();
    }
}
