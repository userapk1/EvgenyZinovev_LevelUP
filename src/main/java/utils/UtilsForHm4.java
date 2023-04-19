package utils;

import java.time.Duration;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class UtilsForHm4 {

    /*protected*/public final WebDriver driver;
    /*protected*/public final WebDriverWait wait;

    /*WebElement frameElement = driver.findElement(By.xpath(
        "//iframe[contains(@class, 'ag-popup__frame__layout__iframe')]"));
    WebDriver frameDriver = driver.switchTo().frame(frameElement);*/

    public UtilsForHm4(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public void switchToFrame() {
        WebElement frameElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
            "//iframe[contains(@class, 'ag-popup__frame__layout__iframe')]")));
        WebDriver frameDriver = driver.switchTo().frame(frameElement);
    }

    public void switchToCurrentPage() {
        var currentHandle = driver.getWindowHandle();
        driver.switchTo().window(currentHandle);
    }

    protected void click(final WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element)).click();
    }

    protected void sendKeys(final WebElement element, String text) {
        wait.until(ExpectedConditions.visibilityOf(element)).sendKeys(text);
    }

    protected Integer amountOfElements(List<WebElement> element) {
        return wait.until(ExpectedConditions.visibilityOfAllElements(element)).size();
    }

    protected String getText(WebElement element) {
        return wait.until(ExpectedConditions.visibilityOf(element)).getText();
    }

    protected String getAttribute(WebElement element, String name) {
        return wait.until(ExpectedConditions.visibilityOf(element)).getAttribute(name);
    }


}
