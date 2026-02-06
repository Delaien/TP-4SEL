package com.saucedemo.tests;

import com.saucedemo.pages.CartPage;
import com.saucedemo.pages.CheckoutOverviewPage;
import com.saucedemo.pages.CheckoutPage;
import com.saucedemo.pages.InventoryPage;
import com.saucedemo.pages.LoginPage;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

@Feature("Checkout process")
public class CheckoutTest extends BaseTest {

    private InventoryPage loginAsStandardUser() {
        LoginPage loginPage = new LoginPage(driver).open();
        return loginPage.loginExpectSuccess("standard_user", "secret_sauce");
    }

    @Test
    @Story("Valid checkout information")
    @Severity(SeverityLevel.NORMAL)
    @Description("Checkout should continue with valid information")
    void testCheckoutWithValidInformation() {
        InventoryPage inventoryPage = loginAsStandardUser();
        inventoryPage.addBackpackToCart();
        CartPage cartPage = inventoryPage.clickCartIcon();
        CheckoutPage checkoutPage = cartPage.clickCheckout();
        checkoutPage.fillInformation("John", "Doe", "12345");
        CheckoutOverviewPage overviewPage = checkoutPage.clickContinue();
        assertTrue(overviewPage.isDisplayed(), "Overview page should be displayed");
    }

    @Test
    @Story("Missing first name")
    @Severity(SeverityLevel.NORMAL)
    @Description("Checkout should show error when first name is missing")
    void testCheckoutWithMissingFirstName() {
        InventoryPage inventoryPage = loginAsStandardUser();
        inventoryPage.addBackpackToCart();
        CartPage cartPage = inventoryPage.clickCartIcon();
        CheckoutPage checkoutPage = cartPage.clickCheckout();
        checkoutPage.fillInformation("", "Doe", "12345");
        checkoutPage.clickContinue();
        assertTrue(checkoutPage.getErrorMessage().length() > 0, "Error message should be displayed");
    }
}
