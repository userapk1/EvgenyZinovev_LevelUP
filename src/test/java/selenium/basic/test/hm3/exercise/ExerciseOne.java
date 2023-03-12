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

public  class ExerciseOne extends BaseSeleniumTest {


    @Test
    @AllSeleniumOneTag
    void loginCreateSaveToDraftSend() {
        driver.navigate().to(URL);
        /*var title = driver.getTitle();
        Assertions.assertThat(title).isEqualTo("Mail.ru: почта, поиск в интернете, новости, игры");*/

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
            "//button[contains(@class, 'resplash-btn')]"))).click();

        /*WebElement butEnter = driver.findElement(By.xpath("//button[contains(@class, 'resplash-btn')]"));
        butEnter.click();*/

        WebElement frameElement = driver.findElement(By.xpath(
            "//iframe[contains(@class, 'ag-popup__frame__layout__iframe')]"));
        WebDriver frameDriver = driver.switchTo().frame(frameElement);

        wait.until(ExpectedConditions.visibilityOfElementLocated(By
                .xpath("//*[@id='login-content']//input[@name='username']")))
            .sendKeys(login);

        /*WebElement usernameTextField = frameDriver.findElement(By.xpath(
            "//*[@id='login-content']//input[@name='username']"));
        usernameTextField.sendKeys("test.lvlup");*/

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

        /*var title = driver.getTitle();
        Assertions.assertThat(title).isEqualTo("Входящие - Почта Mail.ru");*/

        //попытка переписать под явное
        SleepUtils.sleep(2000);
        List<WebElement> searchList = wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By
            .xpath("//div[contains(@class, 'layout__column_left')]//a[contains(@class,"
                + " 'child-level_0')]//div[@class='nav__folder-name__txt']"), 3));

        Assertions.assertThat(searchList.stream().map(WebElement::getText).collect(Collectors.toList()))
                  .hasSize(7);

        /*Assertions.assertThat(searchList.stream().map(WebElement::getText).collect(Collectors.toList()))
                  .containsExactly("Входящие", "Отправленные", "Черновики", "Спам", "Корзина");*/


        //чекаем скока писем в отправленных
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
            "//div[contains(@class, 'application-mail__layout')]//a[contains(@title, 'Отправленные')]"))).click();

        int emailsInSent = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(
            "//*[@class='ReactVirtualized__Grid__innerScrollContainer']/a"))).size();
        System.out.println(emailsInSent);

        //cмотрим скока записей в черновиках
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
            "//div[contains(@id, 'sideBarContent')]//a[@title= 'Черновики']"))).click();

        SleepUtils.sleep(2000);
        int emailsInDrafts;
        emailsInDrafts = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(
            "//*[@class='ReactVirtualized__Grid__innerScrollContainer']/a")))
                                 .size();
        //System.out.println(emailsInDrafts);

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
            "//*[@class='ico ico_16-compose ico_size_s']"))).click();
        //заполняем поле "кому"
        SleepUtils.sleep(2000);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By
                .xpath("//div[contains(@class, 'head_container')]//input[contains(@class, 'container')]")))
            .sendKeys(destination);

        wait.until(ExpectedConditions.visibilityOfElementLocated(By
                .xpath("//div[contains(@class, 'subject__container')]//input[contains(@class, 'container')]")))
            .sendKeys(subject);

        //путь до бади письма
        wait.until(ExpectedConditions.visibilityOfElementLocated(By
                .xpath("//div[contains(@class, 'compose-app_fix')]//div[contains(@class, 'editable')]/div/div[2]")))
            .sendKeys(bodyLetter);

        SleepUtils.sleep(2000);
        //сохраняем черновик
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
            "//div[contains(@class, 'footer')]//button[contains(@data-test-id, 'save')]"))).click();
        //загрываем фрейм
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
            "//div[contains(@class, 'compose-app_fix')]//button[@tabindex = '700']"))).click();

        SleepUtils.sleep(2000);
        int emailsInDrafts2 = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(
            "//*[@class='ReactVirtualized__Grid__innerScrollContainer']/a")))
                                  .size();
        //System.out.println(emailsInDrafts2);
        //проверяем што черновик сохранен
        Assertions.assertThat(emailsInDrafts2 > emailsInDrafts).isTrue();

        //открываем сохраненное письмо
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
            "//div[@class='layout__main-frame']//a[contains(@class,'llc llc_normal llc_first')]"))).click();

        String checkFieldTo = wait.until(ExpectedConditions.visibilityOfElementLocated(By
            .xpath("//div[contains(@class, 'head_container')]//span[contains(@class, 'text')]"))).getText();
        //System.out.println(checkFieldTo);
        Assertions.assertThat(destination).isEqualTo(checkFieldTo);

        String checkFieldSubject = wait.until(ExpectedConditions.visibilityOfElementLocated(By
            .xpath("//div[contains(@class, 'subject__container')]//input[contains(@class, 'container')]")))
                                       .getAttribute("value");
        //System.out.println(checkFieldSubject);
        Assertions.assertThat(subject).isEqualTo(checkFieldSubject);

        String checkFieldBody = wait.until(ExpectedConditions.visibilityOfElementLocated(By
            .xpath("//div[contains(@class, 'compose-app_fix')]"
                + "//div[contains(@class, 'js-helper')]/div/div/div/div[1]"))).getText();
        Assertions.assertThat(bodyLetter).isEqualTo(checkFieldBody);


        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
            "//div[contains(@class, 'footer')]//button[contains(@data-test-id, 'send')]"))).click();
        SleepUtils.sleep(3000);
        //close frame
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
            "//div[contains(@class, 'layer layer')]//span[@class='button2__wrapper button2__wrapper_centered']")))
            .click();

        //8.ПРОВНРЯЕМ Verify, что письмо исчезло из черновиков
        int emailsInDrafts3 = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(
            "//*[@class='ReactVirtualized__Grid__innerScrollContainer']/a")))
                                  .size();
        Assertions.assertThat(emailsInDrafts2 > emailsInDrafts3).isTrue();

        SleepUtils.sleep(2000);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
            "//*[@href = '/sent/?']"))).click();

        SleepUtils.sleep(2000);
        int emailsInSent2 = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(
            "//*[@class='ReactVirtualized__Grid__innerScrollContainer']/a"))).size();
        System.out.println(emailsInSent2);
        Assertions.assertThat(emailsInSent < emailsInSent2).isTrue();

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
            "//div[contains(@class, 'ph-auth')]//div[contains(@class, 'ph-project__account')]"))).click();

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
            "//div[contains(@class, 'ph-item__hover-active')]/div[contains(@class, 'ph-text')]"))).click();
        SleepUtils.sleep(3000);
    }


}
