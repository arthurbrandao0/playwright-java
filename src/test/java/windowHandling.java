import com.microsoft.playwright.*;
import com.microsoft.playwright.BrowserType.LaunchOptions;

public class windowHandling {
    public static void main(String[] args) {
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(
                new LaunchOptions().setHeadless(false)
        );
        Page page = browser.newPage();
        page.navigate("https://www.lambdatest.com/selenium-playground/window-popup-modal-demo");

        Page popup = page.waitForPopup(() -> {
            page.getByText("Follow On Twitter ").click();
        });
        popup.waitForLoadState();
        System.out.println(popup.title());

    }
}
