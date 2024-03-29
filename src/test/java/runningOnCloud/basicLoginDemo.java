package runningOnCloud;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import com.google.gson.JsonObject;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class basicLoginDemo {

    public static void main(String[] args) throws UnsupportedEncodingException {
        JsonObject capabilities = new JsonObject();
        JsonObject ltOptions = new JsonObject();

        String user = "adriano.driuzzo";
        String accessKey = "lR1WfpFWXPhtV5Fo5BhLkeb3BMXloQqsvO3KeQgVJy2LcpWbGy";

        capabilities.addProperty("browsername", "Chrome"); // Browsers allowed: `Chrome`, `MicrosoftEdge`,
        // `pw-chromium`, `pw-firefox` and `pw-webkit`
        capabilities.addProperty("browserVersion", "latest");
        ltOptions.addProperty("platform", "Windows 10");
        ltOptions.addProperty("name", "Playwright Test");
        ltOptions.addProperty("build", "Playwright Java Build 2");
        ltOptions.addProperty("user", user);
        ltOptions.addProperty("accessKey", accessKey);
        capabilities.add("LT:Options", ltOptions);

        // Playwright test
        Playwright playwright = Playwright.create();
        BrowserType chromium = playwright.chromium();
        String caps = URLEncoder.encode(capabilities.toString(), "utf-8");
        String cdpUrl = "wss://cdp.lambdatest.com/playwright?capabilities=" + caps;
        Browser browser = chromium.connect(cdpUrl);
        Page page = browser.newPage();
        try {
            page.navigate("https://www.duckduckgo.com");
            Locator locator = page.locator("[name=\"q\"]");
            locator.click();
            locator.fill("LambdaTest");
            page.keyboard().press("Enter");
            String title = page.title();

            if (title.equals("Go at DuckDuckGo")) {
                // Use the following code to mark the test status.
                setTestStatus("passed", "Title matched", page);
            } else {
                setTestStatus("failed", "Title not matched", page);
            }

        } catch (Exception err) {
            setTestStatus("failed", err.getMessage(), page);
            err.printStackTrace();
        }
        browser.close();
        playwright.close();
    }

    public static void setTestStatus(String status, String remark, Page page) {
        page.evaluate("_ => {}",
                "lambdatest_action: { \"action\": \"setTestStatus\", \"arguments\": { \"status\": \"" + status
                        + "\", \"remark\": \"" + remark + "\"}}");
    }
}