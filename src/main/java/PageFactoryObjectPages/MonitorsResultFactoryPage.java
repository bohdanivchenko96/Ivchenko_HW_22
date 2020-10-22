package PageFactoryObjectPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class MonitorsResultFactoryPage {
    WebDriver webdriver;
    WebDriverWait wait;

    public MonitorsResultFactoryPage(WebDriver webdriver) {
        this.webdriver = webdriver;
        this.wait = new WebDriverWait(webdriver, 15);
        PageFactory.initElements(webdriver, this);
    }
    @FindBy(className = "catalog-settings")
    WebElement catalogSettings;
    @FindBy(css = ".goods-tile__price-value")
    List<WebElement> priceValue;


    public void waitCatalogViewed() {
        wait.until(ExpectedConditions.visibilityOf(catalogSettings));
    }

    public WebElement sortMonitorsByPrice(int price) {
        List<WebElement> monitorsList = priceValue;
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
