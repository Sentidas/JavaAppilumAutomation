package RefactoringEx7.lib.ui.factories;

import RefactoringEx7.lib.Platform;
import RefactoringEx7.lib.ui.ArticlePageObject;
import RefactoringEx7.lib.ui.SearchPageObject;
import RefactoringEx7.lib.ui.android.AndroidArticlePageObject;
import RefactoringEx7.lib.ui.android.AndroidSearchPageObject;
import RefactoringEx7.lib.ui.ios.iOSArticlePageObject;
import RefactoringEx7.lib.ui.ios.iOSSearchPageObject;
import io.appium.java_client.AppiumDriver;

public class ArticlePageObjectFactory {

    public static ArticlePageObject get(AppiumDriver driver) {

        if(Platform.getInstance().isAndroid())
        {
            return new AndroidArticlePageObject(driver);
        }else
        {
            return new iOSArticlePageObject(driver);
        }
    }
}
