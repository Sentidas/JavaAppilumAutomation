package RefactoringEx7.lib.ui.android;

import RefactoringEx7.lib.ui.MyListPageObject;
import io.appium.java_client.AppiumDriver;

public class AndroidMyListPageObject extends MyListPageObject {

    static {
        FOLDER_BY_NAME_TPL = "xpath://android.widget.FrameLayout//android.widget.TextView[@text='{FOLDER_NAME}']";
        ARTICLE_BY_TITLE_TPL = "xpath://*[@text='{TITLE}']";
    }
    public AndroidMyListPageObject(AppiumDriver driver){
        super(driver);
    }
}
