package MyTasks;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class TestEx4 {

    private AppiumDriver driver;
    ArrayList<String> errors = new ArrayList();
    String valueSearch = "java";

    @Before
    public void setUp() throws Exception {
        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "AndroidTestDevice");
        capabilities.setCapability("platformVersion", "10");
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
    public void testSearchArticles() {

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

    private void waitForElementsAndCheckNameinArticles(By by, String search, String errorMessage ) {

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

    private WebElement waitForELementPresent(By by, String errorMessage, long timeOutInSeconds) {

        WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
        wait.withMessage(errorMessage + "\n");
        return wait.until(
                ExpectedConditions.presenceOfElementLocated(by)
        );
    }

    private List waitForELementsPresent(By by, String errorMessage) {

        WebDriverWait wait = new WebDriverWait(driver, 15);
        wait.withMessage(errorMessage + "\n");
        return wait.until(
                ExpectedConditions.presenceOfAllElementsLocatedBy(by)
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

}



