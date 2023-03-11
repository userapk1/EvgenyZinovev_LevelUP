package selenium.page.object.hm4;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;

public class LoginRegistrationPage {

    public static final String URL = "https://mail.ru";
    private final WebDriver driver;
    private final WebDriverWait wait;

    @FindBy(xpath = "//button[contains(@class, 'resplash-btn')]")
    private WebElement loginButton1;
    @FindBy(xpath = "//button[contains(@data-test-id, 'next-button')]")
    private WebElement nextButton;
    @FindBy(xpath = "//*[@id='login-content']//button[@data-test-id='submit-button']")
    private WebElement loginButton2;
    @FindBy(xpath = "//*[@id='login-content']//input[@name='username']")
    private WebElement emailTextField;
    @FindBy(xpath = "//*[@id='login-content']//input[@name='password']")
    private WebElement passTextField;

    public LoginRegistrationPage(final WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public void open() {
        driver.navigate().to(URL);
    }

    public void clickLoginButton1() {
        wait.until(ExpectedConditions.elementToBeClickable(loginButton1)).click();
    }

    public void clickNextButton() {
        wait.until(ExpectedConditions.elementToBeClickable(nextButton)).click();
    }

    public void clickLoginButton2() {
        wait.until(ExpectedConditions.elementToBeClickable(loginButton2)).click();
    }

    public void fillEmailTextField(final String login) {
        wait.until(ExpectedConditions.visibilityOf(emailTextField)).sendKeys(login);
    }

    public void fillPassTextField(final String pass) {
        wait.until(ExpectedConditions.visibilityOf(passTextField)).sendKeys(pass);
    }



}
