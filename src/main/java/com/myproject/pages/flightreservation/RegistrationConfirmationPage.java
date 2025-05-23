package com.myproject.pages.flightreservation;

import com.myproject.pages.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class RegistrationConfirmationPage extends AbstractPage {

    @FindBy(id ="go-to-flights-search")
    private WebElement goToFlightsSearchButton;

    @FindBy(xpath = "//div[@id='registration-confirmation-section']//p//b")
    private WebElement nameElement;

    public RegistrationConfirmationPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isAt() {
        this.wait.until(ExpectedConditions.visibilityOf(this.goToFlightsSearchButton));
        return this.goToFlightsSearchButton.isDisplayed();
    }

    public String getDisplayedName() {
        return this.nameElement.getText();
    }

    public void goToFlightsSearch() {
        this.goToFlightsSearchButton.click();
    }
}
