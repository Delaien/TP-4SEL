package com.saucedemo.pages;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class InventoryPage extends BasePage {
    private final By pageTitle = By.className("title");
    private final By addBackpackButton = By.id("add-to-cart-sauce-labs-backpack");
    private final By cartBadge = By.className("shopping_cart_badge");
    private final By cartIcon = By.className("shopping_cart_link");

    public InventoryPage(WebDriver driver) {
        super(driver);
    }

    public boolean isDisplayed() {
        return isDisplayed(pageTitle);
    }

    public String getPageTitle() {
        return getText(pageTitle);
    }

    public InventoryPage addBackpackToCart() {
        click(addBackpackButton);
        new WebDriverWait(driver, java.time.Duration.ofSeconds(10))
                .until(ExpectedConditions.textToBePresentInElementLocated(cartBadge, "1"));
        return this;
    }

    public int getCartItemCount() {
        try {
            WebElement badge = waitUntilVisible(cartBadge);
            return Integer.parseInt(badge.getText().trim());
        } catch (TimeoutException e) {
            List<WebElement> badges = driver.findElements(cartBadge);
            if (badges.isEmpty()) {
                return 0;
            }
            return Integer.parseInt(badges.get(0).getText().trim());
        }
    }

    public CartPage clickCartIcon() {
        click(cartIcon);
        return new CartPage(driver);
    }
}
