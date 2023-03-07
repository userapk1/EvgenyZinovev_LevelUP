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


public class ExeciseThree extends BaseSeleniumTest {

    @Test
    @AllSeleniumOneTag
    void loginCreateSendYourselfDelete() {
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

        SleepUtils.sleep(2000);
        var currentHandle = driver.getWindowHandle();
        driver.switchTo().window(currentHandle);

        //проверяем шо ход выполнен
        SleepUtils
            .sleep(2000);
        List<WebElement> searchList = wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By
            .xpath("//div[contains(@class, 'layout__column_left')]//a[contains(@class,"
                + " 'child-level_0')]//div[@class='nav__folder-name__txt']"), 3));

        Assertions.assertThat(searchList.stream().map(WebElement::getText).collect(Collectors.toList()))
                  .hasSize(7);

        //чек скока писем во вх себе
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
            "//div[@class='ReactVirtualized__Grid__innerScrollContainer']//div[@class='metathread__contain']/div/a")))
            .click();

        SleepUtils.sleep(2000);
        int emailsInIncoming;
        emailsInIncoming = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(
            "//div[@class='ReactVirtualized__Grid__innerScrollContainer']/a"))).size();

        //check garbage
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
            "//div[contains(@class, 'application-mail__layout')]//a[contains(@title, 'Корзина')]"))).click();

        SleepUtils.sleep(2000);
        int emailsInGarbage;
        emailsInGarbage = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(
            "//div[@class='ReactVirtualized__Grid__innerScrollContainer']/a"))).size();

        //нажимаем написать письмо
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
            "//span[@class='compose-button__ico']//*[@class='ico ico_16-compose ico_size_s']"))).click();

        SleepUtils.sleep(2000);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By
                .xpath("//div[contains(@class, 'head_container')]//input[contains(@class, 'container')]")))
            .sendKeys(letterForMe);

        wait.until(ExpectedConditions.visibilityOfElementLocated(By
                .xpath("//div[contains(@class, 'subject__container')]//input[contains(@class, 'container')]")))
            .sendKeys(subject);

        wait.until(ExpectedConditions.visibilityOfElementLocated(By
                .xpath("//div[contains(@class, 'compose-app_fix')]//div[contains(@class, 'editable')]/div/div[2]")))
            .sendKeys(bodyLetter);

        //отправили и закрыли фрейм
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
            "//div[contains(@class, 'footer')]//button[contains(@data-test-id, 'send')]"))).click();
        SleepUtils.sleep(3000);
        //close frame
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
            "//div[contains(@class, 'layer layer')]//span[@class='button2__wrapper button2__wrapper_centered']")))
            .click();

        //чек писем во вх себе
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
            "//div[contains(@class, 'application-mail__layout')]//a[contains(@title, 'Входящие')]"))).click();

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
            "//div[@class='layout__main-frame']//div[@class='metathread__contain']/div/a"))).click();

        SleepUtils.sleep(2000);
        int emailsInIncoming2 = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(
            "//div[@class='ReactVirtualized__Grid__innerScrollContainer']/a"))).size();
        Assertions.assertThat(emailsInIncoming < emailsInIncoming2).isTrue();

        //открываем письмо в папке вх себе
        /*wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
            "//div[@class='layout__main-frame']//div[@class='metathread__contain']//a"))).click();*/

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
            "//div[@class='ReactVirtualized__Grid__innerScrollContainer']/a[1]"))).click();

        //проверяяем соответствие полей
        String checkFieldTo = wait.until(ExpectedConditions.visibilityOfElementLocated(By
                                      .xpath("//div[contains(@class, 'letter__author')]"
                                          + "//span[@class='letter-contact']")))
                                  .getAttribute("title");
        Assertions.assertThat(letterForMe).isEqualTo(checkFieldTo);

        String checkFieldSubject = wait.until(ExpectedConditions.visibilityOfElementLocated(By
            .xpath("//div[contains(@class, 'thread__subject-line')]/h2[@class='thread-subject']"))).getText();
        Assertions.assertThat(subject).isEqualTo(checkFieldSubject);

        String checkFieldBody = wait.until(ExpectedConditions.visibilityOfElementLocated(By
            .xpath("//div[contains(@class, 'style')]//div[contains(@class, 'js-helper')]/div/div/div/div[1]")))
                                    .getText();
        Assertions.assertThat(bodyLetter).isEqualTo(checkFieldBody);

        //удаляем письмо
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
            "//div[contains(@class, 'portal-menu-element_remove')]/span[@title='Удалить']"))).click();

        //чек корзины
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
            "//div[contains(@class, 'portal-menu-element_back')]/span[@title='Вернуться']"))).click();

        //!!!!!!!!!!!!НЕ НАХОДИТ КНОПКУ
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
            "//div[contains(@class, 'application-mail__layout')]//a[contains(@title, 'Корзина')]"))).click();

        SleepUtils.sleep(2000);
        int emailsInGarbage2 = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(
            "//div[@class='ReactVirtualized__Grid__innerScrollContainer']/a"))).size();
        Assertions.assertThat(emailsInGarbage < emailsInGarbage2).isTrue();

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
            "//div[contains(@class, 'ph-auth')]//div[contains(@class, 'ph-project__account')]"))).click();

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
            "//div[contains(@class, 'ph-item__hover-active')]/div[contains(@class, 'ph-text')]"))).click();
        SleepUtils.sleep(3000);


    }
}
