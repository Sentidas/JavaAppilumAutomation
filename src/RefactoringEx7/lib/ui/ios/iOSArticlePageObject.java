package RefactoringEx7.lib.ui.ios;

import RefactoringEx7.lib.ui.ArticlePageObject;
import io.appium.java_client.AppiumDriver;

public class iOSArticlePageObject extends ArticlePageObject {

    static {
        TITLE = "id:Java (programming language)";
        FOOTER_ELEMENT = "id:View article in browser";
        OPTION_ADD_TO_MY_LIST_BUTTON = "id:Save for later";
        BUTTON_CLOSE_SYNC_MY_SAVED_ARTICLES = "id:places auth close";
        CLOSE_ARTICLE_BUTTON = "id:Back";
    }

    public iOSArticlePageObject (AppiumDriver driver){
        super(driver);
    }
}
