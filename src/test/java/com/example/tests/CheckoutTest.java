package com.example.tests;

import com.example.pages.CartPage;
import com.example.pages.CheckoutPage;
import com.example.pages.LoginPage;
import com.example.pages.ProductsPage;
import com.microsoft.playwright.*;
import org.testng.annotations.*;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class CheckoutTest {

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
    public void checkoutHappyPath() {

        LoginPage loginPage = new LoginPage(page);
        ProductsPage productsPage = new ProductsPage(page);
        CartPage cartPage = new CartPage(page);
        CheckoutPage checkoutPage = new CheckoutPage(page);

        // 1. Open application
        loginPage.open();

        // 2. Login
        loginPage.login("standard_user", "secret_sauce");

        assertThat(page)
                .hasURL("https://www.saucedemo.com/inventory.html");

        // 3. Add product to cart
        productsPage.addProductToCart("Sauce Labs Backpack");

        // 4. Open cart
        cartPage.open();

        assertThat(page)
                .hasURL("https://www.saucedemo.com/cart.html");

        // 5. Verify product
        assertThat(cartPage.product("Sauce Labs Backpack"))
                .isVisible();

        // 6. Checkout
        cartPage.checkout();

        // 7. Customer information
        checkoutPage.fillCustomerInformation(
                "User",
                "Test",
                "46001"
        );

        checkoutPage.continueToOverview();

        // 8. Complete order
        checkoutPage.finish();

        // 9. Verify success
        assertThat(checkoutPage.successMessage())
                .hasText("Thank you for your order!");
    }

    @AfterMethod
    public void tearDown() {
        browser.close();
        playwright.close();
    }
}