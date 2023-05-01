package allure.jenkins.hm7;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.UtilsForHm4;

public class IndexPage extends UtilsForHm4 {

    private final WebDriver driver;
    private final WebDriverWait wait;

    private UtilsForHm4 utils;


    /*@FindBy(xpath = "//div[contains(@class, 'layout__column_left')]//a[contains(@class,"
            + " 'child-level_0')]//div[@class='nav__folder-name__txt']")*/
    /*private List <WebElement> layoutColumnLeft;*/

    @FindBy(xpath = "//*[@href = '/sent/?']")
    private WebElement sentButtonInLayoutColumn;

    @FindBy(xpath = "//*[@href='/inbox/?']")
    private WebElement incomingButtonInLayoutColumn;

    @FindBy(xpath = "//*[@class='ReactVirtualized__Grid__innerScrollContainer']/a")
    private List<WebElement> numberOfLetters;

    @FindBy(xpath = "//div[contains(@id, 'sideBarContent')]//a[@title= 'Черновики']")
    private  WebElement draftsButtonInLayoutColumn;

    /*@FindBy(xpath = "//div[contains(@class, 'ReactVirtualized__Grid')]
    /div[contains(@class, 'ReactVirtualized__Grid')]/a")
    private  WebElement emailInDrafts;*/

    @FindBy(xpath = "//*[@class='ico ico_16-compose ico_size_s']")
    private  WebElement writeALetterButton;

    @FindBy(xpath = "//div[contains(@class, 'head_container')]//input[contains(@class, 'container')]")
    private WebElement toField;

    @FindBy(xpath = "//div[contains(@class, 'subject__container')]//input[contains(@class, 'container')]")
    private WebElement subjectField;

    @FindBy(xpath = "//div[contains(@class, 'editable')]/div/div[2]")
    private WebElement bodyField;

    @FindBy(xpath = "//div[contains(@class, 'footer')]//button[contains(@data-test-id, 'save')]")
    private WebElement saveButton;

    @FindBy(xpath = "//div[contains(@class, 'compose-app_fix')]//button[@tabindex = '700']")
    private WebElement closeFrameButton;

    @FindBy(xpath = "//div[contains(@class, 'portal-menu-element_back')]/span[@title='Вернуться']")
    private WebElement closeFrameButtonAfterDeletedLetter;
    @FindBy(xpath = "//a[contains(@class,'llc llc_normal llc_first')]//div[contains(@title, 'Сегодня')]")
    private WebElement lastLetter;
    ////div[@class='layout__main-frame']//a[contains(@class,'llc llc_normal llc_first')]

    @FindBy(xpath = "//a[contains(@class,'llc llc_normal llc_first')]")
    private WebElement lastLetterInFolderToMyself;

    @FindBy(xpath = "//div[contains(@class, 'head_container')]//span[contains(@class, 'text')]")
    private WebElement toFieldInside;
    @FindBy(xpath = "//div[contains(@class, 'letter__author')]//span[@class='letter-contact']")
    private WebElement toFieldInsideInPackageTest;

    @FindBy(xpath = "//div[contains(@class, 'thread__subject-line')]/h2[@class='thread-subject']")
    private WebElement subjectFieldInsideInPackageTest;

    @FindBy(xpath = "//div[@class = 'letter-body__body-wrapper']")
    private WebElement bodyFieldInsideInPackageTest;

    @FindBy(xpath = "//div[contains(@class, 'subject__container')]//input[contains(@class, 'container')]")
    private WebElement subjectFieldInside;

    @FindBy(xpath = "//*[@role = 'textbox']")
    private WebElement bodyFieldInside;

    @FindBy(xpath = "//div[contains(@class, 'footer')]//button[contains(@data-test-id, 'send')]")
    private WebElement sentButtonInOpenLetter;

    @FindBy(xpath = "//div[@class='ReactVirtualized__Grid__innerScrollContainer']"
        + "//div[@class='metathread__contain']/div/a")
    private WebElement folderToMyselfLetter;

    @FindBy(xpath = "//div[contains(@class, 'layer layer')]//span[@class='button2__wrapper button2__wrapper_centered']")
    private WebElement closeFrameButton2;

    @FindBy(xpath = "//div[contains(@class, 'ph-auth')]//div[contains(@class, 'ph-project__account')]")
    private WebElement logoutButton1;

    @FindBy(xpath = "//div[contains(@class, 'ph-item__hover-active')]/div[contains(@class, 'ph-text')]")
    private WebElement logoutButton2;

    @FindBy(xpath = "//div[contains(@class, 'sidebar__header')]//span[@class='sidebar__menu-ico']")
    private WebElement leftSideMenu;

    @FindBy(xpath = "//nav[contains(@class, 'nav nav_expanded nav_hover-support')]//a[contains(@title, 'Тест')]")
    private WebElement testButtonInLayoutColumn;

    @FindBy(xpath = "//a[@href='/trash/?']")
    private WebElement garbageButton;

    @FindBy(xpath = "//div[contains(@class, 'portal-menu-element_remove')]/span[@title='Удалить']")
    private WebElement deleteButton;


    public IndexPage(final WebDriver driver) {
        super(driver);
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.utils = new UtilsForHm4(driver);
        PageFactory.initElements(driver, this);
    }

    public int getLayoutColumnLeft() {
        return wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By
                         .xpath("//div[contains(@class, 'layout__column_left')]//a[contains(@class,"
                         + " 'child-level_0')]//div[@class='nav__folder-name__txt']"), 3))
                   .stream().map(WebElement::getText).collect(Collectors.toList()).size();
    }

    //переход в отправленные
    public void clickSentButtonInLayoutColumn() {
        click(sentButtonInLayoutColumn);
    }

    public Integer getEmailInSent() {
        return amountOfElements(numberOfLetters);
    }

    public void clickDraftsButtonInLayoutColumn() {
        click(draftsButtonInLayoutColumn);
    }

    public Integer getEmailInDrafts() {
        return amountOfElements(numberOfLetters);
    }

    public Integer getEmailInTests() {
        return amountOfElements(numberOfLetters);
    }

    public Integer getEmailInToMyself() {
        return amountOfElements(numberOfLetters);
    }

    public Integer getEmailInGarbage() {
        return amountOfElements(numberOfLetters);
    }

    public void clickWriteALetterButton() {
        click(writeALetterButton);
    }
    //заполняем поле кому

    public void fillToField(final String destination) {
        sendKeys(toField, destination);
    }

    public void fillSubjectField(final String subject) {
        sendKeys(subjectField, subject);
    }

    public void fillBodyField(final String body) {
        sendKeys(bodyField, body);
    }

    public void clickSaveButton() {
        click(saveButton);
    }

    public void clickCloseFrameButton() {
        click(closeFrameButton);
    }

    public void clickLastLetter() {
        click(lastLetter);
    }

    public String getToFieldInside() {
        return getText(toFieldInside);
    }

    public String getSubjectFieldInside() {
        return getAttribute(subjectFieldInside, "value");
    }

    public String getBodyFieldInside() {
        return getText(bodyFieldInside);
    }

    public void clickSendButtonInOpenLetter() {
        click(sentButtonInOpenLetter);
    }

    public void clickCloseFrameButton2() {
        click(closeFrameButton2);
    }

    public void clickLogoutButton1() {
        click(logoutButton1);
    }

    public void clickLogoutButton2() {
        click(logoutButton2);
    }

    public void clickLeftSideMenu() {
        click(leftSideMenu);
    }

    public void clickTestButtonInLayoutColumn() {
        click(testButtonInLayoutColumn);
    }

    public String getSubjectFieldInsideInPackageTest() {
        return getText(subjectFieldInsideInPackageTest);
    }

    public String getToFieldInsideInPackageTest() {
        return getAttribute(toFieldInsideInPackageTest, "title");
    }

    public String getBodyFieldInsideInPackageTest() {
        return getText(bodyFieldInsideInPackageTest);
    }

    public void clickFolderToMyselfLetter() {
        click(folderToMyselfLetter);
    }

    public void clickGarbageButton() {
        click(garbageButton);
    }

    public void clickIncomingButtonInLayoutColumn() {
        click(incomingButtonInLayoutColumn);
    }

    public void clickLastLetterInFolderToMyself() {
        click(lastLetterInFolderToMyself);
    }

    public void clickDeleteButton() {
        click(deleteButton);
    }

    public void clickCloseFrameButtonAfterDeletedLetter() {
        click(closeFrameButtonAfterDeletedLetter);
    }

}
