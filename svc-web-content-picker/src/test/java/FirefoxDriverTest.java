import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class FirefoxDriverTest {

    public static void main(String[] args) throws InterruptedException {
        // System.out.println("Test Function in another module: " + CommonClass.getCurrentDate().toString());

        System.setProperty("webdriver.gecko.driver","/Users/e5/firefoxdriver_mac_arm64/geckodriver");

        FirefoxOptions options = new FirefoxOptions();
        options.setHeadless(true);


        WebDriver driver = new FirefoxDriver(options);
        driver.get("http://jnmhc.jinan.gov.cn/art/2023/1/7/art_50366_4794863.html");

        String title = driver.getTitle();
        System.out.println("title: " + title);

        WebElement webElement = driver.findElement(By.xpath("//*[@id='barrierfree_container']/div[5]/div/div/table/tbody/tr[1]/td/table/tbody/tr/td[2]/span"));
        System.out.println("webElement.getText(): " + webElement.getText());

        Thread.sleep(3000);

        driver.quit();
    }

}
