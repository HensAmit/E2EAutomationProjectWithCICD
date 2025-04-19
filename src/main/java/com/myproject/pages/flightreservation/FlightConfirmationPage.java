package com.myproject.pages.flightreservation;

import com.myproject.pages.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FlightConfirmationPage extends AbstractPage {

    private static final Logger log = LoggerFactory.getLogger(FlightConfirmationPage.class);

    @FindBy(xpath = "//div[@class='card-body']//div[1]//div[2]//p")
    private WebElement flightConfirmation;

    @FindBy(xpath = "//div[@class='card-body']//div[3]//div[2]//p")
    private WebElement totalPrice;

    public FlightConfirmationPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isAt() {
        this.wait.until(ExpectedConditions.visibilityOf(this.flightConfirmation));
        return this.flightConfirmation.isDisplayed();
    }

    public String getPrice() {
        log.info("Flight confirmation# : {}", this.flightConfirmation.getText());
        log.info("Total price : {}", this.totalPrice.getText());
        return this.totalPrice.getText();
    }
}
