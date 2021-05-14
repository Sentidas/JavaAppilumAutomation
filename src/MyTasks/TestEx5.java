package MyTasks;/*Написать тест, который:
        1. Сохраняет две статьи в одну папку
        2. Удаляет одну из статей
        3. Убеждается, что вторая осталась
        4. Переходит в неё и убеждается, что title совпадает*/


import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.w3c.dom.ls.LSOutput;

import java.net.URL;

public class TestEx5 {

    private AppiumDriver driver;

    @Before
    public void setUp() throws Exception {
        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "AndroidTestDevice");
        capabilities.setCapability("platformVersion", "8.0.0");
        capabilities.setCapability("automationName", "Appium");
        capabilities.setCapability("appPackage", "org.wikipedia");
        capabilities.setCapability("appActivity", ".main.MainActivity");
        capabilities.setCapability("app", "C:/Users/Lena/Projects/JavaAppiumAutomation/apks/org.wikipedia.apk");

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
    }

    @After
    public void tearDown() {
        driver.quit();
    }


    @Test
    public void testSaveTwoArticleInMyListAndDeleteOne() {
        waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find 'Search Wikipedia' input",
                5
        );
        String searchLine = "Havana";
        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search…')]"),
                searchLine,
                "Cannot find 'Search' ",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_description'][@text='Capital and largest city of Cuba']"),
                "Cannot find  article in search" + searchLine,
                5
        );

        waitForELementPresent(
                By.id("org.wikipedia:id/view_page_title_text"),
                "Cannot find article title",
                15
        );


        waitForElementAndClick(
                By.xpath("//android.widget.ImageView[@content-desc='More options']"),
                "Cannot find button to open article options",
                5
        );
        waitForElementAndClick(
               // By.xpath("//android.widget.TextView[@text='Add to reading list']"),
                By.xpath("//android.widget.ListView/android.widget.LinearLayout[3]"),
                "Cannot find option to add article to reading list",
                5
        );

        waitForElementAndClick(
                By.id("org.wikipedia:id/onboarding_button"),
                "Cannot find 'Got it' tip overlay",
                5
        );
        waitForElementAndClear(
                By.id("org.wikipedia:id/text_input"),
                "Cannot find input to set name of articles folder",
                5
        );
        String nameOfFolder = "viaje";

        waitForElementAndSendKeys(
                By.id("org.wikipedia:id/text_input"),
                nameOfFolder,
                "Cannot put text into articles folder input",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[@text='OK']"),
                "Cannot press OK button",
                5
        );

        waitForElementAndClick(
                By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"),
                "Cannot close article, cannot find x link",
                5
        );
        waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find 'Search Wikipedia' input",
                5
        );
        String searchLine1 = "Lisbon";
        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search…')]"),
                searchLine1,
                "Cannot find 'Search' ",
                5
        );
        waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_description'][@text='Capital city of Portugal']"),
                "Cannot find 'Appium' article in search",
                5
        );

        waitForELementPresent(
                By.id("org.wikipedia:id/view_page_title_text"),
                "Cannot find article title",
                15
        );
        waitForElementAndClick(
                By.xpath("//android.widget.ImageView[@content-desc='More options']"),
                "Cannot find button to open article options",
                5
        );
        waitForElementAndClick(
               // By.xpath("//*[@text='Add to reading list']"),
                By.xpath("//android.widget.ListView/android.widget.LinearLayout[3]"),
                "Cannot find option to add article to reading list",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[@text='" + nameOfFolder + "']"),
                "Cannot find created folder",
                5
        );


        waitForElementAndClick(
                By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"),
                "Cannot close article, cannot find x link",
                5
        );

        waitForElementAndClick(
                By.xpath("//android.widget.FrameLayout[@content-desc='My lists']"),
                "Cannot find navigation button to My list",
                5
        );

        waitForElementAndClick(
                By.xpath("//android.widget.TextView[@text='" + nameOfFolder + "']"),
                "Cannot find created folder",
                15
        );

        swipeElementToLeft(
                By.xpath("//*[@text='" + searchLine + "']"),
                "Cannot find saved article"
        );

        waitForELementPresent(
                By.xpath("//*[@text='" + searchLine1 + "']"),
                "Cannot delete saved article",
                15
        );

        waitForElementAndClick(
                By.xpath("//*[@text='" + searchLine1 + "']"),
                "Cannot find article with name" + searchLine1,
                5
        );

        waitForELementPresent(
                By.xpath("//*[@resource-id='org.wikipedia:id/view_page_title_text'][@text='" + searchLine1 + "']"),
                "Cannot find title in article with name " + searchLine1,
                15
        );
    }
    private WebElement waitForELementPresent(By by, String errorMessage, long timeOutInSeconds) {

        WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
        wait.withMessage(errorMessage + "\n");
        return wait.until(
                ExpectedConditions.presenceOfElementLocated(by)
        );
    }

    private WebElement waitForELementPresent(By by, String errorMessage) {

        return waitForELementPresent(by, errorMessage, 5);

    }

    private WebElement waitForElementAndClick(By by, String errorMessage, long timeOutInSeconds) {
        WebElement element = waitForELementPresent(by, errorMessage, timeOutInSeconds);
        element.click();
        return element;
    }

    private WebElement waitForElementAndSendKeys(By by, String value, String errorMessage, long timeOutInSeconds) {
        WebElement element = waitForELementPresent(by, errorMessage, timeOutInSeconds);
        element.sendKeys(value);
        return element;
    }

    private boolean waitForELementNotPresent(By by, String errorMessage, long timeOutInSeconds) {

        WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
        wait.withMessage(errorMessage + "\n");
        return wait.until(
                ExpectedConditions.invisibilityOfElementLocated(by)
        );
    }
    private WebElement waitForElementAndClear(By by, String errorMessage, long timeOutInSeconds){
        WebElement element = waitForELementPresent(by, errorMessage, timeOutInSeconds);
        element.clear();
        return element;
    }

    protected void swipeUp (int timeOfSwipe) {

        TouchAction action = new TouchAction(driver);
        Dimension size = driver.manage().window().getSize(); // получаем параметры экрана

        int x = size.width / 2; // (горизонталь не меняется, тапаем на середину экрана)
        int start_y = (int) (size.height * 0.8); // внизу экрана, около 80 %
        int end_y = (int) (size.height * 0.2);

        action
                .press(x, start_y)
                .waitAction(timeOfSwipe)
                .moveTo(x, end_y)
                .release()
                .perform(); // послед для свайпа, нажали, держим, двигаем, отпускаем
        //perfome - отсылает всю нашу послед на выполнение
    }

    protected void swipeUpQuick () {

        swipeUp(200);
    }

    protected void swipeUpToFindElement(By by, String errorMessage, int maxSwipes) {

        int alreadySwiped = 0;

        while (driver.findElements(by).size() == 0) { // пока не найден нужный элемент свайпим вниз

            if (alreadySwiped > maxSwipes) {
                waitForELementPresent(by, "Cannot find element by swiping up. \n" + errorMessage, 0);
                return;
            }
            swipeUpQuick();
            ++alreadySwiped;
            System.out.println("количество свайпов " + alreadySwiped);
        }
    }
    protected void swipeElementToLeft(By by, String errorMessage) {

        WebElement element = waitForELementPresent(by, errorMessage, 10);

        int left_x = element.getLocation().getX(); // запись левоой координаты элемента
        int right_x = left_x + element.getSize().getWidth(); // прибавляем ширину экрана, находим правую границу экрана
        int upper_y = element.getLocation().getY();
        int lower_y = upper_y + element.getSize().getHeight();
        int middle_y = (upper_y + lower_y) / 2; // ищем середину у элемента по оси у где будем свайпить

        TouchAction action = new TouchAction(driver);
        action
                .press(right_x, middle_y)
                .waitAction(300).moveTo(left_x, middle_y)
                .release()
                .perform();

    }

}

