import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class ChromeDriverTest {

    public static void main(String[] args) {
        // System.out.println("Test Function in another module: " + CommonClass.getCurrentDate().toString());

        System.setProperty("webdriver.chrome.driver","/Users/e5/chromedriver_mac_arm64/chromedriver");

        ChromeOptions chromeOptions = new ChromeOptions();
        //chromeOptions.setHeadless(true);
        chromeOptions.addArguments("--headless");
        chromeOptions.addArguments("--disable-gpu");
        chromeOptions.addArguments("--window-size=1920,1080");
        chromeOptions.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});

        WebDriver driver = new ChromeDriver(chromeOptions);
        driver.get("http://jnmhc.jinan.gov.cn/art/2023/1/7/art_50366_4794863.html");

        String title = driver.getTitle();
        System.out.println("title: " + title);

        WebElement webElement = driver.findElement(By.xpath("//*[@id='barrierfree_container']/div[5]/div/div/table/tbody/tr[1]/td/table/tbody/tr/td[2]/span"));
        System.out.println("webElement.getText(): " + webElement.getText());

        driver.quit();
    }

}
