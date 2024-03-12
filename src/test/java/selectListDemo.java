import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import com.microsoft.playwright.*;
import com.microsoft.playwright.options.SelectOption;
import java.util.List;

public class selectListDemo {
    public static void main(String[] args) {
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(
                new BrowserType.LaunchOptions().setHeadless(false)
        );
        Page page = browser.newPage();
        page.navigate("https://www.lambdatest.com/selenium-playground/select-dropdown-demo");

        Locator dayLocator = page.locator("select#select-demo");

        String day = "Friday";

        //select by option
        dayLocator.selectOption("Sunday");

        //select by value
        dayLocator.selectOption(new SelectOption().setValue(day));
        assertThat(page.locator(".selected-value")).containsText(day);

        //select by index
        dayLocator.selectOption((new SelectOption().setIndex(2)));

        Locator states = page.locator("#multi-select");
        states.selectOption(new String[]{"New Jersey", "Texas"});
        Locator options = states.locator("option");
        System.out.println(options.count());
        List<String> allInnerTexts = options.allInnerTexts();
        allInnerTexts.forEach(option -> System.out.println(option));

        page.navigate("https://www.lambdatest.com/selenium-playground/jquery-dropdown-search-demo");
        Locator country = page.locator(".select2-selection.select2-selection--single").first();
        country.click();

        Locator list = page.locator(".select2-results ul li",
                new Page.LocatorOptions().setHasText("India"));
        list.click();

        Locator files = page.locator("select[name='files']");
        files.selectOption("Ruby");
    }
}
