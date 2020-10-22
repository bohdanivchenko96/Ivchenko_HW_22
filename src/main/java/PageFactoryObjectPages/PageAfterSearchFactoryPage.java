package PageFactoryObjectPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

public class PageAfterSearchFactoryPage {
    WebDriver webdriver;
    WebDriverWait wait;

    public PageAfterSearchFactoryPage(WebDriver webdriver) {
        this.webdriver = webdriver;
        this.wait = new WebDriverWait(webdriver, 15);
        PageFactory.initElements(webdriver, this);
    }
    @FindBy(partialLinkText = "Мобильные телефоны")
    WebElement mobilePhoneFilterName;
    By checkboxName;
    @FindBy(className = "catalog-grid")
    WebElement catalogGrid;
    @FindBy(css = "span[class='goods-tile__title']")
    List<WebElement> productsTitle;
    @FindBy(css = "input[formcontrolname='min']")
    WebElement minPrice;
    @FindBy(css = "input[formcontrolname='max']")
    WebElement maxPrice;
    @FindBy(css = "button[class='button button_color_gray button_size_small slider-filter__button']")
    WebElement waitButtonOfResult;
    @FindBy(className = "goods-tile__price-value")
    List<WebElement> priceTitle;

    public void addFilterByMobilePhones() {
        wait.until(ExpectedConditions.visibilityOf(mobilePhoneFilterName)).click();
    }

    public void activateCheckbox(String id) {
        wait.until(ExpectedConditions.presenceOfElementLocated(checkboxName = By.cssSelector(id))).click();
    }

    public void waitCatalogGridView() {
        wait.until(ExpectedConditions.visibilityOf(catalogGrid));
    }

    public List<WebElement> createListOfProductsTitle() {
        List<WebElement> productNames = productsTitle;
        return productNames;
    }

    public void clearPriceFields() {
        wait.until(ExpectedConditions.visibilityOf(minPrice)).clear();
        wait.until(ExpectedConditions.visibilityOf(maxPrice)).clear();
    }

    public void setupMinAndMaxPrices(int min, int max) {
        minPrice.sendKeys(String.valueOf(min));
        maxPrice.sendKeys(String.valueOf(max));
    }

    public void waitResultOfPriceFilters() {
        wait.until(ExpectedConditions.visibilityOf(waitButtonOfResult)).click();
    }

    public List<WebElement> createListOfPriceTitle() {
        List<WebElement> productPrices = priceTitle;
        return productPrices;
    }

    public boolean verifyPriceListAfterFilter(int min, int max) {
        List<Integer> testList = new ArrayList<>();
        for (WebElement webElement : createListOfPriceTitle()) {
            int monitorPrice = Integer.parseInt(webElement.getText().replaceAll("[^0-9]", ""));
            if ((monitorPrice > min) && (monitorPrice < max)) {
                testList.add(monitorPrice);
            }
        }
        if (createListOfPriceTitle().size() == testList.size()) {
            return true;
        } else {
            return false;
        }
    }
}
