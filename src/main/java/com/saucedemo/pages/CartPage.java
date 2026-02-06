package com.saucedemo.pages;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CartPage extends BasePage {
    private final By pageTitle = By.className("title");
    private final By cartItems = By.className("cart_item");
    private final By checkoutButton = By.id("checkout");
    private final By continueShoppingButton = By.id("continue-shopping");

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public boolean isDisplayed() {
        return isDisplayed(pageTitle);
    }

    public int getItemCount() {
        waitUntilVisible(pageTitle);
        List<WebElement> items = driver.findElements(cartItems);
        return items.size();
    }

    public CheckoutPage clickCheckout() {
        waitUntilVisible(pageTitle);
        click(checkoutButton);
        return new CheckoutPage(driver);
    }

    public InventoryPage clickContinueShopping() {
        click(continueShoppingButton);
        return new InventoryPage(driver);
    }
}
