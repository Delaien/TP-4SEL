package com.saucedemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutPage extends BasePage {
    private final By pageTitle = By.className("title");
    private final By firstNameField = By.id("first-name");
    private final By lastNameField = By.id("last-name");
    private final By zipCodeField = By.id("postal-code");
    private final By continueButton = By.id("continue");
    private final By errorMessage = By.cssSelector("h3[data-test='error']");

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    public boolean isDisplayed() {
        return isDisplayed(pageTitle);
    }

    public CheckoutPage fillInformation(String firstName, String lastName, String zipCode) {
        type(firstNameField, firstName);
        type(lastNameField, lastName);
        type(zipCodeField, zipCode);
        return this;
    }

    public CheckoutOverviewPage clickContinue() {
        click(continueButton);
        return new CheckoutOverviewPage(driver);
    }

    public String getErrorMessage() {
        return getText(errorMessage);
    }
}
