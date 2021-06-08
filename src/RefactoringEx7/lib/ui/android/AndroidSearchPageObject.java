package RefactoringEx7.lib.ui.android;

import RefactoringEx7.lib.ui.SearchPageObject;
import io.appium.java_client.AppiumDriver;

public class AndroidSearchPageObject extends SearchPageObject {

    static{
    SEARCH_INIT_ELEMENT = "xpath://*[@text='Search Wikipedia']";
    SEARCH_INPUT = "xpath://*[contains(@text, 'Search…')]";
    SEARCH_TITLE_IN_ARTICLE = "id:org.wikipedia:id/page_list_item_title";
    SEARCH_CANCEL_BUTTON = "id:org.wikipedia:id/search_close_btn";
    SEARCH_RESULT_BY_SUBSTRING_TRL = "xpath://*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='{SUBSTRING}']";
    SEARCH_RESULT_ELEMENT = "xpath://*[@resource-id='org.wikipedia:id/search_results_list']/*[@resource-id='org.wikipedia:id/page_list_item_container']";
    SEARCH_EMPTY_RESULT_ELEMENT = "xpath://*[@text='No results found']";
    }

    public AndroidSearchPageObject(AppiumDriver driver){
        super(driver);
    }
}
