package RefactoringEx7.lib.ui.factories;

import RefactoringEx7.lib.Platform;
import RefactoringEx7.lib.ui.SearchPageObject;
import RefactoringEx7.lib.ui.android.AndroidSearchPageObject;
import RefactoringEx7.lib.ui.ios.iOSSearchPageObject;
import io.appium.java_client.AppiumDriver;

public class SearchPageObjectFactory {

    public static SearchPageObject get(AppiumDriver driver) {

        if(Platform.getInstance().isAndroid())
        {
        return new AndroidSearchPageObject(driver);
        }else
        {
            return new iOSSearchPageObject(driver);
        }
    }
}
