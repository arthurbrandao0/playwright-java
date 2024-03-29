import com.microsoft.playwright.*;
import com.microsoft.playwright.BrowserType.LaunchOptions;

import java.util.List;

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
        popup.getByText("Entrar").click();
        popup.close();

        Page tabs = page.waitForPopup(new Page.WaitForPopupOptions().setPredicate(
                p-> p.context().pages().size() == 3),() -> {
            page.getByText("Follow All").click();
        });

        List<Page> pages = tabs.context().pages();
        System.out.println(pages.size());

        pages.forEach((tab -> {
            tab.waitForLoadState();
            System.out.println(tab.title());
        }));

        Page fbpage = null;
        Page twpage = null;

        if(pages.get(0).title().endsWith("Twitter")){
            twpage = pages.get(1);
        }
        else {
            fbpage = pages.get(0);
        }
        System.out.println(fbpage.url());

        playwright.close();
    }
}
