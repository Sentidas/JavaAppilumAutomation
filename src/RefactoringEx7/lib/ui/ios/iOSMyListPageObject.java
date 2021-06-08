package RefactoringEx7.lib.ui.ios;

import RefactoringEx7.lib.ui.MyListPageObject;
import io.appium.java_client.AppiumDriver;

public class iOSMyListPageObject extends MyListPageObject {

    static {
        FOLDER_BY_NAME_TPL = "xpath://android.widget.FrameLayout//android.widget.TextView[@text='{FOLDER_NAME}']";
        ARTICLE_BY_TITLE_TPL = "xpath://XCUIElementTypeStaticText[contains(@name,'{TITLE}')]";
        //FIRST_ARTICLE_TO_DELETE ="xpath://XCUIElementTypeCell[1]";
        FIRST_ARTICLE_TO_DELETE ="xpath://XCUIElementTypeStaticText[contains(@name,'Lisbon')]";
    }
    public iOSMyListPageObject(AppiumDriver driver){
        super(driver);
    }
}
