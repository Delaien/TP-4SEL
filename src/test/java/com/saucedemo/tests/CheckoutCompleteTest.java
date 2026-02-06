package com.saucedemo.tests;

import com.saucedemo.pages.CartPage;
import com.saucedemo.pages.CheckoutCompletePage;
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

@Feature("Order completion")
public class CheckoutCompleteTest extends BaseTest {

    @Test
    @Story("Complete order flow")
    @Severity(SeverityLevel.CRITICAL)
    @Description("End-to-end order flow should complete and show confirmation")
    void testCompleteOrderFlow() {
        LoginPage loginPage = new LoginPage(driver).open();
        InventoryPage inventoryPage = loginPage.loginExpectSuccess("standard_user", "secret_sauce");
        inventoryPage.addBackpackToCart();
        CartPage cartPage = inventoryPage.clickCartIcon();
        CheckoutPage checkoutPage = cartPage.clickCheckout();
        checkoutPage.fillInformation("Jane", "Doe", "54321");
        CheckoutOverviewPage overviewPage = checkoutPage.clickContinue();
        CheckoutCompletePage completePage = overviewPage.clickFinish();
        assertTrue(completePage.getConfirmationMessage().toLowerCase().contains("thank you"));
    }
}
