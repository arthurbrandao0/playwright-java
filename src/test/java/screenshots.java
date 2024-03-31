import com.microsoft.playwright.*;
import com.microsoft.playwright.Page.ScreenshotOptions;
import com.microsoft.playwright.options.ScreenshotCaret;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;

public class screenshots {
    public static void main(String[] args) {
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch();
        Page page = browser.newPage();

        page.navigate("https://www.lambdatest.com/selenium-playground/simple-form-demo");

        ScreenshotOptions screenshotOptions = new ScreenshotOptions();
        page.screenshot(screenshotOptions.setPath(Paths.get("./snaps/scr.png")));

        page.screenshot(screenshotOptions.setFullPage(true).setPath(Paths.get("./snaps/fullPage.jpg")));

        Locator bookBtn = page.locator("//button[text()='Book a Demo']");
        bookBtn.screenshot(new Locator.ScreenshotOptions().setPath(Paths.get("./snaps/locator.png")));

        Locator header = page.locator("#header");
        header.screenshot(new Locator.ScreenshotOptions().setPath(Paths.get("./snaps/region.jpg")));

        Locator input = page.locator("input#user-message");
        //input.fill("Something");
        input.scrollIntoViewIfNeeded();
//        page.screenshot(screenshotOptions.setPath(Paths.get("./snaps/input.png"))
//                .setFullPage(false)
//                .setMask(Arrays.asList(input))
//        );

        input.click();

        page.screenshot(new ScreenshotOptions().setCaret(ScreenshotCaret.HIDE).setPath(Paths.get("./snaps/caretHide.png")));


    }
}
