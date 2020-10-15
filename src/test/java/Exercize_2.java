import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class Exercize_2  extends BaseUITest{

    @Test
    public void filterPhonesByManufacturer(){

        searchBySearchingField();
        addFilterByGroup();
        String myCssSelector = null;
        activateCheckboxFilter(myCssSelector = "label[for='Apple']");
        activateCheckboxFilter(myCssSelector = "label[for='Honor']");
        Assert.assertTrue(verifyProductsNameByFilter());

    }

    @Test
    public void filterPhonesByPrice(){
        searchBySearchingField();
        addFilterByGroup();
        setPriceFilter(5000, 15000);
        Assert.assertTrue(verifyFilterPrice(5000, 15000));
    }

    @Test
    public void myOwnFilters(){
        searchBySearchingField();
        addFilterByGroup();
        String myCssSelector = null;
        activateCheckboxFilter(myCssSelector = "label[for='Apple']");
        activateCheckboxFilter(myCssSelector = "label[for='Huawei']");
        Assert.assertTrue(verifyProductsNameByFilter());
        setPriceFilter(7000, 20000);
        Assert.assertTrue(verifyFilterPrice(7000, 20000));
    }

    private boolean verifyFilterPrice(int min, int max) {
        WebElement productCatalogs = driver.findElement(By.className("catalog-grid"));
        wait.until(ExpectedConditions.visibilityOf(productCatalogs));
        List<WebElement> productPrices  = driver.findElements(By.className("goods-tile__price-value"));
        List<Integer> testList = new ArrayList<>();
        for(WebElement webElement : productPrices) {
            int monitorPrice = Integer.parseInt(webElement.getText().replaceAll("[^0-9]", ""));

            if ((monitorPrice > min) && (monitorPrice < max) ) {
                testList.add(monitorPrice);
            }
        }
        if (productPrices.size() == testList.size()){
            return true;
        } else {
            return false;
        }
    }

    private void setPriceFilter(int min, int max) {
        WebElement minPriceField = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("input[formcontrolname='min']")));
        minPriceField.clear();
        minPriceField.sendKeys(String.valueOf(min));
        WebElement maxPriceField = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("input[formcontrolname='max']")));
        maxPriceField.clear();
        maxPriceField.sendKeys(String.valueOf(max));
        driver.switchTo().defaultContent();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("button[class='button button_color_gray button_size_small slider-filter__button']"))).click();
    }

    private boolean verifyProductsNameByFilter() {
        WebElement productCatalogs = driver.findElement(By.className("catalog-grid"));
        wait.until(ExpectedConditions.visibilityOf(productCatalogs));
        List<WebElement> productNames  = driver.findElements(By.cssSelector("span[class='goods-tile__title']"));
        List<String> testList = new ArrayList<>();
        for(WebElement webElement : productNames) {
            String name = webElement.getText();

            if (name.contains("Samsung") || name.contains("Apple") || name.contains("Huawei")) {
                testList.add(name);

            }
        }
        if (productNames.size() == testList.size()){
            return true;
        } else {
            return false;
        }
    }

    private void activateCheckboxFilter(String id) {
        WebElement filterCheckbox = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(id)));
        filterCheckbox.click();
    }

    private void addFilterByGroup() {
        WebElement mobilePhoneFilterName = wait.until(ExpectedConditions.presenceOfElementLocated(By.partialLinkText("Мобильные телефоны")));
        mobilePhoneFilterName.click();
    }

    private void searchBySearchingField() {
        WebElement searchByProductName = wait.until(ExpectedConditions.presenceOfElementLocated(By.name("search")));
        searchByProductName.sendKeys("samsung");
        searchByProductName.sendKeys(Keys.ENTER);
    }
}
