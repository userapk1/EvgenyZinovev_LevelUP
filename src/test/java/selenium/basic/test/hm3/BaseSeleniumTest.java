package selenium.basic.test.hm3;

import com.github.javafaker.Faker;
import java.time.Duration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import selenium.page.object.hm4.configuration.ConfigProvider;

public abstract class BaseSeleniumTest {
    /*private static Properties properties;*/
    protected static final String URL = ConfigProvider.staticVeriables().getURL();
    protected final String login = ConfigProvider.staticVeriables().getLogin();
    protected static final String pass = ConfigProvider.staticVeriables().getPass();
    protected static final String destination = ConfigProvider.staticVeriables().getDestination();
    protected static final String letterForMe = ConfigProvider.staticVeriables().getLetterForMe();
    protected static final String subject = ConfigProvider.staticVeriables().getSubject();
    protected static final String subjectForMe = ConfigProvider.staticVeriables().getSubjectForMe();
    protected static final String bodyLetter = ConfigProvider.staticVeriables().getBodyLetter();
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

    }


    @AfterEach
        public void tearDown() {
        driver.quit();
    }
}

