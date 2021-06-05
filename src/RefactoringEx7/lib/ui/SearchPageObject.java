package RefactoringEx7.lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class SearchPageObject extends MainPageObject {
    private static final String

            SEARCH_INIT_ELEMENT = "xpath://*[@text='Search Wikipedia']",
    //SEARCH_CHECK_TEXT_ELEMENT = "org.wikipedia:id/search_container",
    SEARCH_INPUT = "xpath://*[contains(@text, 'Search…')]",
    SEARCH_TITLE_IN_ARTICLE = "id:org.wikipedia:id/page_list_item_title",
            SEARCH_CANCEL_BUTTON = "id:org.wikipedia:id/search_close_btn",
            SEARCH_RESULT_BY_SUBSTRING_TRL = "xpath://*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='{SUBSTRING}']",
            SEARCH_RESULT_ELEMENT = "xpath://*[@resource-id='org.wikipedia:id/search_results_list']/*[@resource-id='org.wikipedia:id/page_list_item_container']",
            SEARCH_EMPTY_RESULT_ELEMENT = "xpath://*[@text='No results found']";
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

        this.waitForElementAndClick(SEARCH_INIT_ELEMENT, "Cannot find and click search init element", 5);
        this.waitForELementPresent(SEARCH_INPUT, "Cannot find search input after clicking init element", 15);
    }

    public void checkTextInElement(String valueForCheck) {
        this.assertElementHasText(SEARCH_INIT_ELEMENT,  valueForCheck, "We don't see right text");
    }
    public void waitForCancelButtonToAppear(){

        this.waitForELementPresent(SEARCH_CANCEL_BUTTON, "Cannot find search cancel button", 5);
    }

    public void waitForCancelButtonToDisappear(){

        this.waitForELementNotPresent(SEARCH_CANCEL_BUTTON, "Search cancel button is still present", 5);
    }

    public void clickCancelSearch(){

        this.waitForElementAndClick(SEARCH_CANCEL_BUTTON, "Cannot find and click search cancel button", 5);
    }

    public void typeSearchLine (String searchLine) {
        this.waitForElementAndSendKeys(SEARCH_INPUT, searchLine, "Cannot find and type into search input", 5);

    }

    public void waitForSearchResult (String substring) {

        String searchResultXpath = getResultSearchElement(substring);
        this.waitForELementPresent(searchResultXpath,  "Cannot find search result with substring");
    }

    public void clickByArticleWithSubstring(String substring) {

        String searchResultXpath = getResultSearchElement(substring);
        this.waitForElementAndClick(searchResultXpath,  "Cannot find and click search result with substring", 10);

    }

    public int getAmountOfFoundArticles(){
        this.waitForELementPresent(
                SEARCH_RESULT_ELEMENT,
                "Cannot find anything by the request ",
                15
        );

        return this.getAmountOfElements(SEARCH_RESULT_ELEMENT);
    }


    public void waitForEmptyResultLabel(){

        this.waitForELementPresent(SEARCH_EMPTY_RESULT_ELEMENT, "Cannot find empty result element", 15);

    }
    public void assertThereIsNoResultsOfSearch(){
        this.assertElementNotPresent(SEARCH_EMPTY_RESULT_ELEMENT, "Not supposed to find any results");
    }

    public int waitForElementsAndCheckNameinArticles(String search) {
        int numbers = this.waitForElementsAndCheckNameinArticles(
                SEARCH_TITLE_IN_ARTICLE,
                search,
                "Not find articles with search "
        );
                return numbers;
    }
}