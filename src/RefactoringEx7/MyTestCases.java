package RefactoringEx7;

import RefactoringEx7.lib.CoreTestCase;
import io.appium.java_client.TouchAction;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MyTestCases extends CoreTestCase {


    @Test
    public void testCheckTextInElement() {

        assertElementHasText(
                By.xpath("//android.widget.TextView[@text='Search Wikipedia']"),
                "Search Wikipedia",
                "We don't see right text in element"
        );
    }

    @Test
    public void testSearchArticles() {

        waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find 'Search Wikipedia' input",
                5
        );

        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search…')]"),
                "java",
                "Cannot find 'Search' ",
                5
        );

        int recordsCount = (int) driver.findElements(By.id("org.wikipedia:id/page_list_item_container")).stream().count();
        System.out.println("число статей " + recordsCount);

        waitForELementPresent(
                By.id("org.wikipedia:id/page_list_item_container"),
                "no search articles found",
                15
        );

        waitForElementAndClick(
                By.id("org.wikipedia:id/search_close_btn"),
                "Cannot find button x to cancel search",
                5
        );

        waitForELementNotPresent(
                By.id("org.wikipedia:id/page_list_item_container"),
                "search articles found",
                5
        );
        int recordsCountAfterDelete = (int) driver.findElements(By.id("org.wikipedia:id/page_list_item_container")).stream().count();
        System.out.println("число статей после удаления слова " + recordsCountAfterDelete);

    }

    ArrayList<String> errors = new ArrayList();
    String valueSearch = "java";
    @Test
    public void testSearchArticles2() {

        waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find 'Search Wikipedia' input",
                5
        );

        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search…')]"),
                valueSearch,
                "Cannot find 'Search' ",
                5
        );

        int recordsCount = (int) driver.findElements(By.id("org.wikipedia:id/page_list_item_container")).stream().count();
        System.out.println("число статей " + recordsCount);

        WebElement word1 = driver.findElement(By.xpath("//android.widget.LinearLayout[2]//android.widget.TextView"));
        System.out.println("Текст второго элемента " + word1.getText());

        waitForElementsAndCheckNameinArticles(
                By.id("org.wikipedia:id/page_list_item_title"),
                valueSearch,
                "Not find articles with search "
        );
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
    @Test
    public void testTitlePresentWithoutWait() {

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



        assertElementPresent(
                By.xpath("//*[@resource-id='org.wikipedia:id/view_page_title_text']"),
                "Cannot find title in article"
        );
    }

    public void assertElementHasText(By by, String value, String errorMessage) {
        WebElement element = waitForELementPresent(by, errorMessage);
        String textElement = element.getAttribute("text");

        Assert.assertEquals(
                errorMessage,
                value,
                textElement
        );
    }


    public WebElement waitForELementPresent(By by, String errorMessage, long timeOutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
        wait.withMessage(errorMessage + "\n");
        return wait.until(
                ExpectedConditions.presenceOfElementLocated(by)
        );
    }

    public WebElement waitForELementPresent(By by, String errorMessage) {

        return waitForELementPresent(by, errorMessage, 5);

    }

    public WebElement waitForElementAndClick(By by, String errorMessage, long timeOutInSeconds) {
        WebElement element = waitForELementPresent(by, errorMessage, timeOutInSeconds);
        element.click();
        return element;
    }

    public WebElement waitForElementAndSendKeys(By by, String value, String errorMessage, long timeOutInSeconds) {
        WebElement element = waitForELementPresent(by, errorMessage, timeOutInSeconds);
        element.sendKeys(value);
        return element;
    }

    public boolean waitForELementNotPresent(By by, String errorMessage, long timeOutInSeconds) {

        WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
        wait.withMessage(errorMessage + "\n");
        return wait.until(
                ExpectedConditions.invisibilityOfElementLocated(by)
        );
    }
    public WebElement waitForElementAndClear(By by, String errorMessage, long timeOutInSeconds){
        WebElement element = waitForELementPresent(by, errorMessage, timeOutInSeconds);
        element.clear();
        return element;
    }

    public void swipeUp (int timeOfSwipe) {

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

    public void swipeUpQuick () {
        swipeUp(200);
    }

    public void swipeUpToFindElement(By by, String errorMessage, int maxSwipes) {

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
    public void swipeElementToLeft(By by, String errorMessage) {

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
    public int getAmountOfElements(By by){
        List elements = driver.findElements(by);
        return elements.size();
    }

    public void assertElementNotPresent(By by, String errorMessage) {
        int amountOfElements = getAmountOfElements(by);
        if (amountOfElements > 0) {
            String defoultMessage = "an element '" + by.toString() + "'supposed to be not present";
            throw new AssertionError(defoultMessage + " " + errorMessage);
        }
    }

    public void waitForElementsAndCheckNameinArticles(By by, String search, String errorMessage ) {

        List<WebElement> elements = waitForELementsPresent(by, errorMessage);
        for (WebElement element : elements) {
            String name = element.getText();
            if (!name.toLowerCase().contains(search)) {
                errors.add(name);
            }
        }

        Assert.assertEquals(
                "word '" +  valueSearch + "' not find in " + errors.size()+ " articles - " + errors + " ", 0, errors.size());
    }

    public List waitForELementsPresent(By by, String errorMessage) {

        WebDriverWait wait = new WebDriverWait(driver, 15);
        wait.withMessage(errorMessage + "\n");
        return wait.until(
                ExpectedConditions.presenceOfAllElementsLocatedBy(by)
        );
    }
    public void assertElementPresent(By by, String errorMessage) {

        int amountOfElements = getAmountOfElements(by);
        if (amountOfElements < 1 ) {
            String defoultMessage = "an element '" + by.toString() + "'supposed to be not present";
            throw new AssertionError(defoultMessage + " " + errorMessage);
        }
    }
}
