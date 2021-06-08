package RefactoringEx7.lib.ui.factories;

import RefactoringEx7.lib.Platform;
import RefactoringEx7.lib.ui.NavigationUI;
import RefactoringEx7.lib.ui.SearchPageObject;
import RefactoringEx7.lib.ui.android.AndroidNavigationUI;
import RefactoringEx7.lib.ui.android.AndroidSearchPageObject;
import RefactoringEx7.lib.ui.ios.iOSNavigationUI;
import RefactoringEx7.lib.ui.ios.iOSSearchPageObject;
import io.appium.java_client.AppiumDriver;

public class NavigationUIFactory {

    public static NavigationUI get(AppiumDriver driver) {

        if(Platform.getInstance().isAndroid())
        {
            return new AndroidNavigationUI(driver);
        }else
        {
            return new iOSNavigationUI(driver);
        }
    }
}
