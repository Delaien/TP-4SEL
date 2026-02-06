package com.saucedemo.pages;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CheckoutOverviewPage extends BasePage {
    private final By pageTitle = By.className("title");
    private final By cartItems = By.className("cart_item");
    private final By totalPrice = By.className("summary_total_label");
    private final By finishButton = By.id("finish");

    public CheckoutOverviewPage(WebDriver driver) {
        super(driver);
    }

    public boolean isDisplayed() {
        return isDisplayed(pageTitle);
    }

    public int getItemCount() {
        List<WebElement> items = driver.findElements(cartItems);
        return items.size();
    }

    public String getTotalPrice() {
        return getText(totalPrice);
    }

    public CheckoutCompletePage clickFinish() {
        click(finishButton);
        return new CheckoutCompletePage(driver);
    }
}
