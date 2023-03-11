package selenium.page.object.test.hm4;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;
import selenium.basic.test.hm3.BaseSeleniumTest;
import selenium.basic.test.hm3.annotation.AllSeleniumOneTag;
import selenium.page.object.hm4.IndexPage;
import selenium.page.object.hm4.LoginRegistrationPage;
import utils.SleepUtils;
import utils.UtilsForHm4;
import java.io.IOException;
import java.util.stream.Collectors;

public class ExerciseOneInPOTest extends BaseSeleniumTest {

   private LoginRegistrationPage loginRegistrationPage;

   private IndexPage indexPage;
   private UtilsForHm4 utils;

    @Override
    @BeforeEach
    public void setUp() {
        super.setUp();
        loginRegistrationPage = new LoginRegistrationPage(driver);
        indexPage = new IndexPage(driver);
        utils = new UtilsForHm4(driver);
    }

    @Test
    @AllSeleniumOneTag
    void loginCreateSaveToDraftSend() throws IOException {

        loginRegistrationPage.open();
        loginRegistrationPage.clickLoginButton1();
        utils.switchToFrame();
        /*loginRegistrationPage.fillEmailTextField(sds(login));*/
        loginRegistrationPage.fillEmailTextField(login);
        loginRegistrationPage.clickNextButton();
        loginRegistrationPage.fillPassTextField(pass);
        loginRegistrationPage.clickLoginButton2();
        SleepUtils.sleep(2000);
        utils.switchToCurrentPage();

        /*var actualNumbOfElementsInColumnLeft = indexPage.IndexPageColumnLeftNumber();
        Assertions.assertThat(actualNumbOfElementsInColumnLeft.stream().map(WebElement::getText).collect(Collectors.toList())).hasSize(7);*/


    }
}
