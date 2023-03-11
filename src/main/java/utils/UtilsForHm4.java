package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class UtilsForHm4 {

    private final WebDriver driver;
    private final WebDriverWait wait;

    /*WebElement frameElement = driver.findElement(By.xpath(
        "//iframe[contains(@class, 'ag-popup__frame__layout__iframe')]"));
    WebDriver frameDriver = driver.switchTo().frame(frameElement);*/

    public UtilsForHm4(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
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



}
