package selenium.basic.test.hm3.exercise;

import java.util.List;
import java.util.stream.Collectors;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import selenium.basic.test.hm3.BaseSeleniumTest;
import selenium.basic.test.hm3.annotation.AllSeleniumOneTag;
import utils.SleepUtils;

public class ExerciseTwo extends BaseSeleniumTest {

    @Test
    @AllSeleniumOneTag
    void loginCreateSendYourself() {
        driver.navigate().to(URL);

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
            "//button[contains(@class, 'resplash-btn')]"))).click();

        WebElement frameElement = driver.findElement(By.xpath(
            "//iframe[contains(@class, 'ag-popup__frame__layout__iframe')]"));
        WebDriver frameDriver = driver.switchTo().frame(frameElement);

        wait.until(ExpectedConditions.visibilityOfElementLocated(By
                .xpath("//*[@id='login-content']//input[@name='username']")))
            .sendKeys(login);

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
            "//button[contains(@data-test-id, 'next-button')]"))).click();


        wait.until(ExpectedConditions.visibilityOfElementLocated(By
                .xpath("//*[@id='login-content']//input[@name='password']")))
            .sendKeys(pass);


        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
            "//*[@id='login-content']//button[@data-test-id='submit-button']"))).click();

        var currentHandle = driver.getWindowHandle();
        driver.switchTo().window(currentHandle);

        //проверяем шо ход выполнен
        List<WebElement> searchList = wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By
            .xpath("//div[contains(@class, 'layout__column_left')]//a[contains(@class,"
                + " 'child-level_0')]//div[@class='nav__folder-name__txt']"), 3));

        Assertions.assertThat(searchList.stream().map(WebElement::getText).collect(Collectors.toList()))
                  .hasSize(7);

        //чек скока записей в Тест
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
            "//div[contains(@class, 'sidebar__header')]//span[@class='sidebar__menu-ico']"))).click();

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
            "//nav[contains(@class, 'nav nav_expanded nav_hover-support')]//a[contains(@title, 'Тест')]"))).click();

        final int emailsInTest = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(
            "//*[@class='ReactVirtualized__Grid__innerScrollContainer']/a"))).size();
        //System.out.println(emailsInTest);

        //чекаем скока писем в отправленных
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
            "//div[contains(@class, 'application-mail__layout')]//a[contains(@title, 'Отправленные')]"))).click();

        final int emailsInSent = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(
            "//*[@class='ReactVirtualized__Grid__innerScrollContainer']/a"))).size();
        //System.out.println(emailsInSent);

        //нажимаем написать письмо
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
            "//*[@class='ico ico_16-compose ico_size_s']"))).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By
                .xpath("//div[contains(@class, 'head_container')]//input[contains(@class, 'container')]")))
            .sendKeys(letterForMe);

        wait.until(ExpectedConditions.visibilityOfElementLocated(By
                .xpath("//div[contains(@class, 'subject__container')]//input[contains(@class, 'container')]")))
            .sendKeys(subjectForMe);

        wait.until(ExpectedConditions.visibilityOfElementLocated(By
                .xpath("//div[contains(@class, 'editable')]/div/div[2]")))
            .sendKeys(bodyLetter);

        //отправили и закрыли фрейм
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
            "//div[contains(@class, 'footer')]//button[contains(@data-test-id, 'send')]"))).click();
        //close frame
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
            "//div[contains(@class, 'layer layer')]//span[@class='button2__wrapper button2__wrapper_centered']")))
            .click();

        int emailsInSent2 = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(
            "//*[@class='ReactVirtualized__Grid__innerScrollContainer']/a"))).size();
        Assertions.assertThat(emailsInSent < emailsInSent2).isTrue();

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
            "//div[contains(@class, 'sidebar__header')]//span[@class='sidebar__menu-ico']"))).click();

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
            "//nav[contains(@class, 'nav nav_expanded nav_hover-support')]//a[contains(@title, 'Тест')]"))).click();

        int emailsInTest2 = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(
            "//*[@class='ReactVirtualized__Grid__innerScrollContainer']/a"))).size();
        Assertions.assertThat(emailsInTest < emailsInTest2).isTrue();

        //открываем письмо в папке тест
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
            "//div[@class='layout__main-frame']//a[contains(@class,'llc llc_normal llc_first')]"))).click();

        //проверяяем соответствие полей
        String checkFieldTo = wait.until(ExpectedConditions.visibilityOfElementLocated(By
                                      .xpath("//div[contains(@class, 'letter__author')]"
                                          + "//span[@class='letter-contact']")))
                                       .getAttribute("title");
        Assertions.assertThat(letterForMe).isEqualTo(checkFieldTo);

        String checkFieldSubject = wait.until(ExpectedConditions.visibilityOfElementLocated(By
            .xpath("//div[contains(@class, 'thread__subject-line')]/h2[@class='thread-subject']"))).getText();
        Assertions.assertThat(subjectForMe).isEqualTo(checkFieldSubject);

        String checkFieldBody = wait.until(ExpectedConditions.visibilityOfElementLocated(By
            .xpath("//*[@role = 'textbox']")))
                                    .getText();
        Assertions.assertThat(checkFieldBody).contains(bodyLetter);

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
            "//div[contains(@class, 'ph-auth')]//div[contains(@class, 'ph-project__account')]"))).click();

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
            "//div[contains(@class, 'ph-item__hover-active')]/div[contains(@class, 'ph-text')]"))).click();


    }
}
