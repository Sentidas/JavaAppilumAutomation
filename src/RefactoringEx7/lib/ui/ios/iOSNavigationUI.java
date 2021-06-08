package RefactoringEx7.lib.ui.ios;

import RefactoringEx7.lib.ui.NavigationUI;
import io.appium.java_client.AppiumDriver;

public class iOSNavigationUI extends NavigationUI {
    static {
        MY_LISTS_LINK = "id:Saved";

    }
    public iOSNavigationUI (AppiumDriver driver){
        super(driver);
    }
}
