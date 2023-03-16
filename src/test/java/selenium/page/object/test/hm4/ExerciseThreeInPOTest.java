package selenium.page.object.test.hm4;

import java.util.Properties;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.support.PageFactory;
import selenium.basic.test.hm3.BaseSeleniumTest;
import selenium.basic.test.hm3.annotation.AllSeleniumOneTag;
import selenium.page.object.hm4.IndexPage;
import selenium.page.object.hm4.LoginRegistrationPage;
import utils.SleepUtils;
import utils.UtilsForHm4;

public class ExerciseThreeInPOTest extends BaseSeleniumTest {

    private LoginRegistrationPage loginRegistrationPage;
    private IndexPage indexPage;
    private UtilsForHm4 utils;

    public Properties properties;

    @Override
    @BeforeEach
    public void setUp() {
        super.setUp();
        loginRegistrationPage = new LoginRegistrationPage(driver);
        indexPage = new IndexPage(driver);
        utils = new UtilsForHm4(driver);
        PageFactory.initElements(driver, this);

        /*properties = new Properties();
        try {
            properties.load(this.getClass().getResourceAsStream(PATH_TO_PROPERTIES));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }*/
    }

    /*protected String login11 = properties.getProperty("user.name");*/

    @Test
    @AllSeleniumOneTag
    void loginCreateSendYourselfDelete() {

        //этап открытия IndexPage
        loginRegistrationPage.open();
        loginRegistrationPage.clickLoginButton1();
        utils.switchToFrame();
        loginRegistrationPage.fillEmailTextField(login);
        //System.out.println(login11);
        loginRegistrationPage.clickNextButton();
        loginRegistrationPage.fillPassTextField(pass);
        loginRegistrationPage.clickLoginButton2();
        SleepUtils.sleep(1000);
        utils.switchToCurrentPage();



        //этап написания,отправки письма себе и удаления (папка "тест")
        var num = indexPage.getLayoutColumnLeft();
        Assertions.assertThat(num).isEqualTo(7);
        indexPage.clickFolderToMyselfLetter();
        SleepUtils.sleep(1000);
        final var emailsBeforeSendingInToMyself = indexPage.getEmailInToMyself();
        indexPage.clickGarbageButton();
        SleepUtils.sleep(1000);
        final var emailsBeforeDeleteInGarbage = indexPage.getEmailInGarbage();
        indexPage.clickWriteALetterButton();
        indexPage.fillToField(letterForMe);
        indexPage.fillSubjectField(subject);
        indexPage.fillBodyField(bodyLetter);
        indexPage.clickSendButtonInOpenLetter();
        indexPage.clickCloseFrameButton2();
        indexPage.clickIncomingButtonInLayoutColumn();
        indexPage.clickFolderToMyselfLetter();
        SleepUtils.sleep(1000);
        var emailsAfterSendingInToMyself = indexPage.getEmailInToMyself();
        Assertions.assertThat(emailsBeforeSendingInToMyself < emailsAfterSendingInToMyself).isTrue();
        indexPage.clickLastLetterInFolderToMyself();
        SleepUtils.sleep(1000);
        var checkFieldTo = indexPage.getToFieldInsideInPackageTest();
        Assertions.assertThat(checkFieldTo).contains(letterForMe);
        SleepUtils.sleep(1000);
        var checkFieldSubject = indexPage.getSubjectFieldInsideInPackageTest();
        Assertions.assertThat(subject).isEqualTo(checkFieldSubject);
        SleepUtils.sleep(1000);
        var checkFieldBody = indexPage.getBodyFieldInsideInPackageTest();
        Assertions.assertThat(checkFieldBody).contains(bodyLetter);
        indexPage.clickDeleteButton();
        indexPage.clickCloseFrameButtonAfterDeletedLetter();
        indexPage.clickGarbageButton();
        SleepUtils.sleep(1000);
        var emailsAfterDeleteInGarbage = indexPage.getEmailInGarbage();
        Assertions.assertThat(emailsBeforeDeleteInGarbage < emailsAfterDeleteInGarbage).isTrue();
        indexPage.clickLogoutButton1();
        indexPage.clickLogoutButton2();
    }
}
