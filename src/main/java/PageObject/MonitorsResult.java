package PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class MonitorsResult {
    WebDriver webdriver;
    WebDriverWait wait;

    public MonitorsResult(WebDriver webdriver) {
        this.webdriver = webdriver;
        this.wait = new WebDriverWait(webdriver, 15);
    }

    By catalogSettings = By.className("catalog-settings");
    By priceValue = By.cssSelector(".goods-tile__price-value");


    public void waitCatalogViewed() {
        wait.until(ExpectedConditions.presenceOfElementLocated(catalogSettings));
    }

    public WebElement sortMonitorsByPrice(int price) {
        List<WebElement> monitorsList = webdriver.findElements(priceValue);
        WebElement selectedMonitor = null;
        for (WebElement webElement : monitorsList) {
            int monitorPrice = Integer.parseInt(webElement.getText().replaceAll("\\s+", ""));
            if (monitorPrice < price) {
                selectedMonitor = webElement;
                break;
            }
        }
        return selectedMonitor;
    }

}
