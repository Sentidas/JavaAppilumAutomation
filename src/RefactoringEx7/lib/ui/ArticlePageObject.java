package RefactoringEx7.lib.ui;

import RefactoringEx7.lib.Platform;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;

 abstract public class ArticlePageObject extends MainPageObject{

    protected static String
            TITLE,
            FOOTER_ELEMENT,
            OPTIONS_BUTTON,
            BUTTON_CLOSE_SYNC_MY_SAVED_ARTICLES,
            OPTION_ADD_TO_MY_LIST_BUTTON,
            ADD_TO_MY_LIST_OVERLAY,
            MY_LIST_NAME_INPUT,
            MY_LIST_OK_BUTTON,
                    NAME_OF_MY_LIST_TPL,
            CLOSE_ARTICLE_BUTTON;

    public static String getSavedListXpathByNameTpl(String nameOfFolder) {
        return NAME_OF_MY_LIST_TPL.replace("{NAME_LIST}", nameOfFolder);
    }
    public ArticlePageObject (AppiumDriver driver) {
        super(driver);
    }

    public WebElement waitForTitleElement(){
        return this.waitForELementPresent(TITLE, "Cannot find article title on page!", 15);
    }

    public WebElement notWaitForTitleElementOnlyClick(){
        return this.waitForELementPresent(TITLE, "Cannot find article title on page!", 15);
    }

    public String getArticleTitle() {
        WebElement titleElement = waitForTitleElement();
        if(Platform.getInstance().isAndroid()){
            return titleElement.getAttribute("text");
        }else {
            return titleElement.getAttribute("name");
        }

    }
    public void swipeToFooter() {
        if (Platform.getInstance().isAndroid()) {
            this.swipeUpToFindElement(FOOTER_ELEMENT, "Cannot find the end of article", 40);
        } else {
            this.swipeUpTitleElementAppear(FOOTER_ELEMENT, "Cannot find the end of article", 40);
        }
    }

    public void addArticleToNewList(String nameOfFolder) {

        this.waitForElementAndClick(
                OPTIONS_BUTTON,
                "Cannot find button to open article options",
                5
        );
        this.waitForElementAndClick(
                OPTION_ADD_TO_MY_LIST_BUTTON,
                "Cannot find option to add article to reading list",
                5
        );

        this.waitForElementAndClick(
                ADD_TO_MY_LIST_OVERLAY,
                "Cannot find 'Got it' tip overlay",
                5
        );
        this.waitForElementAndClear(
                MY_LIST_NAME_INPUT,
                "Cannot find input to set name of articles folder",
                5
        );

        this.waitForElementAndSendKeys(
                MY_LIST_NAME_INPUT,
                nameOfFolder,
                "Cannot put text into articles folder input",
                5
        );

        this.waitForElementAndClick(
                MY_LIST_OK_BUTTON,
                "Cannot press OK button",
                5
        );
    }

    public void addArticlesToMySavedIOS(){
        this.waitForElementAndClick(OPTION_ADD_TO_MY_LIST_BUTTON, "Cannot find option to add article to reading list", 15);
    }
     public void closeButtonSyncMySavedArticlesMyLists(){

         this.waitForElementAndClick(
                 BUTTON_CLOSE_SYNC_MY_SAVED_ARTICLES,
                 "Cannot find button to close for sync my saved articles" ,
                 5);
     }
    public void addArticleToPresentList(String nameOfFolder) {

        this.waitForElementAndClick(
                OPTIONS_BUTTON,
                "Cannot find button to open article options",
                5
        );
        this.waitForElementAndClick(
                OPTION_ADD_TO_MY_LIST_BUTTON,
                "Cannot find option to add article to reading list",
                5
        );
        String listNameXpath = getSavedListXpathByNameTpl(nameOfFolder);
        this.waitForElementAndClick(
                listNameXpath,
                "Cannot find created folder",
                15
        );
    }

    public void closeArticle(){
        this.waitForElementAndClick(
                CLOSE_ARTICLE_BUTTON,
                "Cannot close article, cannot find x link",
                5
        );
    }

    public void assertElementPresent() {
        this.assertElementPresent(
                TITLE,
                "Cannot find title in article"
        );
    }
}
