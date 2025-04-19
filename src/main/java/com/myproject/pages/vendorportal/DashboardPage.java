package com.myproject.pages.vendorportal;

import com.myproject.pages.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DashboardPage extends AbstractPage {

    private static final Logger log = LoggerFactory.getLogger(DashboardPage.class);

    @FindBy(id = "monthly-earning")
    private WebElement monthlyEarningElement;

    @FindBy(id = "annual-earning")
    private WebElement annualEarningElement;

    @FindBy(id = "monthly-earning")
    private WebElement profitMarginElement;

    @FindBy(id = "available-inventory")
    private WebElement availableInventoryElement;

    @FindBy(xpath = "//div[@id='dataTable_filter']//input")
    private WebElement searchInput;

    @FindBy(id = "dataTable_info")
    private WebElement searchResultsCountElement;

    @FindBy(xpath = "//a[@id='userDropdown']//img")
    private WebElement userProfileImageElement;

    @FindBy(linkText = "Logout")
    private WebElement logoutLink;

    @FindBy(xpath = "//div[@id='logoutModal']//a[text()='Logout']")
    private WebElement modalLogoutButton;

    public DashboardPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isAt() {
        this.wait.until(ExpectedConditions.visibilityOf(this.monthlyEarningElement));
        return this.monthlyEarningElement.isDisplayed();
    }

    public String getMonthlyEarning() {
        return this.monthlyEarningElement.getText();
    }

    public String getAnnualEarning() {
        return this.annualEarningElement.getText();
    }

    public String getProfileMargin() {
        return this.profitMarginElement.getText();
    }

    public String getAvailableInventory() {
        return this.availableInventoryElement.getText();
    }

    public void searchOrderHistoryBy(String keyword) {
        this.searchInput.sendKeys(keyword);
    }

    public int getSearchResultsCount() {
        String resultText = this.searchResultsCountElement.getText();
        String[] arr = resultText.split(" ");
        int count = Integer.parseInt(arr[5]);
        log.info("Results count: {}", count);
        return count;
    }

    public void logout() {
        this.userProfileImageElement.click();
        this.logoutLink.click();
        this.modalLogoutButton.click();
    }
}
