package selenium.page.object.hm4;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

public class IndexPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    @FindBy(xpath = "//div[contains(@class, 'layout__column_left')]//a[contains(@class,"
        + " 'child-level_0')]//div[@class='nav__folder-name__txt']")
    private List <WebElement> indexPageColumnLeft;

    public IndexPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

  /*  public List<WebElement> IndexPageColumnLeftNumber() {
        return wait.until(ExpectedConditions
            .numberOfElementsToBeMoreThan(By.xpath("//div[contains(@class, 'layout__column_left')]//a[contains(@class,"
                + " 'child-level_0')]//div[@class='nav__folder-name__txt']"), 3));
    }*/
}
