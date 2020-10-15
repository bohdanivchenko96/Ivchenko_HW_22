import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class Exercize_1 extends BaseUITest {

    @Test
    public void rozetkaTest() {
        int comparassionImageCountValue = 0;
        maximisizeBrowserWindow();
        selectGroupInHiddenMenu();
        selectFirstElementWithLessPrice(3000);
        Monitor firstMonitor = new Monitor();
        comparassionImageCountValue++;
        addMonitorToComparison(firstMonitor);
        checkComparisonImage(comparassionImageCountValue);

        backButtonClick();

        selectFirstElementWithLessPrice(firstMonitor.getMonitorPrice());
        Monitor secondMonitor = new Monitor();
        comparassionImageCountValue++;
        addMonitorToComparison(secondMonitor);
        checkComparisonImage(comparassionImageCountValue);

        navigateToComprassion();
        verifyCountOfMonitor(comparassionImageCountValue);

        verifyNamesOfMonitor(firstMonitor, secondMonitor);
        verifyPricesOfMonitor(firstMonitor, secondMonitor);

    }


    private void navigateToComprassion() {
        driver.findElement(By.className("header-actions__button-icon")).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.className("comparison-modal__link"))).click();
    }


    private void backButtonClick() {
        driver.navigate().back();
    }

    private void maximisizeBrowserWindow() {
        driver.manage().window().maximize();
    }

    public Monitor addMonitorToComparison(Monitor monitor) {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.className("product-photo__picture")));
        WebElement monitorPriceText = driver.findElement(By.cssSelector("p[class='product-prices__big product-prices__big_color_red']"));
        int monitorPrice = Integer.parseInt(monitorPriceText.getText().replaceAll("[^0-9]", ""));
        driver.findElement(By.cssSelector("button.compare-button")).click();
        String monitorName = driver.findElement(By.className("product__title")).getText();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.className("header-actions__button-counter")));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("path[clip-rule='evenodd']")));
        monitor.setMonitorName(monitorName);
        monitor.setMonitorPrice(monitorPrice);
        return monitor;

    }

    void selectGroupInHiddenMenu() {

        WebElement hoverPopup = driver.findElement(By.linkText("Ноутбуки и компьютеры"));
        Actions action = new Actions(driver);
        action.moveToElement(hoverPopup).build().perform();

        WebElement monitorsGroup = wait.until(ExpectedConditions.presenceOfElementLocated(By.partialLinkText("Мониторы")));
        monitorsGroup.click();

    }

    public void selectFirstElementWithLessPrice(int price) {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.className("catalog-settings")));
        List<WebElement> monitorsList = driver.findElements(By.cssSelector(".goods-tile__price-value"));
        WebElement selectedMonitor = null;
        for (WebElement webElement : monitorsList) {

            int monitorPrice = Integer.parseInt(webElement.getText().replaceAll("\\s+", ""));
            if (monitorPrice < price) {
                selectedMonitor = webElement;
                break;
            }
        }
        try {
            WebElement selectedMonitorParent = selectedMonitor.findElement(By.xpath(".."));
            selectedMonitorParent.findElement(By.xpath("ancestor::*/a[@class = 'goods-tile__picture']")).click();
        } catch (NullPointerException e) {
            System.out.println("NullPointerException is caught");
        }


    }

    private void verifyNamesOfMonitor(Monitor firstMonitor, Monitor secondMonitor) {
        List<String> monitorsFactuelNames = new ArrayList<>();
        monitorsFactuelNames.add(firstMonitor.getMonitorName());
        monitorsFactuelNames.add(secondMonitor.getMonitorName());

        List<String> monitorsExpectedNames = new ArrayList<>();
        List<WebElement> monitorsWebElements = driver.findElements(By.cssSelector("a.product__heading"));
        for (WebElement webElement : monitorsWebElements) {
            monitorsExpectedNames.add(webElement.getText());
        }

        Assert.assertEquals(monitorsFactuelNames, monitorsExpectedNames, "Monitors name are equals");
    }

    private void verifyCountOfMonitor(int comparassionImageCountValue) {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("a.product__heading")));
        List<WebElement> monitorsList = driver.findElements(By.cssSelector("a.product__heading"));

        Assert.assertEquals(comparassionImageCountValue, monitorsList.size(), "Monitor counts are equals");

    }

    private void verifyPricesOfMonitor(Monitor firstMonitor, Monitor secondMonitor) {
        List<Integer> monitorsFactuelPrices = new ArrayList<>();
        monitorsFactuelPrices.add(firstMonitor.getMonitorPrice());
        monitorsFactuelPrices.add(secondMonitor.getMonitorPrice());

        List<Integer> monitorsExpectedPrices = new ArrayList<>();
        List<WebElement> monitorsWebElements = driver.findElements(By.cssSelector("div[class='product__price product__price--red']"));
        for (WebElement webElement : monitorsWebElements) {
            String word = webElement.getText().concat(" ");
            String[] words = word.split("\u20B4\n");
            word = words[1].replaceAll("[^0-9]", "");
            monitorsExpectedPrices.add(Integer.parseInt(word));
        }

        Assert.assertEquals(monitorsFactuelPrices, monitorsExpectedPrices, "Monitor prices are equals");
    }

    private void checkComparisonImage(int comparassionImageCountValue) {
        WebElement comparisonImage = wait.until(ExpectedConditions.presenceOfElementLocated(By.className("header-actions__button-counter")));

        if (comparassionImageCountValue > 1) {
            wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("path[fill='#00a046']")));
            Boolean comparisonImageSecond = wait.until(ExpectedConditions.textToBePresentInElement(comparisonImage, "2"));
            Assert.assertTrue(comparisonImageSecond);
            return;
        }
        int count = Integer.parseInt(comparisonImage.getText());
        Assert.assertEquals(comparassionImageCountValue, count);
    }


}

