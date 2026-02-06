package com.saucedemo.tests;

import com.saucedemo.pages.InventoryPage;
import com.saucedemo.pages.LoginPage;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

@Feature("Login functionality")
public class LoginTest extends BaseTest {

    @Test
    @Story("Valid login")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Login with standard_user should open inventory page")
    void testLoginWithValidCredentials() {
        LoginPage loginPage = new LoginPage(driver).open();
        InventoryPage inventoryPage = loginPage.login("standard_user", "secret_sauce");
        assertTrue(inventoryPage.isDisplayed(), "Inventory page should be displayed");
    }

    @Test
    @Story("Locked out user")
    @Severity(SeverityLevel.NORMAL)
    @Description("Login with locked_out_user should show locked out error")
    void testLoginWithLockedUser() {
        LoginPage loginPage = new LoginPage(driver).open();
        loginPage.login("locked_out_user", "secret_sauce");
        assertTrue(loginPage.isErrorDisplayed(), "Error message should be displayed");
        assertTrue(loginPage.getErrorMessage().toLowerCase().contains("locked out"));
    }

    @Test
    @Story("Invalid password")
    @Severity(SeverityLevel.NORMAL)
    @Description("Login with wrong password should show error")
    void testLoginWithInvalidPassword() {
        LoginPage loginPage = new LoginPage(driver).open();
        loginPage.login("standard_user", "wrong_password");
        assertTrue(loginPage.isErrorDisplayed(), "Error message should be displayed");
    }

    @Test
    @Story("Empty credentials")
    @Severity(SeverityLevel.NORMAL)
    @Description("Login with empty fields should show error")
    void testLoginWithEmptyCredentials() {
        LoginPage loginPage = new LoginPage(driver).open();
        loginPage.login("", "");
        assertTrue(loginPage.isErrorDisplayed(), "Error message should be displayed");
    }
}
