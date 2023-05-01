package allure.jenkins.hm7;

import allure.jenkins.hm7.listener.AllureAttachmentReport;
import allure.jenkins.hm7.listener.TestContext;
import com.github.javafaker.Faker;
import java.time.Duration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import selenium.page.object.hm4.configuration.ConfigProvider;

@ExtendWith({AllureAttachmentReport.class})
public abstract class BasePage {

    protected static final String URL = "https://mail.ru";
    protected final String login = ConfigProvider.staticVeriables().getLogin();
    protected static final String pass = ConfigProvider.staticVeriables().getPass();
    protected static final String destination = "test@mail.ru";
    protected static final String letterForMe = "test.lvlup@mail.ru";
    protected static final String subject = "test";
    protected static final String subjectForMe = "Тест";
    protected static final String bodyLetter = "testBody";
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected Faker faker;

    @BeforeEach
        public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        faker = new Faker();
        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofMillis(10000));
        PageFactory.initElements(driver, this);
        TestContext.getInstance().addObject("driver", driver);
    }

    @AfterEach
        public void tearDown() {
        driver.quit();
        TestContext.clear();
    }
}

