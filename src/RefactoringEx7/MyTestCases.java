package RefactoringEx7;

import RefactoringEx7.lib.CoreTestCase;
import io.appium.java_client.TouchAction;
import lib.ui.MainPageObject;
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
    private MainPageObject mainPageObject;

    protected void setUp() throws Exception {
        super.setUp();
        mainPageObject = new MainPageObject(driver);
    }

    @Test
    public void testCheckTextInElement_ex2() {

        mainPageObject.assertElementHasText(
                By.xpath("//android.widget.TextView[@text='Search Wikipedia']"),
                "Search Wikipedia",
                "We don't see right text in element"
        );
    }

    @Test
    public void testCancelSearch_ex3() {

        mainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find 'Search Wikipedia' input",
                5
        );

        mainPageObject.waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search…')]"),
                "java",
                "Cannot find 'Search' ",
                5
        );

        int recordsCount = (int) driver.findElements(By.id("org.wikipedia:id/page_list_item_container")).stream().count();
        System.out.println("число статей " + recordsCount);

        mainPageObject.waitForELementPresent(
                By.id("org.wikipedia:id/page_list_item_container"),
                "no search articles found",
                15
        );

        mainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/search_close_btn"),
                "Cannot find button x to cancel search",
                5
        );

        mainPageObject. waitForELementNotPresent(
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
    public void testCheckUpTitleInArticle_ex4() {

        mainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find 'Search Wikipedia' input",
                5
        );

        mainPageObject.waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search…')]"),
                valueSearch,
                "Cannot find 'Search' ",
                5
        );

        int recordsCount = (int) driver.findElements(By.id("org.wikipedia:id/page_list_item_container")).stream().count();
        System.out.println("число статей " + recordsCount);

        WebElement word1 = driver.findElement(By.xpath("//android.widget.LinearLayout[2]//android.widget.TextView"));
        System.out.println("Текст второго элемента " + word1.getText());

//        mainPageObject.waitForElementsAndCheckNameinArticles(
//                By.id("org.wikipedia:id/page_list_item_title"),
//               "java",
//                "Not find articles with search "
//        );
    }
    @Test
    public void testSaveTwoArticleInMyListAndDeleteOne_ex5() {
        mainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find 'Search Wikipedia' input",
                5
        );
        String searchLine = "Havana";
        mainPageObject.waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search…')]"),
                searchLine,
                "Cannot find 'Search' ",
                5
        );

        mainPageObject.waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_description'][@text='Capital and largest city of Cuba']"),
                "Cannot find  article in search" + searchLine,
                5
        );

        mainPageObject.waitForELementPresent(
                By.id("org.wikipedia:id/view_page_title_text"),
                "Cannot find article title",
                15
        );


        mainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.ImageView[@content-desc='More options']"),
                "Cannot find button to open article options",
                5
        );
        mainPageObject.waitForElementAndClick(
                // By.xpath("//android.widget.TextView[@text='Add to reading list']"),
                By.xpath("//android.widget.ListView/android.widget.LinearLayout[3]"),
                "Cannot find option to add article to reading list",
                5
        );

        mainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/onboarding_button"),
                "Cannot find 'Got it' tip overlay",
                5
        );
        mainPageObject.waitForElementAndClear(
                By.id("org.wikipedia:id/text_input"),
                "Cannot find input to set name of articles folder",
                5
        );
        String nameOfFolder = "viaje";

        mainPageObject.waitForElementAndSendKeys(
                By.id("org.wikipedia:id/text_input"),
                nameOfFolder,
                "Cannot put text into articles folder input",
                5
        );

        mainPageObject.waitForElementAndClick(
                By.xpath("//*[@text='OK']"),
                "Cannot press OK button",
                5
        );

        mainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"),
                "Cannot close article, cannot find x link",
                5
        );
        mainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find 'Search Wikipedia' input",
                5
        );
        String searchLine1 = "Lisbon";
        mainPageObject.waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search…')]"),
                searchLine1,
                "Cannot find 'Search' ",
                5
        );
        mainPageObject.waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_description'][@text='Capital city of Portugal']"),
                "Cannot find 'Appium' article in search",
                5
        );

        mainPageObject.waitForELementPresent(
                By.id("org.wikipedia:id/view_page_title_text"),
                "Cannot find article title",
                15
        );
        mainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.ImageView[@content-desc='More options']"),
                "Cannot find button to open article options",
                5
        );
        mainPageObject.waitForElementAndClick(
                // By.xpath("//*[@text='Add to reading list']"),
                By.xpath("//android.widget.ListView/android.widget.LinearLayout[3]"),
                "Cannot find option to add article to reading list",
                5
        );

        mainPageObject.waitForElementAndClick(
                By.xpath("//*[@text='" + nameOfFolder + "']"),
                "Cannot find created folder",
                5
        );


        mainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"),
                "Cannot close article, cannot find x link",
                5
        );

        mainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.FrameLayout[@content-desc='My lists']"),
                "Cannot find navigation button to My list",
                5
        );

        mainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.TextView[@text='" + nameOfFolder + "']"),
                "Cannot find created folder",
                15
        );

        mainPageObject.swipeElementToLeft(
                By.xpath("//*[@text='" + searchLine + "']"),
                "Cannot find saved article"
        );

        mainPageObject.waitForELementPresent(
                By.xpath("//*[@text='" + searchLine1 + "']"),
                "Cannot delete saved article",
                15
        );

        mainPageObject.waitForElementAndClick(
                By.xpath("//*[@text='" + searchLine1 + "']"),
                "Cannot find article with name" + searchLine1,
                5
        );

        mainPageObject.waitForELementPresent(
                By.xpath("//*[@resource-id='org.wikipedia:id/view_page_title_text'][@text='" + searchLine1 + "']"),
                "Cannot find title in article with name " + searchLine1,
                15
        );
    }
    @Test
    public void testTitlePresentWithoutWait_ex6() {

        mainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find 'Search Wikipedia' input",
                5
        );
        String searchLine = "Havana";

        mainPageObject.waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search…')]"),
                searchLine,
                "Cannot find 'Search' ",
                5
        );

        mainPageObject.waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_description'][@text='Capital and largest city of Cuba']"),
                "Cannot find  article in search" + searchLine,
                5
        );



//        mainPageObject.assertElementPresent(
//                By.xpath("//*[@resource-id='org.wikipedia:id/view_page_title_text']"),
//                "Cannot find title in article"
//        );
    }


}
