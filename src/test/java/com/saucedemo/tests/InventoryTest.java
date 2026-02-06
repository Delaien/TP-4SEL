package com.saucedemo.tests;

import com.saucedemo.pages.InventoryPage;
import com.saucedemo.pages.LoginPage;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Feature("Inventory management")
public class InventoryTest extends BaseTest {

    private InventoryPage loginAsStandardUser() {
        LoginPage loginPage = new LoginPage(driver).open();
        return loginPage.loginExpectSuccess("standard_user", "secret_sauce");
    }

    @Test
    @Story("Inventory display")
    @Severity(SeverityLevel.NORMAL)
    @Description("Inventory page should be visible after login")
    void testInventoryPageDisplayed() {
        InventoryPage inventoryPage = loginAsStandardUser();
        assertTrue(inventoryPage.isDisplayed(), "Inventory page should be displayed");
        assertEquals("Products", inventoryPage.getPageTitle());
    }

    @Test
    @Story("Add product to cart")
    @Severity(SeverityLevel.NORMAL)
    @Description("Adding backpack should show cart badge = 1")
    void testAddProductToCart() {
        InventoryPage inventoryPage = loginAsStandardUser();
        inventoryPage.addBackpackToCart();
        assertEquals(1, inventoryPage.getCartItemCount());
    }
}
