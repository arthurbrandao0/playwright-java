import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import com.microsoft.playwright.*;

public class linkToDownload {
    public static void main(String[] args) {
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(
                new BrowserType.LaunchOptions().setHeadless(false)
        );
        Page page = browser.newPage();
        page.navigate("https://www.lambdatest.com/selenium-playground/generate-file-to-download-demo");
        page.locator("#textbox").fill("This is a playwright testing using Java!");
        page.locator("#textbox").press("Enter");
        page.locator("#create").click();
        assertThat(page.locator("#link-to-download")).hasText("Download");

        page.close();
        playwright.close();
    }
}
