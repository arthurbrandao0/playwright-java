import com.microsoft.playwright.*;
import com.microsoft.playwright.Page.ScreenshotOptions;

public class screenshots {
    public static void main(String[] args) {
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch();
        Page page = browser.newPage();

        page.navigate("https://www.lambdatest.com/selenium-playground/simple-form-demo");

        ScreenshotOptions screenshotOptions = new ScreenshotOptions();

        page.screenshot();

    }
}
