package bi.leo.picker.service;

import bi.leo.picker.config.WebDriverConfig;
import bi.leo.picker.exception.WebDriverPoolException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WebDriverPoolManager implements WebDriverPool, InitializingBean, DisposableBean {

    @Autowired
    private WebDriverConfig webDriverConfig;

    private List<WebDriver> webDriverPool;

    private List<WebDriver> usedWebDrivers = new ArrayList<>();

    private int webDriverCount = 0;

    private static String CHROME_DRIVER_NAME = "webdriver.chrome.driver";

    private static String FIREFOX_DRIVER_NAME = "webdriver.gecko.driver";


    @Override
    public synchronized WebDriver getWebDriver() throws WebDriverPoolException {
        if (webDriverPool.size() <= 0) {
            if(webDriverCount < webDriverConfig.getMaxPoolSize()) {
                webDriverPool.add(buildWebDriver());
            } else {
                throw new WebDriverPoolException("B001", "No extra resource in web driver pool");
            }
        }

        WebDriver webDriver = webDriverPool.remove(webDriverPool.size() - 1);
        usedWebDrivers.add(webDriver);
        return webDriver;
    }

    @Override
    public synchronized boolean releaseWebDriver(WebDriver webDriver) {
        webDriverPool.add(webDriver);
        return usedWebDrivers.remove(webDriver);
    }

    @Override
    public void afterPropertiesSet() throws Exception {

        System.out.println("Driver Name: " + webDriverConfig.getName());
        System.out.println("Driver Location: " + webDriverConfig.getLocation());
        System.out.println("Headless: " + webDriverConfig.getHeadless());
        System.out.println("Initial Pool Size: " + webDriverConfig.getInitialPoolSize());
        System.out.println("Max Pool Size: " + webDriverConfig.getMaxPoolSize());

        webDriverPool = new ArrayList<>();

        for (int i = 0; i < webDriverConfig.getInitialPoolSize(); i++ ) {
            webDriverPool.add(buildWebDriver());
        }
    }

    @Override
    public void destroy() throws Exception {
        // release resources
        webDriverPool.stream().forEach(webDriver -> {
            webDriver.quit();
        });

        // release resources
        usedWebDrivers.stream().forEach(webDriver -> {
            webDriver.quit();
        });
    }

    private WebDriver buildWebDriver() {
        System.setProperty(webDriverConfig.getName(), webDriverConfig.getLocation());

        WebDriver webDriver = null;

        if (CHROME_DRIVER_NAME.equalsIgnoreCase(webDriverConfig.getName())) {
            System.setProperty("webdriver.chrome.whitelistedIps", "");

            ChromeOptions chromeOptions = new ChromeOptions();

            // enable headless or not
            if(webDriverConfig.getHeadless()) {
                chromeOptions.addArguments("--headless");

                chromeOptions.addArguments("--no-sandbox");
                chromeOptions.addArguments("--disable-gpu");
                chromeOptions.addArguments("--disable-dev-shm-usage");

                chromeOptions.addArguments("--disable-gpu");
                chromeOptions.addArguments("--window-size=1920,1080");
                chromeOptions.addArguments("--start-maximized");
            }

            chromeOptions.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});

            webDriver = new ChromeDriver(chromeOptions);
        } else if(FIREFOX_DRIVER_NAME.equalsIgnoreCase(webDriverConfig.getName())) {
            FirefoxOptions options = new FirefoxOptions();

            // enable headless or not
            if(webDriverConfig.getHeadless()) {
                options.setHeadless(true);
            }

            webDriver = new FirefoxDriver(options);
        } else {
            System.out.println("[ERROR] should not be here!");
        }

        webDriverCount ++;

        return webDriver;
    }
}
