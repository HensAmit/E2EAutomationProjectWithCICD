package com.myproject.pages.flightreservation;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegistrationConfirmationPage {

    @FindBy(id ="Go To Flights Search")
    private WebElement goToFlightsSearchButton;

    public RegistrationConfirmationPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void goToFlightsSearch() {
        this.goToFlightsSearchButton.click();
    }
}
