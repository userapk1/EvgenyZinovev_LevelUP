package allure.jenkins.hm7.exercise;

import allure.jenkins.hm7.BasePage;
import allure.jenkins.hm7.Steps;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@Feature("Сохранение в черновики")
@DisplayName("Сценарий один")
public class OneTest extends BasePage {

    private Steps steps;

    @Override
    @BeforeEach
    public void setUp() {
        super.setUp();
        steps = new Steps(driver);
    }

    @Test
    void loginCreateSaveToDraftSend() {
        steps.openHomePage();
        steps.checkOfSuccessAuthorization();
    }
}
