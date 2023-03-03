import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.chromium.ChromiumOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class RemoteChromeDriverTest {

    public static void main(String[] args) throws MalformedURLException {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setCapability("browserVersion", "98.0");

        //chromeOptions.setCapability("se:name", "My simple test");
        //chromeOptions.setCapability("se:sampleMetadata", "Sample metadata value");

        WebDriver driver = new RemoteWebDriver(new URL("http://localhost:4444"), chromeOptions);

        System.out.println("step 01");

        driver.get("http://jnmhc.jinan.gov.cn/art/2023/1/7/art_50366_4794863.html");

        System.out.println("step 02");


        String title = driver.getTitle();
        System.out.println("title: " + title);

        WebElement webElement = driver.findElement(By.xpath("//*[@id='barrierfree_container']/div[5]/div/div/table/tbody/tr[1]/td/table/tbody/tr/td[2]/span"));
        System.out.println("webElement.getText(): " + webElement.getText());

        driver.quit();
    }

}
