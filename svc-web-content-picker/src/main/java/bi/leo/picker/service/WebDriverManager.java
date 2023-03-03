package bi.leo.picker.service;

import bi.leo.picker.config.WebDriverConfig;
import bi.leo.picker.exception.CustomWebDriverException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.MalformedURLException;
import java.net.URL;

@Service
public class WebDriverManager {

    @Autowired
    private WebDriverConfig webDriverConfig;

    public WebDriver getWebDriver() throws CustomWebDriverException {
        if("chrome".equalsIgnoreCase(webDriverConfig.getType())) {
            return getRemoteChromeDriver();
        } else {
            System.out.println("[ERROR] should not get here...");
            return null;
        }
    }

    public WebDriver getRemoteChromeDriver() throws CustomWebDriverException {

        System.out.println("Getting Remote Chrome Driver - " + webDriverConfig.getName()
                + ": " + webDriverConfig.getBrowserVersion());

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setCapability("browserVersion", webDriverConfig.getBrowserVersion());

        chromeOptions.setCapability("se:name", "My simple test");
        chromeOptions.setCapability("se:sampleMetadata", "Sample metadata value");

        WebDriver driver = null;

        try {
            driver = new RemoteWebDriver(new URL(webDriverConfig.getGridUrl()), chromeOptions);
        } catch (MalformedURLException e) {
            throw new CustomWebDriverException("GRID01", "unable to connect to selenium grid URL");
        }

        return driver;
    }

}
