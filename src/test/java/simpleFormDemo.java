import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import com.microsoft.playwright.*;

public class simpleFormDemo {
    public static void main(String[] args) {
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(
                new BrowserType.LaunchOptions().setHeadless(false)
        );
        Page page = browser.newPage();
        page.navigate("https://www.lambdatest.com/selenium-playground/simple-form-demo");
        page.locator("input#user-message").fill("Hey tester");
        page.locator("#showInput").click();
        String message = page.locator("#message").textContent();
        System.out.println(message);
        assertThat(page.locator("#message")).hasText("Hey tester");

        page.close();
        playwright.close();
    }
}
