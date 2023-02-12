package bi.leo.picker.service;

import bi.leo.picker.exception.WebDriverPoolException;
import org.openqa.selenium.WebDriver;

public interface WebDriverPool {

    WebDriver getWebDriver() throws WebDriverPoolException;

    boolean releaseWebDriver(WebDriver webDriver);

}
