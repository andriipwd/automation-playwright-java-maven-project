package com.example.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class CartPage {

    private final Page page;

    public CartPage(Page page) {
        this.page = page;
    }

    public void open() {
        page.locator("[data-test=\"shopping-cart-link\"]").click();
    }

    public Locator product(String productName) {
        return page.getByText(productName);
    }

    public void checkout() {
        page.locator("[data-test=\"checkout\"]").click();
    }
}