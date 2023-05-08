package allure.jenkins.hm7;

import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@Feature("Сценарии в портале mail.ru")
@DisplayName("Четыре сценария с формированием, сохранением, отправкой, удалением письма и неудачной авторизацией")
public class SeleniumStepDesignTest extends BasePage{
    private Steps steps;

    @Override
    @BeforeEach
    public void setUp() {
        super.setUp();
        steps = new Steps(driver);
    }

    @Test
    @Story("Создание письма, сохранение в Черновики и его отправка")
    void loginCreateSaveToDraftSend() {
        steps.openHomePage();
        steps.logIn(login, pass);
        steps.checkOfSuccessAuthorization();
        steps.formingAndSavingADraft(destination,subject,bodyLetter);
        steps.checkOfFilledFieldsInScenarioOne(destination, subject, bodyLetter);
        steps.sendingLetter();
        steps.logOut();
    }

    @Test
    @Story("Создание письма, отправка его себе(перемещение в папку 'Тест')")
    void loginCreateSendYourself() {
        steps.openHomePage();
        steps.logIn(login, pass);
        steps.checkOfSuccessAuthorization();
        steps.formingAndSendingLetterToYourselfForScenarioTwo(letterForMe, subjectForMe, bodyLetter);
        steps.checkOfFilledFieldsInScenarioTwo(letterForMe, subjectForMe, bodyLetter);
        steps.logOut();
    }

    @Test
    @Story("Создание письма, отправка его себе и удаление")
    void loginCreateSendYourselfDelete() {
        steps.openHomePage();
        steps.logIn(login, pass);
        steps.checkOfSuccessAuthorization();
        steps.formingAndSendingLetterToYourselfForScenarioThree(letterForMe,subject, bodyLetter);
        steps.checkOfFilledFieldsInScenarioThree(letterForMe, subject, bodyLetter);
        steps.deleteLetter();
        steps.logOut();
    }

    @Test
    @Story("Неудачная авторизация")
    void failedAuthorization() {
        steps.openHomePage();
        steps.logIn(login+1, pass);
    }
}
