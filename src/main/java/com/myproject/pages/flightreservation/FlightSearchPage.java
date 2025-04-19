package com.myproject.pages.flightreservation;

import com.myproject.pages.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

public class FlightSearchPage extends AbstractPage {

    @FindBy(id = "passengers")
    private WebElement passengersDropdown;

    @FindBy(id = "search-flights")
    private WebElement searchFlightButton;

    public FlightSearchPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isAt() {
        this.wait.until(ExpectedConditions.visibilityOf(this.passengersDropdown));
        return this.passengersDropdown.isDisplayed();
    }

    public void selectPassengers(String noOfPassengers) {
        Select passengers = new Select(this.passengersDropdown);
        passengers.selectByValue(noOfPassengers);
    }

    public void searchFlight() {
        this.searchFlightButton.click();
    }
}
