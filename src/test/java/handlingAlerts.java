import com.microsoft.playwright.*;

public class handlingAlerts {
    public static void main(String[] args) {
        Playwright playwright = Playwright.create();
        BrowserType browserType = playwright.chromium();

        Page page = browserType.launch(new BrowserType.LaunchOptions().setHeadless(false)).newContext(
                new Browser.NewContextOptions()
                        .setHttpCredentials("admin", "admin")
        ).newPage();
        page.navigate("https://www.lambdatest.com/selenium-playground/javascript-alert-box-demo");
        Locator buttons = page.locator("text='Click Me'");
        page.onceDialog(alert->  {
            String message = alert.message();
            System.out.println(message);
            alert.accept();
        });
        buttons.first().click();
        System.out.println(page.locator("#confirm-demo").textContent());

        page.onceDialog(alert->  {
            String message = alert.message();
            System.out.println(message);
            alert.dismiss();
        });
        buttons.nth(1).click();
        System.out.println(page.locator("#confirm-demo").textContent());

        page.onceDialog(alert->  {
            String message = alert.message();
            System.out.println(message);
            System.out.println(alert.defaultValue());
            alert.accept("Driuzzo");
        });
        buttons.last().click();

        System.out.println(page.locator("#prompt-demo").textContent());


        page.waitForTimeout(3000);

        page.navigate("https://the-internet.herokuapp.com/basic_auth");

        System.out.println(page.locator("h3").textContent());

        playwright.close();
    }
}
