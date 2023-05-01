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
}
