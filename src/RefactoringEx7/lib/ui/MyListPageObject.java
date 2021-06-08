package RefactoringEx7.lib.ui;

import RefactoringEx7.lib.Platform;
import RefactoringEx7.lib.ui.factories.SearchPageObjectFactory;
import io.appium.java_client.AppiumDriver;

import static RefactoringEx7.lib.ui.SearchPageObject.getResultSearchElement;


abstract public class MyListPageObject extends MainPageObject {

    protected static String
            FOLDER_BY_NAME_TPL,
            ARTICLE_BY_TITLE_TPL,
            FIRST_ARTICLE_TO_DELETE;

    public MyListPageObject(AppiumDriver driver){
        super(driver);
    }

    public static String getFolderXpathByNameTpl(String nameOfFolder) {
        return FOLDER_BY_NAME_TPL.replace("{FOLDER_NAME}", nameOfFolder);
    }

    public static String  getSavedArticleXpathByTitleTpl(String articleTitle) {
        return ARTICLE_BY_TITLE_TPL.replace("{TITLE}", articleTitle);
    }


    public void openFolderByName(String nameOfFolder) {

        String nameOfFolderXpath = getFolderXpathByNameTpl(nameOfFolder);

        this.waitForElementAndClick(
                nameOfFolderXpath,
                "Cannot find folder by name " + nameOfFolder,
                5
        );
    }

    public void waitForArticleToAppearByTitle(String articleTitle){

        String articleTitleXpath = getSavedArticleXpathByTitleTpl(articleTitle);
        this.waitForELementPresent(articleTitleXpath, "Cannot find saved article by title " + articleTitle, 15);
    }

    public void waitForArticleToDisappearByTitle(String articleTitle){

        String articleTitleXpath = getSavedArticleXpathByTitleTpl(articleTitle);
        this.waitForELementNotPresent(articleTitleXpath, "Saved article still present with title " + articleTitle, 15);
    }


    public void swipeToArticleToDelete(String articleTitle) {

        this.waitForArticleToAppearByTitle(articleTitle);

        String articleXpath = getSavedArticleXpathByTitleTpl(articleTitle);

        this.swipeElementToLeft(
                articleXpath,
                "Cannot find saved article"
        );

        if (Platform.getInstance().isIOS()) {
            this.clickElementToTheRightUpperCorner(articleXpath, "дурацкая ошибка");
        }
         this.waitForArticleToDisappearByTitle(articleTitle);
    }

    public void waitForArticleToAppearByTitleAndClick(String articleTitle){

        String articleTitleXpath = getSavedArticleXpathByTitleTpl(articleTitle);
        // this.waitForELementPresent(By.xpath(articleTitleXpath), "Cannot find saved article by title" + articleTitle, 15);
        this.waitForElementAndClick(articleTitleXpath, "Cannot find saved article by title" + articleTitle, 15);
    }
}
