package RefactoringEx7.lib.ui.factories;

import RefactoringEx7.lib.Platform;
import RefactoringEx7.lib.ui.MyListPageObject;
import RefactoringEx7.lib.ui.NavigationUI;
import RefactoringEx7.lib.ui.android.AndroidMyListPageObject;
import RefactoringEx7.lib.ui.android.AndroidNavigationUI;
import RefactoringEx7.lib.ui.ios.iOSMyListPageObject;
import RefactoringEx7.lib.ui.ios.iOSNavigationUI;
import io.appium.java_client.AppiumDriver;

public class MyListPageObjectFactory {

    public static MyListPageObject get(AppiumDriver driver) {

        if(Platform.getInstance().isAndroid())
        {
            return new AndroidMyListPageObject(driver);
        }else
        {
            return new iOSMyListPageObject(driver);
        }
    }
}
