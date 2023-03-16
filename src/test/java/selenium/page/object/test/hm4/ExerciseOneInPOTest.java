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
        PageFactory.initElements(driver, this);
    }

    @Test
    @AllSeleniumOneTag
    void loginCreateSaveToDraftSend() {

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

        //этап написания и сохранения письма
        var num = indexPage.getLayoutColumnLeft();
        Assertions.assertThat(num).isEqualTo(7);
        SleepUtils.sleep(1000);
        indexPage.clickSentButtonInLayoutColumn();
        SleepUtils.sleep(1000);
        final var emailsBeforeSendingInSent = indexPage.getEmailInSent();
        indexPage.clickDraftsButtonInLayoutColumn();
        SleepUtils.sleep(1000);
        final var emailsBeforeSaveInDrafts = indexPage.getEmailInDrafts();
        indexPage.clickWriteALetterButton();
        indexPage.fillToField(destination);
        indexPage.fillSubjectField(subject);
        indexPage.fillBodyField(bodyLetter);
        indexPage.clickSaveButton();
        indexPage.clickCloseFrameButton();
        SleepUtils.sleep(1000);
        var emailsAfterSaveInDrafts = indexPage.getEmailInDrafts();
        System.out.println(emailsBeforeSaveInDrafts);
        System.out.println(emailsAfterSaveInDrafts);
        Assertions.assertThat(emailsBeforeSaveInDrafts < emailsAfterSaveInDrafts).isTrue();

        //этап отправки письма
        indexPage.clickLastLetter();
        var checkFieldTo = indexPage.getToFieldInside();
        Assertions.assertThat(destination).isEqualTo(checkFieldTo);
        var checkFieldSubject = indexPage.getSubjectFieldInside();
        Assertions.assertThat(subject).isEqualTo(checkFieldSubject);
        SleepUtils.sleep(1000);
        var checkFieldBody = indexPage.getBodyFieldInside();
        Assertions.assertThat(checkFieldBody).contains(bodyLetter);
        SleepUtils.sleep(1000);
        indexPage.clickSendButtonInOpenLetter();
        indexPage.clickCloseFrameButton2();
        SleepUtils.sleep(1000);
        var emailsAfterSendingInDrafts = indexPage.getEmailInDrafts();
        Assertions.assertThat(emailsAfterSaveInDrafts > emailsAfterSendingInDrafts).isTrue();
        indexPage.clickSentButtonInLayoutColumn();
        SleepUtils.sleep(1000);
        var emailsAfterSendingInSent = indexPage.getEmailInSent();
        Assertions.assertThat(emailsBeforeSendingInSent < emailsAfterSendingInSent).isTrue();
        indexPage.clickLogoutButton1();
        indexPage.clickLogoutButton2();
    }
}
