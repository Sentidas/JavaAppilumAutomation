        /*Написать тест, который открывает статью и убеждается, что у нее есть элемент title.
        Важно: тест не должен дожидаться появления title, проверка должна производиться сразу.
        Если title не найден - тест падает с ошибкой. Метод можно назвать assertElementPresent.*/


        import io.appium.java_client.AppiumDriver;
        import io.appium.java_client.android.AndroidDriver;
        import org.junit.After;
        import org.junit.Before;
        import org.junit.Test;
        import org.openqa.selenium.By;
        import org.openqa.selenium.WebElement;
        import org.openqa.selenium.remote.DesiredCapabilities;
        import org.openqa.selenium.support.ui.ExpectedConditions;
        import org.openqa.selenium.support.ui.WebDriverWait;

        import java.net.URL;
        import java.util.List;

        public class TestEx6 {

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

            private void assertElementPresent(By by, String errorMessage) {

                int amountOfElements = getAmountOfElements(by);
                if (amountOfElements < 1 ) {
                    String defoultMessage = "an element '" + by.toString() + "'supposed to be not present";
                    throw new AssertionError(defoultMessage + " " + errorMessage);
                }
            }

            private int getAmountOfElements(By by){

                List elements = driver.findElements(by);
                int a = elements.size();
                System.out.println("найдено элементов title " + a);
                return a;
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

            private WebElement waitForELementPresent(By by, String errorMessage, long timeOutInSeconds) {

                WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
                wait.withMessage(errorMessage + "\n");
                return wait.until(
                        ExpectedConditions.presenceOfElementLocated(by)
                );
            }

}


