package com.saucedemo.tests;

import com.saucedemo.pages.CartPage;
import com.saucedemo.pages.InventoryPage;
import com.saucedemo.pages.LoginPage;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Feature("Shopping cart")
public class CartTest extends BaseTest {

    private InventoryPage loginAsStandardUser() {
        LoginPage loginPage = new LoginPage(driver).open();
        return loginPage.login("standard_user", "secret_sauce");
    }

    @Test
    @Story("Empty cart")
    @Severity(SeverityLevel.NORMAL)
    @Description("Cart should be empty for a new session")
    void testEmptyCart() {
        InventoryPage inventoryPage = loginAsStandardUser();
        CartPage cartPage = inventoryPage.clickCartIcon();
        assertEquals(0, cartPage.getItemCount());
    }

    @Test
    @Story("Cart with one product")
    @Severity(SeverityLevel.NORMAL)
    @Description("Cart should contain one item after adding backpack")
    void testCartWithOneProduct() {
        InventoryPage inventoryPage = loginAsStandardUser();
        inventoryPage.addBackpackToCart();
        CartPage cartPage = inventoryPage.clickCartIcon();
        assertEquals(1, cartPage.getItemCount());
    }
}
