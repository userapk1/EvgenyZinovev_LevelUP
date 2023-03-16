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

public abstract class BaseSeleniumTest {

    protected static final String URL = "https://mail.ru";
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
    public static final String PATH_TO_PROPERTIES = "/config.properties";
    /*public Properties properties;*/


    @BeforeEach
        public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        faker = new Faker();
        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofMillis(10000));
        PageFactory.initElements(driver, this);

        /*properties = new Properties();
        try {
            properties.load(this.getClass().getResourceAsStream(PATH_TO_PROPERTIES));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }*/
    }

    /*protected String login11 = properties.getProperty("user.name");*/

    @AfterEach
        public void tearDown() {
        driver.quit();
    }
}

