package selenium.basic.test.hm3;

import com.github.javafaker.Faker;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.UtilsForHm4;

public abstract class BaseSeleniumTest {

    /*public static final String PATH_TO_PROPERTIES = "src/main/resources/config.properties";
    static  Properties property = new Properties();
    FileInputStream fis;
    protected String sds(String string) throws IOException {
        fis = new FileInputStream(PATH_TO_PROPERTIES);
        property.load(fis);
        *//*String dfd = property.getProperty("login");*//*
        return string;
    }*/

    UtilsForHm4 utilsForHm4;
    protected static final String URL = "https://mail.ru";
    /*protected static final String login = property.getProperty("login");*/
    protected static final String login = "test.lvlup";
    protected static final String pass = "klik_1360";
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
        faker = new Faker();
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofMillis(10000));
        PageFactory.initElements(driver, this);
    }

    @AfterEach
        public void tearDown() {
        driver.quit();
    }
}

