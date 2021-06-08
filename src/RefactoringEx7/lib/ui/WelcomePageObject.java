package RefactoringEx7.lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class WelcomePageObject extends MainPageObject {

    private static final String
    STEP_LEARN_MORE_LINK = "id:Learn more about Wikipedia",
    STEP_NEW_WAY_TO_EXPLORER_TEXT = "id:New ways to explore",
    STEP_ADD_OR_EDIT_LANG_TEXT = "id:Add or edit preferred languages",
    STEP_LEARN_MORE_ABOUT_DATA_COLLECTED_LINK = "id:Learn more about data collected",
    NEXT_LINK= "id:Next",
    GET_STARTED_BUTTON = "id:Get started",
    //SKIP = "xpath://XCUIElementTypeStaticText[@name=Skip]";
    SKIP = "id:Skip";

    public WelcomePageObject(AppiumDriver driver) {
        super(driver);
    }

    public void waitForLearnMoreLink() {
        this.waitForELementPresent(STEP_LEARN_MORE_LINK, "Cannot find 'Learn more about Wikipedia' link ", 10);
    }

    public void waitForNewWayToExploreText() {
        this.waitForELementPresent(STEP_NEW_WAY_TO_EXPLORER_TEXT, "Cannot find 'New ways to explore' link ");
    }

    public void waitForAddAndEditLangText() {
        this.waitForELementPresent(STEP_ADD_OR_EDIT_LANG_TEXT, "Cannot find 'Add or edit preferred languages' link ");
    }
    public void waitForLearnMoreAboutDataCollected() {
        this.waitForELementPresent(STEP_LEARN_MORE_ABOUT_DATA_COLLECTED_LINK, "Cannot find 'Learn more about data collected' link ");
    }

    public void clickNextButton() {
        this.waitForElementAndClick(NEXT_LINK, "Cannot find 'Next' button ", 10);
    }

    public void clickGetStartedButton() {
        this.waitForElementAndClick(GET_STARTED_BUTTON, "Cannot find 'Get started' button ", 10);
    }
    public void clickSkip(){
        this.waitForElementAndClick(SKIP, "Cannot find and click skip button", 5);
    }
}
