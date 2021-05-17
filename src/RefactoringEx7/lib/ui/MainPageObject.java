package RefactoringEx7.lib.ui;

import RefactoringEx7.lib.CoreTestCase;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

public class MainPageObject extends CoreTestCase {
    protected AppiumDriver driver; // инициализируем драйвер

    public MainPageObject(AppiumDriver driver) {
        this.driver = driver;
    }

    public void assertElementHasText(By by, String value, String errorMessage) {
        WebElement element = waitForELementPresent(by, errorMessage);
        String textElement = element.getAttribute("text");

        assertEquals(
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

    public WebElement waitForElementAndClear(By by, String errorMessage, long timeOutInSeconds) {
        WebElement element = waitForELementPresent(by, errorMessage, timeOutInSeconds);
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
                .press(x, start_y)
                .waitAction(timeOfSwipe)
                .moveTo(x, end_y)
                .release()
                .perform(); // послед для свайпа, нажали, держим, двигаем, отпускаем
        //perfome - отсылает всю нашу послед на выполнение
    }

    public void swipeUpQuick() {
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

    public int getAmountOfElements(By by) {
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

    public List waitForELementsPresent(By by, String errorMessage) {

        WebDriverWait wait = new WebDriverWait(driver, 15);
        wait.withMessage(errorMessage + "\n");
        return wait.until(
                ExpectedConditions.presenceOfAllElementsLocatedBy(by)
        );
    }

    public void assertElementPresent(By by, String errorMessage) {

        int amountOfElements = getAmountOfElements(by);
        if (amountOfElements < 1) {
            String defoultMessage = "an element '" + by.toString() + "'supposed to be not present";
            throw new AssertionError(defoultMessage + " " + errorMessage);
        }
    }

    public int waitForElementsAndCheckNameinArticles(By by, String search, String errorMessage) {
        ArrayList<String> errors = new ArrayList();
        List<WebElement> elements = waitForELementsPresent(by, errorMessage);
        for (WebElement element : elements) {
            String name = element.getText();
            if (!name.toLowerCase().contains(search)) {
                errors.add(name);
            }
        }
        return errors.size();
    }
}
