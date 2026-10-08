package com.example.tests;

import com.microsoft.playwright.*;
import org.testng.annotations.*;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class SuccessfulLoginTest {

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
    public void standardUserCanLogin() {
        com.example.pages.LoginPage loginPage = new com.example.pages.LoginPage(page);

        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");

        assertThat(page)
                .hasURL("https://www.saucedemo.com/inventory.html");
        assertThat(page.locator("[data-test=\"title\"]"))
                .hasText("Products");
    }

    @AfterMethod
    public void tearDown() {
        browser.close();
        playwright.close();
    }
}
