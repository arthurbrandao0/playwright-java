package com.ltpages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class HeaderSection {
    private Page page = null;
    public HeaderSection(Page page) {
        this.page = page;
    }

    private Locator getMyAccount() {
        return this.page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("My account"));
    }

    public void clickLogin() {
        this.getMyAccount().hover();
        this.page.locator("span.title:has-text('Login')").click();
    }
    public void clickRegister() {
        this.getMyAccount().hover();
        this.page.locator("span.title:has-text('Register')").first().click();
    }
}
