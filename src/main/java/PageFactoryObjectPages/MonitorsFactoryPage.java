package PageFactoryObjectPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MonitorsFactoryPage {
    WebDriver webdriver;
    WebDriverWait wait;

    public MonitorsFactoryPage(WebDriver webdriver) {
        this.webdriver = webdriver;
        this.wait = new WebDriverWait(webdriver, 15);
        PageFactory.initElements(webdriver, this);
    }
    @FindBy(className = "product-photo__picture")
    WebElement monitorImage;
    @FindBy(css = "p[class='product-prices__big product-prices__big_color_red']")
    WebElement monitorPrice;
    @FindBy(css = "button.compare-button")
    WebElement addToCompare;
    @FindBy(className = "product__title")
    WebElement monitorName;
    @FindBy(className = "header-actions__button-counter")
    WebElement indicatorOfComparassionIcon;
    @FindBy(css = "path[clip-rule='evenodd']")
    WebElement checkComparassionIcon;

    public void waitMonitorImage() {
        wait.until(ExpectedConditions.visibilityOf(monitorImage));
    }

    public String findMonitorPrice() {
        return monitorPrice.getText().replaceAll("[^0-9]", "");
    }

    public void addMonitorToCompare() {
        addToCompare.click();
    }

    public String findMonitorName() {
        return monitorName.getText();
    }

    public void waitResultOfAddingToComprassion() {
        wait.until(ExpectedConditions.visibilityOf(indicatorOfComparassionIcon));
        wait.until(ExpectedConditions.visibilityOf(checkComparassionIcon));
    }

}
