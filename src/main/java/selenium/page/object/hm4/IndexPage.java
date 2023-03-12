package selenium.page.object.hm4;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class IndexPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    /*@FindBy(xpath = "//div[contains(@class, 'layout__column_left')]//a[contains(@class,"
        + " 'child-level_0')]//div[@class='nav__folder-name__txt']")*/
    private List <WebElement> layoutColumnLeft;

    @FindBy(xpath = "//div[contains(@class, 'application-mail__layout')]//a[contains(@title, 'Отправленные')]")
    private WebElement sentButton;

    @FindBy(xpath = "//*[@href = '/sent/?']")
    private  WebElement emailInSent;

    @FindBy(xpath = "//div[contains(@id, 'sideBarContent')]//a[@title= 'Черновики']")
    private  WebElement draftsButton;

    @FindBy(xpath = "//div[contains(@class, 'ReactVirtualized__Grid')]/div[contains(@class, 'ReactVirtualized__Grid')]/a")
    private  WebElement emailInDrafts;

    @FindBy(xpath = "//*[@class='ico ico_16-compose ico_size_s']")
    private  WebElement writeALetterButton;

    @FindBy(xpath = "//div[contains(@class, 'head_container')]//input[contains(@class, 'container')]")
    private WebElement toField;

    @FindBy(xpath = "//div[contains(@class, 'subject__container')]//input[contains(@class, 'container')]")
    private WebElement subjectField;

    @FindBy(xpath = "//div[contains(@class, 'compose-app_fix')]//div[contains(@class, 'editable')]/div/div[2]")
    private WebElement bodyField;

    @FindBy(xpath = "//div[contains(@class, 'footer')]//button[contains(@data-test-id, 'save')]")
    private WebElement saveButton;

    @FindBy(xpath = "//div[contains(@class, 'compose-app_fix')]//button[@tabindex = '700']")
    private WebElement closeFrameButton;


    public IndexPage(final WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public int getLayoutColumnLeft() {
        return wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By
                         .xpath("//div[contains(@class, 'layout__column_left')]//a[contains(@class,"
                         + " 'child-level_0')]//div[@class='nav__folder-name__txt']"), 3))
                   .stream().map(WebElement::getText).collect(Collectors.toList()).size();
    }
    /*public int getLayoutColumnLeft() {
        return wait.until(ExpectedConditions.visibilityOfAllElements(layoutColumnLeft))
                   .stream().map(WebElement::getText).collect(Collectors.toList()).size();
    }*/

    //пример
  /*  public List<WebElement> IndexPageColumnLeftNumber() {
        return wait.until(ExpectedConditions
            .numberOfElementsToBeMoreThan(By.xpath("//div[contains(@class, 'layout__column_left')]//a[contains(@class,"
                + " 'child-level_0')]//div[@class='nav__folder-name__txt']"), 3));
    }*/

    //переход в отправленные
    public void clickSentButton() {
        wait.until(ExpectedConditions.elementToBeClickable(sentButton)).click();
    }

    public Integer getEmailInSent() {
       return wait.until(ExpectedConditions.visibilityOfAllElements(emailInSent)).size();
    }

    public void clickDraftsButton() {
        wait.until(ExpectedConditions.elementToBeClickable(draftsButton)).click();
    }

    public Dimension getEmailInDrafts() {
        return wait.until(ExpectedConditions.visibilityOf(emailInDrafts)).getSize();
    }

    public void clickWriteALetterButton() {
        wait.until(ExpectedConditions.elementToBeClickable(writeALetterButton)).click();
    }

    //заполняем поле кому
    public void fillToField(final String destination) {
        wait.until(ExpectedConditions.visibilityOf(toField)).sendKeys(destination);
    }

    public void fillSubjectField(final String subject) {
        wait.until(ExpectedConditions.visibilityOf(subjectField)).sendKeys(subject);
    }

    public void fillBodyField(final String body) {
        wait.until(ExpectedConditions.visibilityOf(bodyField)).sendKeys(body);
    }

    public void clickSaveButton() {
        wait.until(ExpectedConditions.elementToBeClickable(saveButton)).click();
    }

    public void clickCloseFrameButton() {
        wait.until(ExpectedConditions.elementToBeClickable(closeFrameButton)).click();
    }



}
