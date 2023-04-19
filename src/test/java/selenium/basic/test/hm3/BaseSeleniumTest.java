package selenium.basic.test.hm3;

import com.github.javafaker.Faker;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
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
    protected static final String URL = "https://mail.ru";
    protected static final String login = "test.lvlup";
    protected final String login11 = ConfigProvider.staticVeriables().getLogin();
    protected static final String pass = "klik_1360";
    protected static final String destination = "test@mail.ru";
    protected static final String letterForMe = "test.lvlup@mail.ru";
    protected static final String subject = "test";
    protected static final String subjectForMe = "Тест";
    protected static final String bodyLetter = "testBody";
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected Faker faker;
    /*public static final String PATH_TO_PROPERTIES = "/config.properties";*/


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

