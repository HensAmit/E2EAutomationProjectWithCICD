package com.myproject.tests;

import static com.myproject.util.Constants.*;

import com.myproject.listener.TestListener;
import com.myproject.util.ConfigUtil;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestContext;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

@Listeners({TestListener.class})
public class AbstractTest {

    protected WebDriver driver;
    private static final Logger log = LoggerFactory.getLogger(AbstractTest.class);

    @BeforeSuite
    public void setUpConfig() {
        ConfigUtil.initialize();
    }

    @BeforeTest
    public void setDriver(ITestContext testContext) {
        try {
            this.driver = Boolean.parseBoolean(ConfigUtil.get(GRID_ENABLED)) ? getRemoteDriver() : getLocalDriver();
            testContext.setAttribute(DRIVER, this.driver);
        } catch(Exception e) {
            log.error("Failed in setDriver() : ", e);
        }
    }

    private WebDriver getRemoteDriver() throws MalformedURLException {
        Capabilities capabilities = new ChromeOptions();
        if(ConfigUtil.get(BROWSER).equalsIgnoreCase(CHROME)) {
            ChromeOptions options = new ChromeOptions();
            Map<String, Object> prefs = new HashMap<>();
            prefs.put("profile.default_content_setting_values.notifications", 2); // Block notifications
            prefs.put("credentials_enable_service", false); // Disable password manager
            prefs.put("password_leak_detection_enabled", false); // Disable password leak detection
            options.setExperimentalOption("prefs", prefs);
            options.addArguments("--incognito");
            capabilities = options;
        } else if(ConfigUtil.get(BROWSER).equalsIgnoreCase(FIREFOX)) {
            capabilities = new FirefoxOptions();
        }
        String urlFormat = ConfigUtil.get(GRID_URL_FORMAT);
        String hubHost = ConfigUtil.get(GRID_HUB_HOST);
        String url = String.format(urlFormat, hubHost);
        log.info("grid url: {}", url);
        return new RemoteWebDriver(new URL(url), capabilities);
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
