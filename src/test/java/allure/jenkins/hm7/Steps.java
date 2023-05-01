package allure.jenkins.hm7;

import io.qameta.allure.Step;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import utils.SleepUtils;
import utils.UtilsForHm4;

public class Steps extends BasePage{

    private final IndexPage indexPage;
    private final LoginRegistrationPage loginRegistrationPage;
    private final UtilsForHm4 utils;

    public Steps(WebDriver driver) {
        this.indexPage = new IndexPage(driver);
        this.loginRegistrationPage = new LoginRegistrationPage(driver);
        this.utils = new UtilsForHm4(driver);
        PageFactory.initElements(driver, this);
    }

    @Step("Открытие домашней страницы")
    public void openHomePage() {
        loginRegistrationPage.open();
    }

    @Step("Авторизация")
    public void logIn(String log, String password) {
        loginRegistrationPage.clickLoginButton1();
        utils.switchToFrame();
        loginRegistrationPage.fillEmailTextField(log);
        loginRegistrationPage.clickNextButton();
        loginRegistrationPage.fillPassTextField(password);
        loginRegistrationPage.clickLoginButton2();
        SleepUtils.sleep(1000);
        utils.switchToCurrentPage();
    }

    @Step("Проверка авторизации на успех")
    public void checkOfSuccessAuthorization() {
        var num = indexPage.getLayoutColumnLeft();
        Assertions.assertThat(num).isEqualTo(7);
        SleepUtils.sleep(1000);
    }

    @Step("Логаут")
    public void logOut() {
        indexPage.clickLogoutButton1();
        indexPage.clickLogoutButton2();
    }

    //сценарий1
    @Step("Формирование, сохранение письма, и проверка сохранения черновика")
    public void formingAndSavingADraft(String destination, String subject, String bodyLetter) {
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
        /*System.out.println(emailsBeforeSaveInDrafts);
        System.out.println(emailsAfterSaveInDrafts);*/
        Assertions.assertThat(emailsBeforeSaveInDrafts < emailsAfterSaveInDrafts).isTrue();
    }

    @Step("Проверка на соответствие полей 'Кому', 'Тема' и тела письма")
    public void checkOfFilledFieldsInScenarioOne(String destination, String subject, String bodyLetter) {
        indexPage.clickLastLetter();
        var checkFieldTo = indexPage.getToFieldInside();
        Assertions.assertThat(destination).isEqualTo(checkFieldTo);
        var checkFieldSubject = indexPage.getSubjectFieldInside();
        Assertions.assertThat(subject).isEqualTo(checkFieldSubject);
        SleepUtils.sleep(1000);
        var checkFieldBody = indexPage.getBodyFieldInside();
        Assertions.assertThat(checkFieldBody).contains(bodyLetter);
        SleepUtils.sleep(1000);
    }

    @Step("Отправка письма")
    public void sendingLetter() {
        indexPage.clickSendButtonInOpenLetter();
        indexPage.clickCloseFrameButton2();
        SleepUtils.sleep(1000);
    }

    //сценарий2
    @Step("Формирование, отправка письма себе, и проверка отправления письма")
    public void formingAndSendingLetterToYourselfForScenarioTwo(String letterForMe, String subjectForMe, String bodyLetter) {
        indexPage.clickLeftSideMenu();
        indexPage.clickTestButtonInLayoutColumn();
        SleepUtils.sleep(1000);
        final var emailsBeforeSendingInTest = indexPage.getEmailInTests();
        indexPage.clickSentButtonInLayoutColumn();
        SleepUtils.sleep(1000);
        final var emailsBeforeSendingInSent = indexPage.getEmailInSent();
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
    }

    @Step("Проверка на соответствие полей 'Кому', 'Тема' и тела письма")
    public void checkOfFilledFieldsInScenarioTwo(String letterForMe, String subjectForMe, String bodyLetter) {
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
    }

    //сценарий3
    @Step("Формирование, отправка письма себе, и проверка отправления письма")
    public void formingAndSendingLetterToYourselfForScenarioThree(String letterForMe, String subject, String bodyLetter) {
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
    }

    @Step("Проверка на соответствие полей 'Кому', 'Тема' и тела письма")
    public void checkOfFilledFieldsInScenarioThree(String letterForMe, String subjectForMe, String bodyLetter) {
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
    }

    @Step("Удаление письма")
    public void deleteLetter(String letterForMe, String subjectForMe, String bodyLetter) {
        indexPage.clickDeleteButton();
        indexPage.clickCloseFrameButtonAfterDeletedLetter();
        indexPage.clickGarbageButton();
        SleepUtils.sleep(1000);
    }

}
