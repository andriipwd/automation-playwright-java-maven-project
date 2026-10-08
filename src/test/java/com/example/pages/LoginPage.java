package com.example.pages;

import com.microsoft.playwright.Page;

public class LoginPage {
    
    private final Page page;

    public LoginPage(Page page) {
        this.page = page;
    }

    public void open() {
        page.navigate("https://www.saucedemo.com/");
    }

    public void login(String username, String password) {
        page.locator("[data-test=\"username\"]").fill(username);
        page.locator("[data-test=\"password\"]").fill(password);
        page.locator("[data-test=\"login-button\"]").click();
    }
}
