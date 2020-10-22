package PageFactoryObjectPages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RozetkaHomePageFactoryPage {
    WebDriver webdriver;
    WebDriverWait wait;

    public RozetkaHomePageFactoryPage(WebDriver webdriver) {
        this.webdriver = webdriver;
        this.wait = new WebDriverWait(webdriver, 15);
        PageFactory.initElements(webdriver, this);
    }
    @FindBy(linkText = "Ноутбуки и компьютеры")
    WebElement MonitorsAndNotebooksGroup;
    @FindBy(partialLinkText = "Мониторы")
    WebElement MonitorsGroup;
    @FindBy( css = "input[name='search']")
    WebElement searchByProductName;

    public WebElement waitForHoverPopup() {
        return MonitorsAndNotebooksGroup;
    }

    public void selectMonitorsAndNotebooksGroup() {
        Actions action = new Actions(webdriver);
        action.moveToElement(waitForHoverPopup()).build().perform();
    }

    public void selectMonitorsGroup() {
        wait.until(ExpectedConditions.visibilityOf(MonitorsGroup)).click();
    }
    public void searchByField(){
        wait.until(ExpectedConditions.visibilityOf(searchByProductName)).sendKeys("samsung");
        searchByProductName.sendKeys(Keys.ENTER);
    }
}
