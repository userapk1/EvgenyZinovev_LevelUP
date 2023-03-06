package selenium.basic.test.hm3;

import com.github.javafaker.Faker;
import java.time.Duration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
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


        @BeforeEach
        public void setUp() {
            faker = new Faker();
            driver = new ChromeDriver();
            wait = new WebDriverWait(driver, Duration.ofMillis(10000));
        }

        @AfterEach
        public void tearDown() {
            driver.quit();
        }
    }

