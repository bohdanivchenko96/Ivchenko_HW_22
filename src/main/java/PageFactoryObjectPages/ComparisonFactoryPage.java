package PageFactoryObjectPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class ComparisonFactoryPage {
    WebDriver webdriver;
    WebDriverWait waiter;

    public ComparisonFactoryPage(WebDriver driver, WebDriverWait wait) {
        this.webdriver = driver;
        this.waiter = new WebDriverWait(driver, 15);
        PageFactory.initElements(webdriver, this);
    }
    @FindBy(css = "span[class='header-actions__button-counter']")
    WebElement comparisonImage;
    @FindBy(css = "path[fill='#00a046']")
    WebElement comparisonImageValue;
    @FindBy(className = "header-actions__button-icon")
    WebElement comparisonIcon;
    @FindBy(className = "comparison-modal__link")
    WebElement comparisonButtonLink;
    @FindBy(css = "a.product__heading")
    WebElement productHeading;
    @FindBy(css = "a.product__heading")
    List<WebElement> productHeadingList;
    @FindBy(css = "div[class='product__price product__price--red']")
    List<WebElement> productPrices;

    public void waitComparisonImage() {
        waiter.until(ExpectedConditions.visibilityOf(comparisonImage));
    }

    public void waitComparisonImageValue() {
        waiter.until(ExpectedConditions.visibilityOf(comparisonImageValue));
    }

    public String getComparisonIconValue() {
        return comparisonImage.getText();
    }

    public Boolean checkComparisonIconValue() {
        return waiter.until(ExpectedConditions.textToBePresentInElement(comparisonImage, "2"));
    }

    public void navigateToComparison() {
        comparisonIcon.click();
        waiter.until(ExpectedConditions.visibilityOf(comparisonButtonLink)).click();
    }

    public List<WebElement> getListOfProductsHeading() {
        waiter.until(ExpectedConditions.visibilityOf(productHeading));
        List<WebElement> monitorsList = waiter.until(ExpectedConditions.visibilityOfAllElements(productHeadingList));
        return monitorsList;
    }

    public List<WebElement> getListOfProductsPrices() {
        List<WebElement> monitorsPriceList = productPrices;
        return monitorsPriceList;
    }

}
