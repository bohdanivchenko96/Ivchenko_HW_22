package PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class Comparison {
    WebDriver webdriver;
    WebDriverWait waiter;

    public Comparison(WebDriver driver, WebDriverWait wait) {
        this.webdriver = driver;
        this.waiter = new WebDriverWait(webdriver, 15);
    }

    By comparisonImage = By.className("header-actions__button-counter");
    By comparisonImageValue = By.cssSelector("path[fill='#00a046']");
    By comparisonIcon = By.className("header-actions__button-icon");
    By comparisonButtonLink = By.className("comparison-modal__link");
    By productHeading = By.cssSelector("a.product__heading");
    By productPrices = By.cssSelector("div[class='product__price product__price--red']");

    public WebElement waitComparisonImage() {
        return waiter.until(ExpectedConditions.presenceOfElementLocated(comparisonImage));
    }

    public void waitComparisonImageValue() {
        waiter.until(ExpectedConditions.presenceOfElementLocated(comparisonImageValue));
    }

    public String getComparisonIconValue() {
        return waitComparisonImage().getText();
    }

    public Boolean checkComparisonIconValue() {
        return waiter.until(ExpectedConditions.textToBePresentInElement(waitComparisonImage(), "2"));
    }

    public void navigateToComparison() {
        webdriver.findElement(comparisonIcon).click();
        waiter.until(ExpectedConditions.presenceOfElementLocated(comparisonButtonLink)).click();
    }

    public List<WebElement> getListOfProductsHeading() {
        waiter.until(ExpectedConditions.presenceOfElementLocated(productHeading));
        List<WebElement> monitorsList = webdriver.findElements(productHeading);
        return monitorsList;
    }

    public List<WebElement> getListOfProductsPrices() {
        List<WebElement> monitorsPriceList = webdriver.findElements(productPrices);
        return monitorsPriceList;
    }

}
