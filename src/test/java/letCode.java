import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import com.microsoft.playwright.*;

public class letCode {

    public static void main(String[] args) {
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(
                new BrowserType.LaunchOptions().setHeadless(false)
        );
        Page page = browser.newPage();

        page.navigate("https://letcode.in/edit");

        Locator fullNamelocator = page.locator("#fullName");
        assertThat(fullNamelocator).hasAttribute("placeholder", "Enter first & last name");
        fullNamelocator.fill("Adriano Driuzzo");

        Locator join = page.locator("#join");
        String value = join.getAttribute("value");
        String novoTexto = " edited";
        join.evaluate("(element, novoTexto) => { element.value = element.value + novoTexto; }", novoTexto);

        String inputValue = page.locator("#getMe").inputValue();
        System.out.println(inputValue);

        page.locator("#clearMe").clear();

        Locator disabledElement = page.locator("#noEdit");
        assertThat(disabledElement).hasAttribute("disabled", "");

        Locator dontWrite = page.locator("#dontwrite");
        assertThat(dontWrite).hasAttribute("readonly", "");

        page.close();
        playwright.close();

    }
}
