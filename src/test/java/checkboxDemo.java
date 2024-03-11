import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import com.microsoft.playwright.*;

public class checkboxDemo {
    public static void main(String[] args) {
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(
                new BrowserType.LaunchOptions().setHeadless(false)
        );
        Page page = browser.newPage();
        page.navigate("https://www.lambdatest.com/selenium-playground/checkbox-demo");

        Locator isAge = page.locator("#isAgeSelected");
        assertThat(isAge).not().isChecked();
        isAge.check();
        assertThat(isAge).isChecked();

        page.close();
        playwright.close();
    }
}
