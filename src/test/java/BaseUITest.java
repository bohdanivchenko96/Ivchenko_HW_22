import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;


public class BaseUITest {
    public String loginUrl = "https://rozetka.com.ua";
    public WebDriver driver;
    public WebDriverWait wait;
    @BeforeClass
    public void initialChromeDriver() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 20);
    }
    @BeforeMethod
    public void getUrlPath() {
        driver.get(loginUrl);
    }

    @AfterClass
    public void closeWebDriver() {
        driver.quit();
    }
}
