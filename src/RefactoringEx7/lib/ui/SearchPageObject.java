package RefactoringEx7.lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class SearchPageObject extends MainPageObject {
    private static final String

            SEARCH_INIT_ELEMENT = "//*[@text='Search Wikipedia']",
    //SEARCH_CHECK_TEXT_ELEMENT = "org.wikipedia:id/search_container",
    SEARCH_INPUT = "//*[contains(@text, 'Search…')]",
    SEARCH_TITLE_IN_ARTICLE = "org.wikipedia:id/page_list_item_title",
            SEARCH_CANCEL_BUTTON = "org.wikipedia:id/search_close_btn",
            SEARCH_RESULT_BY_SUBSTRING_TRL = "//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='{SUBSTRING}']",
            SEARCH_RESULT_ELEMENT = "//*[@resource-id='org.wikipedia:id/search_results_list']/*[@resource-id='org.wikipedia:id/page_list_item_container']",
            SEARCH_EMPTY_RESULT_ELEMENT = "//*[@text='No results found']";
    // By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Object-oriented programming language']"),

    public SearchPageObject(AppiumDriver driver) {
        super(driver);
    }

    /* TEMPLATES METHODS */
    private static String getResultSearchElement (String substring) {
        return SEARCH_RESULT_BY_SUBSTRING_TRL.replace("{SUBSTRING}", substring);
    }
    /* TEMPLATES METHODS  - методы шаблонов */



    public void initSearchInput () {

        this.waitForElementAndClick(By.xpath(SEARCH_INIT_ELEMENT), "Cannot find and click search init element", 5);
        this.waitForELementPresent(By.xpath(SEARCH_INPUT), "Cannot find search input after clicking init element", 15);
    }

    public void checkTextInElement(String valueForCheck) {
        this.assertElementHasText(By.xpath(SEARCH_INIT_ELEMENT),  valueForCheck, "We don't see right text");
    }
    public void waitForCancelButtonToAppear(){

        this.waitForELementPresent(By.id(SEARCH_CANCEL_BUTTON), "Cannot find search cancel button", 5);
    }

    public void waitForCancelButtonToDisappear(){

        this.waitForELementNotPresent(By.id(SEARCH_CANCEL_BUTTON), "Search cancel button is still present", 5);
    }

    public void clickCancelSearch(){

        this.waitForElementAndClick(By.id(SEARCH_CANCEL_BUTTON), "Cannot find and click search cancel button", 5);
    }

    public void typeSearchLine (String searchLine) {
        this.waitForElementAndSendKeys(By.xpath(SEARCH_INPUT), searchLine, "Cannot find and type into search input", 5);

    }

    public void waitForSearchResult (String substring) {

        String searchResultXpath = getResultSearchElement(substring);
        this.waitForELementPresent(By.xpath(searchResultXpath),  "Cannot find search result with substring");
    }

    public void clickByArticleWithSubstring(String substring) {

        String searchResultXpath = getResultSearchElement(substring);
        this.waitForElementAndClick(By.xpath(searchResultXpath),  "Cannot find and click search result with substring", 10);

    }

    public int getAmountOfFoundArticles(){
        this.waitForELementPresent(
                By.xpath(SEARCH_RESULT_ELEMENT),
                "Cannot find anything by the request ",
                15
        );

        return this.getAmountOfElements(By.xpath(SEARCH_RESULT_ELEMENT));
    }


    public void waitForEmptyResultLabel(){

        this.waitForELementPresent(By.xpath(SEARCH_EMPTY_RESULT_ELEMENT), "Cannot find empty result element", 15);

    }
    public void assertThereIsNoResultsOfSearch(){
        this.assertElementNotPresent(By.xpath(SEARCH_EMPTY_RESULT_ELEMENT), "Not supposed to find any results");
    }

    public int waitForElementsAndCheckNameinArticles(String search) {
        int numbers = this.waitForElementsAndCheckNameinArticles(
                By.id(SEARCH_TITLE_IN_ARTICLE),
                search,
                "Not find articles with search "
        );
                return numbers;
    }
}