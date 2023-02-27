package selenium.basic.test.hm3.exercise1;

import org.assertj.core.api.Assertions;
import org.assertj.core.api.ListAssert;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import selenium.basic.test.hm3.BaseSeleniumTest;
import utils.SleepUtils;
import java.util.List;
import java.util.stream.Collectors;

public  class ExerciseOne extends BaseSeleniumTest {


    @Test
    void loginCreateSaveToDraftSend(){
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
            .sendKeys("test.lvlup");

        /*WebElement usernameTextField = frameDriver.findElement(By.xpath(
            "//*[@id='login-content']//input[@name='username']"));
        usernameTextField.sendKeys("test.lvlup");*/

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
            "//button[contains(@data-test-id, 'next-button')]"))).click();


        wait.until(ExpectedConditions.visibilityOfElementLocated(By
                .xpath("//*[@id='login-content']//input[@name='password']")))
            .sendKeys("klik_1360");


        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
            "//*[@id='login-content']//button[@data-test-id='submit-button']"))).click();

        SleepUtils.sleep(3000);
        var currentHandle = driver.getWindowHandle();
        driver.switchTo().window(currentHandle);
        SleepUtils.sleep(3000);

        var title = driver.getTitle();
        Assertions.assertThat(title).isEqualTo("Входящие - Почта Mail.ru");

        //попытка переписать под явное
        List<WebElement> searchList = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.
            xpath("//div[contains(@class, 'layout__column_left')]//a[contains(@class,"
                + " 'child-level_0')]//div[@class='nav__folder-name__txt']")));

        //ПАДАЕТ ТУТ - ВОЗВРАЩАЕТ ПУСТОЙ МАССИВ
        Assertions.assertThat(searchList.stream().map(WebElement::getText).collect(Collectors.toList()))
                  .containsExactly("Входящие", "Отправленные", "Черновики", "Спам", "Корзина");

        /*List <WebElement> searchList = driver.findElements(By.xpath(
            "//div[contains(@class, 'layout__column_left')]//a[contains(@class,"
                + " 'child-level_0')]//div[@class='nav__folder-name__txt']"));*/
        ////div[@class='layout__column layout__column_left']//a[contains(@class, 'child-level_0')]
        //div[@class='layout__main-frame']//span[@class='octopus__title']

        /*Assertions.assertThat(searchList.stream().map(WebElement::getText).collect(Collectors.toList()))
                  .containsExactly("Входящие", "Отправленные", "Черновики", "Спам", "Корзина");*/

        // List <String> linksText = searchList.stream().map(WebElement::getText).collect(Collectors.toList());



        //ПАДАЕТ ТУТ - ВОЗВРАЩАЕТ ПУСТОЙ МАССИВ
       // Assertions.assertThat(linksText).isEqualTo("Отправленные");
       //  Assertions.assertThat(linksText).containsExactly("Отправленные");


        //рабочий ассерт
       // Assertions.assertThat(searchList).hasSizeGreaterThan(4);
/*

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
            "//*[@class='ico ico_16-compose ico_size_s']"))).click();
        //заполняем поле "кому"
        wait.until(ExpectedConditions.visibilityOfElementLocated(By
                .xpath("//div[contains(@class, 'head_container')]//input[contains(@class, 'container')]")))
            .sendKeys("test@mail.ru");

        wait.until(ExpectedConditions.visibilityOfElementLocated(By
                .xpath("//div[contains(@class, 'subject__container')]//input[contains(@class, 'container')]")))
            .sendKeys("test");

        //ССАНЫЙ ПУТЬ
        wait.until(ExpectedConditions.visibilityOfElementLocated(By
                .xpath("//body[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[2]/div[3]/div[5]/div[1]/div[1]/div[2]/div[1]/div[1]")))
            .sendKeys("testBody");
        //div[contains(@class, 'compose-app')]//div[contains(@class, 'editable-taau')]
        SleepUtils.sleep(3000);

        //сохраняем черновик
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
            "//div[contains(@class, 'footer')]//button[contains(@data-test-id, 'save')]"))).click();

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
            "//div[contains(@class, 'compose-app_fix')]//button[@tabindex = '700']"))).click();

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
            "//div[contains(@id, 'sideBarContent')]//a[@title= 'Черновики']"))).click();

        //ПРОБОВАЛ ВЫТЯНУТЬ TITLE
        */
/*List <WebElement> e = driver.findElements(By
            .xpath("//div[contains(@class, 'llc__item llc__item_date')]"));
        e.forEach(link-> System.out.println(link.getAttribute("title")));*//*



        //5.ПРОВЕРИТЬ ЧТО ПИСЬМО СОХРАНЕНО В ЧЕРН
        //6.Verify контент, адресата и тему письма (должно совпадать с пунктом 3)

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
            "//div[@class='layout__main-frame']//a[contains(@class,'llc llc_normal llc_first')]"))).click();

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
            "//div[contains(@class, 'footer')]//button[contains(@data-test-id, 'send')]"))).click();

        SleepUtils.sleep(3000);



        //8.ПРОВНРЯЕМ Verify, что письмо исчезло из черновиков
        boolean draftsAreEmpty = driver.findElements(By.xpath(
            "//a[contains(@class, 'llc llc_normal llc_first llc_last')]")).isEmpty();
        Assertions.assertThat(draftsAreEmpty).isTrue();



        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
            "//div[contains(@class, 'layer layer')]//span[@class='button2__wrapper button2__wrapper_centered']"))).click();


        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
            "//div[contains(@class, 'application-mail__layout')]//a[contains(@title, 'Отправленные')]"))).click();

*/


    }


    }
