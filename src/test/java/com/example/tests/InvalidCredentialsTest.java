package com.example.tests;

import com.microsoft.playwright.*;
import org.testng.annotations.*;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class InvalidCredentialsTest {

    private Playwright playwright;
    private Browser browser;
    private Page page;

    @BeforeMethod
    public void setUp() {
        playwright = Playwright.create();

        browser = playwright.chromium().launch(
                new BrowserType.LaunchOptions()
                        .setHeadless(false)
        );

        page = browser.newPage();
    }

    @Test
    public void lockedOutUserCannotLogin() {
        com.example.pages.LoginPage loginPage = new com.example.pages.LoginPage(page);

        loginPage.open();
        loginPage.login("locked_out_user", "123");

        assertThat(page.locator("[data-test=\"error\"]"))
                .hasText("Epic sadface: Username and password do not match any user in this service");
        assertThat(page)
                .hasURL("https://www.saucedemo.com/");
    }

    @AfterMethod
    public void tearDown() {
        browser.close();
        playwright.close();
    }
}
