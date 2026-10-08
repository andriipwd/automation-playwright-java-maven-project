package com.example.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class ProductsPage {

    private final Page page;

    public ProductsPage(Page page) {
        this.page = page;
    }

    public void addProductToCart(String productName) {
        Locator product = page.locator("[data-test=\"inventory-item\"]")
                .filter(new Locator.FilterOptions().setHasText(productName));

        product.getByRole(
                AriaRole.BUTTON,
                new Locator.GetByRoleOptions().setName("Add to cart")
        ).click();
    }
}