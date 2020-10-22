import PageFactoryObjectPages.ComparisonFactoryPage;
import PageFactoryObjectPages.MonitorsFactoryPage;
import PageFactoryObjectPages.MonitorsResultFactoryPage;
import PageFactoryObjectPages.RozetkaHomePageFactoryPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class Exercize1_PageFactoryObjectTest extends BaseUITest {


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
        ComparisonFactoryPage comparisonPage = new ComparisonFactoryPage(driver, wait);
        comparisonPage.navigateToComparison();
    }

    private void maximisizeBrowserWindow() {
        driver.manage().window().maximize();
    }

    public Monitor addMonitorToComparison(Monitor monitor) {
        MonitorsFactoryPage monitorPage = new MonitorsFactoryPage(driver);
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
        RozetkaHomePageFactoryPage rozetkaHomePage = new RozetkaHomePageFactoryPage(driver);
        rozetkaHomePage.waitForHoverPopup();
        rozetkaHomePage.selectMonitorsAndNotebooksGroup();
        rozetkaHomePage.selectMonitorsGroup();
    }

    public void selectFirstElementWithLessPrice(int price) {
        MonitorsResultFactoryPage monitorsResult = new MonitorsResultFactoryPage(driver);
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
        ComparisonFactoryPage comparisonFactoryPage = new ComparisonFactoryPage(driver, wait);
        List<String> monitorsFactuelNames = new ArrayList<>();
        monitorsFactuelNames.add(firstMonitor.getMonitorName());
        monitorsFactuelNames.add(secondMonitor.getMonitorName());
        List<String> monitorsExpectedNames = new ArrayList<>();
        for (WebElement webElement : comparisonFactoryPage.getListOfProductsHeading()) {
            monitorsExpectedNames.add(webElement.getText());
        }
        Assert.assertEquals(monitorsFactuelNames, monitorsExpectedNames, "MonitorsPF name are equals");
    }

    private void verifyCountOfMonitor(int comparassionImageCountValue) {
        ComparisonFactoryPage comparisonFactoryPage = new ComparisonFactoryPage(driver, wait);
        Assert.assertEquals(comparassionImageCountValue, comparisonFactoryPage.getListOfProductsHeading().size(), "Monitor counts are equals");
    }

    private void verifyPricesOfMonitor(Monitor firstMonitor, Monitor secondMonitor) {
        ComparisonFactoryPage comparisonFactoryPage = new ComparisonFactoryPage(driver, wait);
        List<Integer> monitorsFactuelPrices = new ArrayList<>();
        monitorsFactuelPrices.add(firstMonitor.getMonitorPrice());
        monitorsFactuelPrices.add(secondMonitor.getMonitorPrice());
        List<Integer> monitorsExpectedPrices = new ArrayList<>();
        for (WebElement webElement : comparisonFactoryPage.getListOfProductsPrices()) {
            String word = webElement.getText().concat(" ");
            String[] words = word.split("\u20B4\n");
            word = words[1].replaceAll("[^0-9]", "");
            monitorsExpectedPrices.add(Integer.parseInt(word));
        }
        Assert.assertEquals(monitorsFactuelPrices, monitorsExpectedPrices, "Monitor prices are equals");
    }

    private void checkComparisonImage(int comparassionImageCountValue) {
        ComparisonFactoryPage comparisonFactoryPage = new ComparisonFactoryPage(driver, wait);
        comparisonFactoryPage.waitComparisonImage();
        if (comparassionImageCountValue > 1) {
            Assert.assertTrue(comparisonFactoryPage.checkComparisonIconValue());
            return;
        }
        int count = Integer.parseInt(comparisonFactoryPage.getComparisonIconValue());
        Assert.assertEquals(comparassionImageCountValue, count);
    }

}

