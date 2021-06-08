package RefactoringEx7.lib.ui;

import RefactoringEx7.lib.CoreTestCase;
import RefactoringEx7.lib.Platform;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class MainPageObject extends CoreTestCase {
    protected AppiumDriver driver; // инициализируем драйвер

    public MainPageObject(AppiumDriver driver) {
        this.driver = driver;
    }

    public void assertElementHasText(String locator, String value, String errorMessage) {
        WebElement element = waitForELementPresent(locator, errorMessage);
        String textElement;
        if(Platform.getInstance().isAndroid()) {
             textElement = element.getAttribute("text");
        } else {
            textElement = element.getAttribute("name");
        }
        assertEquals(
                errorMessage,
                value,
                textElement
        );
    }


    public WebElement waitForELementPresent(String locator, String errorMessage, long timeOutInSeconds) {

        By by = this.getLocatorByString(locator);
        WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
        wait.withMessage(errorMessage + "\n");
        return wait.until(
                ExpectedConditions.presenceOfElementLocated(by)
        );
    }

    public WebElement waitForELementPresent(String locator, String errorMessage) {

        return waitForELementPresent(locator, errorMessage, 5);

    }

    public WebElement waitForElementAndClick(String locator, String errorMessage, long timeOutInSeconds) {
        WebElement element = waitForELementPresent(locator, errorMessage, timeOutInSeconds);
        element.click();
        return element;
    }

    public WebElement waitForElementAndSendKeys(String locator, String value, String errorMessage, long timeOutInSeconds) {
        WebElement element = waitForELementPresent(locator, errorMessage, timeOutInSeconds);
        element.sendKeys(value);
        return element;
    }

    public boolean waitForELementNotPresent(String locator, String errorMessage, long timeOutInSeconds) {

        By by = this.getLocatorByString(locator);
        WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
        wait.withMessage(errorMessage + "\n");
        return wait.until(
                ExpectedConditions.invisibilityOfElementLocated(by)
        );
    }

    public WebElement waitForElementAndClear(String locator, String errorMessage, long timeOutInSeconds) {
        WebElement element = waitForELementPresent(locator, errorMessage, timeOutInSeconds);
        element.clear();
        return element;
    }

    public void swipeUp(int timeOfSwipe) {

        TouchAction action = new TouchAction(driver);
        Dimension size = driver.manage().window().getSize(); // получаем параметры экрана

        int x = size.width / 2; // (горизонталь не меняется, тапаем на середину экрана)
        int start_y = (int) (size.height * 0.8); // внизу экрана, около 80 %
        int end_y = (int) (size.height * 0.2);

        action
                .press(PointOption.point(x, start_y))
                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(300)))
                .moveTo(PointOption.point(x, end_y))
                .release()
                .perform();
    }

    public void swipeUpQuick() {
        swipeUp(200);
    }

    public void swipeUpToFindElement(String locator, String errorMessage, int maxSwipes) {
        By by = this.getLocatorByString(locator);
        int alreadySwiped = 0;

        while (driver.findElements(by).size() == 0) { // пока не найден нужный элемент свайпим вниз

            if (alreadySwiped > maxSwipes) {
                waitForELementPresent(locator, "Cannot find element by swiping up. \n" + errorMessage, 0);
                return;
            }
            swipeUpQuick();
            ++alreadySwiped;
            System.out.println("количество свайпов " + alreadySwiped);
        }
    }
    public void swipeUpTitleElementAppear(String locator, String errorMessage, int maxSwipes) {

        int alreadySwiped = 0;
        while (!this.isElementLocationOntheScreen(locator)) {
            if (alreadySwiped > maxSwipes) {
                Assert.assertTrue(errorMessage, this.isElementLocationOntheScreen(locator));
            }
            swipeUpQuick();
            ++alreadySwiped;
            System.out.println("количество свайпов " + alreadySwiped);
        }
    }

    public boolean isElementLocationOntheScreen(String locator) {
        int element_location_by_y = this.waitForELementPresent(locator,
                "Cannot find element by locator", 1).getLocation().getY();
        int screen_size_by_y = driver.manage().window().getSize().getHeight();
        return element_location_by_y < screen_size_by_y;
    }

    public void clickElementToTheRightUpperCorner(String locator, String error_message){

        WebElement element = this.waitForELementPresent(locator + "/..", error_message);
        int right_x = element.getLocation().getX();
        int upper_y = element.getLocation().getY();
        int lower_y = upper_y + element.getSize().getHeight();
        int middle_y = (upper_y + lower_y)/2;
        int width = element.getSize().getWidth();

        int point_to_click_x = (right_x + width) - 3;
        int point_to_click_y = middle_y;

        TouchAction action = new TouchAction(driver);
        action.tap(PointOption.point(point_to_click_x,point_to_click_y)).perform();
    }

    public void swipeElementToLeft(String locator, String errorMessage) {

        WebElement element = waitForELementPresent(locator, errorMessage, 10);

        int left_x = element.getLocation().getX(); // запись левоой координаты элемента
        int right_x = left_x + element.getSize().getWidth(); // прибавляем ширину экрана, находим правую границу экрана
        int upper_y = element.getLocation().getY();
        int lower_y = upper_y + element.getSize().getHeight();
        int middle_y = (upper_y + lower_y) / 2; // ищем середину у элемента по оси у где будем свайпить

       TouchAction action = new TouchAction(driver);
       action.press(PointOption.point(right_x, middle_y));
       action.waitAction(WaitOptions.waitOptions(Duration.ofMillis(900)));

       if(Platform.getInstance().isAndroid())
       {
           action.moveTo(PointOption.point(left_x, middle_y));
       } else {
           int offset_x = (-1 * element.getSize().getWidth());
           action.moveTo(PointOption.point(offset_x,0));
       }
       action.release();
       action.perform();
    }
    
    public int getAmountOfElements(String locator) {
        By by = this.getLocatorByString(locator);
        List elements = driver.findElements(by);
        return elements.size();
    }

    public void assertElementNotPresent(String locator, String errorMessage) {
        int amountOfElements = getAmountOfElements(locator);
        if (amountOfElements > 0) {
            String defoultMessage = "an element '" + locator + "'supposed to be not present";
            throw new AssertionError(defoultMessage + " " + errorMessage);
        }
    }

    public List waitForELementsPresent(String locator, String errorMessage) {
        By by = this.getLocatorByString(locator);
        WebDriverWait wait = new WebDriverWait(driver, 15);
        wait.withMessage(errorMessage + "\n");
        return wait.until(
                ExpectedConditions.presenceOfAllElementsLocatedBy(by)
        );
    }

    public void assertElementPresent(String locator, String errorMessage) {

        int amountOfElements = getAmountOfElements(locator);
        if (amountOfElements < 1) {
            String defoultMessage = "an element '" + locator + "'supposed to be not present";
            throw new AssertionError(defoultMessage + " " + errorMessage);
        }
    }

    public int waitForElementsAndCheckNameinArticles(String locator, String search, String errorMessage) {
        ArrayList<String> errors = new ArrayList();
        List<WebElement> elements = waitForELementsPresent(locator, errorMessage);
        for (WebElement element : elements) {
            String name = element.getText();
            if (!name.toLowerCase().contains(search)) {
                errors.add(name);
            }
        }
        return errors.size();
    }

    private By getLocatorByString(String locatorWithType) {

        String[] explodedLocator = locatorWithType.split(Pattern.quote(":"), 2);
        String byType = explodedLocator[0];
        String locator = explodedLocator[1];

        if(byType.equals("xpath")) {
            return By.xpath(locator);
        }else if(byType.equals("id")) {
            return By.id(locator);
        }else{
            throw new IllegalArgumentException("Cannot get type of locator. Locator: " + locatorWithType);
        }
    }
}
