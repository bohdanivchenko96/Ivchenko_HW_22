package PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Monitors {
    WebDriver webdriver;
    WebDriverWait wait;

    public Monitors(WebDriver webdriver) {
        this.webdriver = webdriver;
        this.wait = new WebDriverWait(webdriver, 15);
    }

    By monitorImage = By.className("product-photo__picture");
    By monitorPrice = By.cssSelector("p[class='product-prices__big product-prices__big_color_red']");
    By addToCompare = By.cssSelector("button.compare-button");
    By monitorName = By.className("product__title");
    By indicatorOfComparassionIcon = By.className("header-actions__button-counter");
    By checkComparassionIcon = By.cssSelector("path[clip-rule='evenodd']");

    public void waitMonitorImage() {
        wait.until(ExpectedConditions.presenceOfElementLocated(monitorImage));
    }

    public String findMonitorPrice() {
        return webdriver.findElement(monitorPrice).getText().replaceAll("[^0-9]", "");
    }

    public void addMonitorToCompare() {
        webdriver.findElement(addToCompare).click();
    }

    public String findMonitorName() {
        return webdriver.findElement(monitorName).getText();
    }

    public void waitResultOfAddingToComprassion() {
        wait.until(ExpectedConditions.presenceOfElementLocated(indicatorOfComparassionIcon));
        wait.until(ExpectedConditions.presenceOfElementLocated(checkComparassionIcon));
    }

}
