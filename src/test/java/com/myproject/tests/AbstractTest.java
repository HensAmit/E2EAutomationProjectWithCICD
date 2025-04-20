package com.myproject.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class AbstractTest {

    protected WebDriver driver;

    @BeforeTest
    public void setDriver() {
        WebDriverManager.edgedriver().setup();
        this.driver = new EdgeDriver();
        driver.manage().window().maximize();
    }

    @AfterTest
    public void quitDriver() {
        this.driver.quit();
    }
}
