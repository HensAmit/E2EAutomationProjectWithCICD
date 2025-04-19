package com.myproject.pages.flightreservation;

import com.myproject.pages.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RegistrationConfirmationPage extends AbstractPage {

    @FindBy(id ="Go To Flights Search")
    private WebElement goToFlightsSearchButton;

    public RegistrationConfirmationPage(WebDriver driver) {
        super(driver);
    }

    public void goToFlightsSearch() {
        this.goToFlightsSearchButton.click();
    }
}
