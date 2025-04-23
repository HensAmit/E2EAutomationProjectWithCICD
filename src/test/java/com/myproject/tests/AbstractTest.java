package com.myproject.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class AbstractTest {

    protected WebDriver driver;

    @BeforeTest
    public void setDriver() {
        try {
            if (Boolean.getBoolean("selenium.grid.enabled")) {
                this.driver = getRemoteDriver();
            } else {
                this.driver = getLocalDriver();
                driver.manage().window().maximize();
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    private WebDriver getRemoteDriver() throws MalformedURLException {
        Capabilities capabilities;
        if(System.getProperty("browser").equalsIgnoreCase("chrome")) {
            ChromeOptions options = new ChromeOptions();
            Map<String, Object> prefs = new HashMap<>();
            prefs.put("profile.default_content_setting_values.notifications", 2); // Block notifications
            prefs.put("credentials_enable_service", false); // Disable password manager
            prefs.put("password_leak_detection_enabled", false); // Disable password leak detection
            options.setExperimentalOption("prefs", prefs);
            options.addArguments("--incognito");
            capabilities = options;
        } else {
            capabilities = new FirefoxOptions();
        }
        return new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilities);
    }

    private WebDriver getLocalDriver() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("profile.default_content_setting_values.notifications", 2); // Block notifications
        prefs.put("credentials_enable_service", false); // Disable password manager
        prefs.put("password_leak_detection_enabled", false); // Disable password leak detection
        options.setExperimentalOption("prefs", prefs);
        options.addArguments("--incognito");
        return new ChromeDriver(options);
    }

    @AfterTest
    public void quitDriver() {
        this.driver.quit();
    }
}
