package selenium.page.object.test.hm4;

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

public class ExerciseTwoInPOTest extends BaseSeleniumTest {

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
        PageFactory.initElements(driver, this);
    }

    @Test
    @AllSeleniumOneTag
    void loginCreateSendYourself() {

        //этап открытия IndexPage
        loginRegistrationPage.open();
        loginRegistrationPage.clickLoginButton1();
        utils.switchToFrame();
        /*loginRegistrationPage.fillEmailTextField(sds(login));*/
        loginRegistrationPage.fillEmailTextField(login);
        loginRegistrationPage.clickNextButton();
        loginRegistrationPage.fillPassTextField(pass);
        loginRegistrationPage.clickLoginButton2();
        SleepUtils.sleep(1000);
        utils.switchToCurrentPage();

        //этап написания и отправки письма себе (папка "тест")
        var num = indexPage.getLayoutColumnLeft();
        Assertions.assertThat(num).isEqualTo(7);
        indexPage.clickLeftSideMenu();
        indexPage.clickTestButtonInLayoutColumn();
        SleepUtils.sleep(1000);
        var emailsBeforeSendingInTest = indexPage.getEmailInTests();
        indexPage.clickSentButtonInLayoutColumn();
        SleepUtils.sleep(1000);
        var emailsBeforeSendingInSent = indexPage.getEmailInSent();
        indexPage.clickWriteALetterButton();
        indexPage.fillToField(letterForMe);
        indexPage.fillSubjectField(subjectForMe);
        indexPage.fillBodyField(bodyLetter);
        indexPage.clickSendButtonInOpenLetter();
        indexPage.clickCloseFrameButton2();
        SleepUtils.sleep(1000);
        var emailsAfterSendingInSent = indexPage.getEmailInSent();
        Assertions.assertThat(emailsBeforeSendingInSent < emailsAfterSendingInSent).isTrue();
        indexPage.clickLeftSideMenu();
        indexPage.clickTestButtonInLayoutColumn();
        SleepUtils.sleep(1000);
        var emailsAfterSendingInTest = indexPage.getEmailInTests();
        Assertions.assertThat(emailsBeforeSendingInTest < emailsAfterSendingInTest).isTrue();
        indexPage.clickLastLetter();
        SleepUtils.sleep(1000);
        var checkFieldTo = indexPage.getToFieldInsideInPackageTest();
        Assertions.assertThat(checkFieldTo).contains(letterForMe);
        SleepUtils.sleep(1000);
        var checkFieldSubject = indexPage.getSubjectFieldInsideInPackageTest();
        Assertions.assertThat(subjectForMe).isEqualTo(checkFieldSubject);
        SleepUtils.sleep(1000);
        var checkFieldBody = indexPage.getBodyFieldInsideInPackageTest();
        Assertions.assertThat(checkFieldBody).contains(bodyLetter);
        indexPage.clickLogoutButton1();
        indexPage.clickLogoutButton2();

    }
}
