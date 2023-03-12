package utils;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class UtilsForHm4 {

    private final WebDriver driver;
    private final WebDriverWait wait;

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

    /*protected Integer getElement(final WebElement element) {
        return wait.until(ExpectedConditions.visibilityOf(element)).getSize();
    }*/



}
