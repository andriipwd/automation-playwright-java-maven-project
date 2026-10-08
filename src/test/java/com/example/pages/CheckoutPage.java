package com.example.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class CheckoutPage {

    private final Page page;

    public CheckoutPage(Page page) {
        this.page = page;
    }

    public void fillCustomerInformation(
            String firstName,
            String lastName,
            String postalCode) {

        page.locator("[data-test=\"firstName\"]").fill(firstName);
        page.locator("[data-test=\"lastName\"]").fill(lastName);
        page.locator("[data-test=\"postalCode\"]").fill(postalCode);
    }

    public void continueToOverview() {
        page.locator("[data-test=\"continue\"]").click();
    }

    public void finish() {
        page.locator("[data-test=\"finish\"]").click();
    }

    public Locator successMessage() {
        return page.locator("[data-test=\"complete-header\"]");
    }
}