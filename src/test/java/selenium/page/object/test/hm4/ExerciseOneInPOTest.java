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

        var num = indexPage.getLayoutColumnLeft();
        Assertions.assertThat(num).isEqualTo(7);
        SleepUtils.sleep(1000);
        indexPage.clickSentButton();
        SleepUtils.sleep(1000);
        var emailsBeforeSending = indexPage.getEmailInSent();
        SleepUtils.sleep(1000);
        System.out.println(emailsBeforeSending);
        /*var emailsBeforeSending = Integer.parseInt(String.valueOf(indexPage.getEmailInSent()));*//*
        indexPage.clickDraftsButton();
        SleepUtils.sleep(1000);
        var emailsBeforeSave = Integer.parseInt(String.valueOf(indexPage.getEmailInDrafts()));
        indexPage.clickWriteALetterButton();
        indexPage.fillToField(destination);
        indexPage.fillSubjectField(subject);
        indexPage.fillBodyField(bodyLetter);
        indexPage.clickSaveButton();
        indexPage.clickCloseFrameButton();
        var emailsAfterSave = Integer.parseInt(String.valueOf(indexPage.getEmailInDrafts()));
        Assertions.assertThat(emailsBeforeSave < emailsAfterSave);
        System.out.println(emailsBeforeSave);
        System.out.println(emailsAfterSave);*/



    }
}
