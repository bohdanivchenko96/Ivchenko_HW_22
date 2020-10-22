import PageFactoryObjectPages.PageAfterSearchFactoryPage;
import PageFactoryObjectPages.RozetkaHomePageFactoryPage;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class Exercize_2_PageFactoryObject extends BaseUITest {
    @Test
    public void filterPhonesByManufacturer() {
        driver.manage().window().maximize();
        searchBySearchingField();
        addFilterByGroup();
        activateCheckboxFilter("label[for='Apple']");
        activateCheckboxFilter("label[for='Huawei']");
        Assert.assertTrue(verifyProductsNameByFilter());
    }

    @Test
    public void filterPhonesByPrice() {
        searchBySearchingField();
        addFilterByGroup();
        setPriceFilter(5000, 15000);
        Assert.assertTrue(verifyFilterPrice(5000, 15000));
    }
    @Test
    public void myOwnFilters(){
        searchBySearchingField();
        addFilterByGroup();
        activateCheckboxFilter("label[for='Apple']");
        activateCheckboxFilter("label[for='Huawei']");
        Assert.assertTrue(verifyProductsNameByFilter());
        setPriceFilter(7000, 20000);
        Assert.assertTrue(verifyFilterPrice(7000, 20000));
    }

    private void searchBySearchingField() {
        RozetkaHomePageFactoryPage rozetkaHomePage = new RozetkaHomePageFactoryPage(driver);
        rozetkaHomePage.searchByField();
    }

    private void addFilterByGroup() {
        PageAfterSearchFactoryPage pageAfterSearch = new PageAfterSearchFactoryPage(driver);
        pageAfterSearch.addFilterByMobilePhones();
    }

    private void activateCheckboxFilter(String id) {
        PageAfterSearchFactoryPage pageAfterSearch = new PageAfterSearchFactoryPage(driver);
        pageAfterSearch.activateCheckbox(id);
    }

    private boolean verifyProductsNameByFilter() {
        PageAfterSearchFactoryPage pageAfterSearch = new PageAfterSearchFactoryPage(driver);
        pageAfterSearch.waitCatalogGridView();
        List<String> testList = new ArrayList<>();
        for (WebElement webElement : pageAfterSearch.createListOfProductsTitle()) {
            String name = webElement.getText();
            if (name.contains("Samsung") || name.contains("Apple") || name.contains("Huawei")) {
                testList.add(name);
            }
        }
        if (pageAfterSearch.createListOfProductsTitle().size() == testList.size()) {
            System.out.println("Return true");
            return true;
        } else {
            return false;
        }
    }

    private void setPriceFilter(int min, int max) {
        PageAfterSearchFactoryPage pageAfterSearch = new PageAfterSearchFactoryPage(driver);
        pageAfterSearch.clearPriceFields();
        pageAfterSearch.setupMinAndMaxPrices(min, max);
        driver.switchTo().defaultContent();
        pageAfterSearch.waitResultOfPriceFilters();
    }

    private boolean verifyFilterPrice(int min, int max) {
        PageAfterSearchFactoryPage pageAfterSearch = new PageAfterSearchFactoryPage(driver);
        pageAfterSearch.waitCatalogGridView();
        return pageAfterSearch.verifyPriceListAfterFilter(min, max);
    }
}
