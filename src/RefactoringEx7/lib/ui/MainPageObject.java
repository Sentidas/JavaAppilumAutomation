package RefactoringEx7.lib.ui;

import RefactoringEx7.lib.CoreTestCase;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

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
        String textElement = element.getAttribute("text");

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

       // action
       //         .press(x, start_y)
        //        .waitAction(timeOfSwipe)
         //       .moveTo(x, end_y)
         //       .release()
          //      .perform(); // послед для свайпа, нажали, держим, двигаем, отпускаем
        //perfome - отсылает всю нашу послед на выполнение
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

    public void swipeElementToLeft(String locator, String errorMessage) {

        WebElement element = waitForELementPresent(locator, errorMessage, 10);

        int left_x = element.getLocation().getX(); // запись левоой координаты элемента
        int right_x = left_x + element.getSize().getWidth(); // прибавляем ширину экрана, находим правую границу экрана
        int upper_y = element.getLocation().getY();
        int lower_y = upper_y + element.getSize().getHeight();
        int middle_y = (upper_y + lower_y) / 2; // ищем середину у элемента по оси у где будем свайпить

       // TouchAction action = new TouchAction(driver);
       // action
        //        .press(right_x, middle_y)
         //       .waitAction(300).moveTo(left_x, middle_y)
          //      .release()
          //      .perform();
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
