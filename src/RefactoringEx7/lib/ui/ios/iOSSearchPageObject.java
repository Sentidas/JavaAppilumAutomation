package RefactoringEx7.lib.ui.ios;

import RefactoringEx7.lib.ui.SearchPageObject;
import io.appium.java_client.AppiumDriver;

public class iOSSearchPageObject extends SearchPageObject {
    static{
        SEARCH_INIT_ELEMENT = "xpath://XCUIElementTypeSearchField[@name='Search Wikipedia']";
        SEARCH_INPUT = "xpath://XCUIElementTypeSearchField[@value='Search Wikipedia']";
        SEARCH_TITLE_IN_ARTICLE = "xpath://XCUIElementTypeStaticText";
        SEARCH_CANCEL_BUTTON = "id:clear mini";
        SEARCH_RESULT_BY_SUBSTRING_TRL = "xpath://XCUIElementTypeStaticText[contains(@name,'{SUBSTRING}')]";
        SEARCH_RESULT_ELEMENT = "xpath://XCUIElementTypeCell";
        SEARCH_EMPTY_RESULT_ELEMENT = "xpath://XCUIElementTypeStaticText[@name='No results found']";
    }

    public iOSSearchPageObject(AppiumDriver driver){
        super(driver);
    }
}
