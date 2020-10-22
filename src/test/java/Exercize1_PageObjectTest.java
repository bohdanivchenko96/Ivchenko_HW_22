import PageFactoryObjectPages.MonitorsFactoryPage;
import PageFactoryObjectPages.MonitorsResultFactoryPage;
import PageObject.Comparison;
import PageObject.Monitors;
import PageObject.MonitorsResult;
import PageObject.RozetkaHomePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class Exercize1_PageObjectTest extends BaseUITest {


    @Test
    public void rozetkaTestPO() {
        int comparassionImageCountValue = 0;
        maximisizeBrowserWindow();
        selectGroupInHiddenMenu();
        selectFirstElementWithLessPrice(3000);
        Monitor firstMonitor = new Monitor();
        comparassionImageCountValue++;
        addMonitorToComparison(firstMonitor);
        checkComparisonImage(comparassionImageCountValue);

        driver.navigate().back();

        selectFirstElementWithLessPrice(firstMonitor.getMonitorPrice());
        Monitor secondMonitor = new Monitor();
        comparassionImageCountValue++;
        addMonitorToComparison(secondMonitor);
        checkComparisonImage(comparassionImageCountValue);

        navigateToComprassion();
        verifyCountOfMonitor(comparassionImageCountValue);

        verifyNamesOfMonitor(firstMonitor, secondMonitor);
        System.out.println("Names of monitor is verified");
        verifyPricesOfMonitor(firstMonitor, secondMonitor);
        System.out.println("Prices of monitor is verified");

    }

    private void navigateToComprassion() {
        Comparison comparisonPage = new Comparison(driver, wait);
        comparisonPage.navigateToComparison();
    }

    private void maximisizeBrowserWindow() {
        driver.manage().window().maximize();
    }

    public Monitor addMonitorToComparison(Monitor monitor) {
        Monitors monitorPage = new Monitors(driver);
        monitorPage.waitMonitorImage();
        int monitorPrice = Integer.parseInt(monitorPage.findMonitorPrice());
        monitorPage.addMonitorToCompare();
        String monitorName = monitorPage.findMonitorName();
        monitorPage.waitResultOfAddingToComprassion();
        monitor.setMonitorName(monitorName);
        monitor.setMonitorPrice(monitorPrice);
        return monitor;
    }

    void selectGroupInHiddenMenu() {
        RozetkaHomePage rozetkaHomePage = new RozetkaHomePage(driver);
        rozetkaHomePage.waitForHoverPopup();
        rozetkaHomePage.selectMonitorsAndNotebooksGroup();
        rozetkaHomePage.selectMonitorsGroup();
    }

    public void selectFirstElementWithLessPrice(int price) {
        MonitorsResult monitorsResult = new MonitorsResult(driver);
        monitorsResult.waitCatalogViewed();
        monitorsResult.sortMonitorsByPrice(price);
        try {
            WebElement selectedMonitorParent = monitorsResult.sortMonitorsByPrice(price).findElement(By.xpath(".."));
            selectedMonitorParent.findElement(By.xpath("ancestor::*/a[@class = 'goods-tile__picture']")).click();
        } catch (NullPointerException e) {
            System.out.println("NullPointerException is caught");
        }
    }

    private void verifyNamesOfMonitor(Monitor firstMonitor, Monitor secondMonitor) {
        Comparison comparisonPage = new Comparison(driver, wait);
        List<String> monitorsFactuelNames = new ArrayList<>();
        monitorsFactuelNames.add(firstMonitor.getMonitorName());
        monitorsFactuelNames.add(secondMonitor.getMonitorName());
        List<String> monitorsExpectedNames = new ArrayList<>();
        for (WebElement webElement : comparisonPage.getListOfProductsHeading()) {
            monitorsExpectedNames.add(webElement.getText());
        }
        Assert.assertEquals(monitorsFactuelNames, monitorsExpectedNames, "MonitorsPF name are equals");
    }

    private void verifyCountOfMonitor(int comparassionImageCountValue) {
        Comparison comparisonPage = new Comparison(driver, wait);
        Assert.assertEquals(comparassionImageCountValue, comparisonPage.getListOfProductsHeading().size(), "Monitor counts are equals");
    }

    private void verifyPricesOfMonitor(Monitor firstMonitor, Monitor secondMonitor) {
        Comparison comparisonPage = new Comparison(driver, wait);
        List<Integer> monitorsFactuelPrices = new ArrayList<>();
        monitorsFactuelPrices.add(firstMonitor.getMonitorPrice());
        monitorsFactuelPrices.add(secondMonitor.getMonitorPrice());
        List<Integer> monitorsExpectedPrices = new ArrayList<>();
        for (WebElement webElement : comparisonPage.getListOfProductsPrices()) {
            String word = webElement.getText().concat(" ");
            String[] words = word.split("\u20B4\n");
            word = words[1].replaceAll("[^0-9]", "");
            monitorsExpectedPrices.add(Integer.parseInt(word));
        }
        Assert.assertEquals(monitorsFactuelPrices, monitorsExpectedPrices, "Monitor prices are equals");
    }

    private void checkComparisonImage(int comparassionImageCountValue) {
        Comparison comparisonPage = new Comparison(driver, wait);
        comparisonPage.waitComparisonImage();
        if (comparassionImageCountValue > 1) {
            comparisonPage.waitComparisonImageValue();
            Assert.assertTrue(comparisonPage.checkComparisonIconValue());
            return;
        }
        int count = Integer.parseInt(comparisonPage.getComparisonIconValue());
        Assert.assertEquals(comparassionImageCountValue, count);
    }

}

