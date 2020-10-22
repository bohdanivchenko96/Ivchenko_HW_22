package PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RozetkaHomePage {
    WebDriver webdriver;
    WebDriverWait wait;

    public RozetkaHomePage(WebDriver webdriver) {
        this.webdriver = webdriver;
        this.wait = new WebDriverWait(webdriver, 15);
    }

    By MonitorsAndNotebooksGroup = By.linkText("Ноутбуки и компьютеры");
    By MonitorsGroup = By.partialLinkText("Мониторы");
    By searchByProductName = By.name("search");

    public WebElement waitForHoverPopup() {
        return webdriver.findElement(MonitorsAndNotebooksGroup);
    }

    public void selectMonitorsAndNotebooksGroup() {
        Actions action = new Actions(webdriver);
        action.moveToElement(waitForHoverPopup()).build().perform();
    }

    public void selectMonitorsGroup() {
        wait.until(ExpectedConditions.presenceOfElementLocated(MonitorsGroup)).click();
    }

    public void searchByField(){
        wait.until(ExpectedConditions.presenceOfElementLocated(searchByProductName)).sendKeys("samsung");
        webdriver.findElement(searchByProductName).sendKeys(Keys.ENTER);
    }
}
