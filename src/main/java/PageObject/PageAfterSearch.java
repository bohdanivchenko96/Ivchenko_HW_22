package PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

public class PageAfterSearch {
    WebDriver webdriver;
    WebDriverWait wait;

    public PageAfterSearch(WebDriver webdriver) {
        this.webdriver = webdriver;
        this.wait = new WebDriverWait(webdriver, 15);
    }

    By mobilePhoneFilterName = By.partialLinkText("Мобильные телефоны");
    By checkboxName;
    By catalogGrid = By.className("catalog-grid");
    By productsTitle = By.cssSelector("span[class='goods-tile__title']");
    By minPrice = By.cssSelector("input[formcontrolname='min']");
    By maxPrice = By.cssSelector("input[formcontrolname='max']");
    By waitButtonOfResult = By.cssSelector("button[class='button button_color_gray button_size_small slider-filter__button']");
    By priceTitle = By.className("goods-tile__price-value");

    public void addFilterByMobilePhones() {
        wait.until(ExpectedConditions.presenceOfElementLocated(mobilePhoneFilterName)).click();
    }

    public void activateCheckbox(String id) {
        wait.until(ExpectedConditions.presenceOfElementLocated(checkboxName = By.cssSelector(id))).click();
    }

    public void waitCatalogGridView() {
        wait.until(ExpectedConditions.presenceOfElementLocated(catalogGrid));
    }

    public List<WebElement> createListOfProductsTitle() {
        List<WebElement> productNames = webdriver.findElements(productsTitle);
        return productNames;
    }

    public void clearPriceFields() {
        wait.until(ExpectedConditions.presenceOfElementLocated(minPrice)).clear();
        wait.until(ExpectedConditions.presenceOfElementLocated(maxPrice)).clear();
    }

    public void setupMinAndMaxPrices(int min, int max) {
        webdriver.findElement(minPrice).sendKeys(String.valueOf(min));
        webdriver.findElement(maxPrice).sendKeys(String.valueOf(max));
    }

    public void waitResultOfPriceFilters() {
        wait.until(ExpectedConditions.presenceOfElementLocated(waitButtonOfResult)).click();
    }

    public List<WebElement> createListOfPriceTitle() {
        List<WebElement> productPrices = webdriver.findElements(priceTitle);
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
