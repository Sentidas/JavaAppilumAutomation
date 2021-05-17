package RefactoringEx7.lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class MyListPageObject extends MainPageObject {

    private static final String
            FOLDER_BY_NAME_TPL = "//android.widget.FrameLayout//android.widget.TextView[@text='{FOLDER_NAME}']",
            ARTICLE_BY_TITLE_TPL = "//*[@text='{TITLE}']";

    public MyListPageObject(AppiumDriver driver){
        super(driver);
    }

    public static String getFolderXpathByNameTpl(String nameOfFolder) {
        return FOLDER_BY_NAME_TPL.replace("{FOLDER_NAME}", nameOfFolder);
    }

    public static String getSavedArticleXpathByTitleTpl(String articleTitle) {
        return ARTICLE_BY_TITLE_TPL.replace("{TITLE}", articleTitle);
    }


    public void openFolderByName(String nameOfFolder) {

        String nameOfFolderXpath = getFolderXpathByNameTpl(nameOfFolder);

        this.waitForElementAndClick(
                By.xpath(nameOfFolderXpath),
                "Cannot find folder by name " + nameOfFolder,
                5
        );
    }

    public void waitForArticleToAppearByTitle(String articleTitle){

        String articleTitleXpath = getSavedArticleXpathByTitleTpl(articleTitle);
        this.waitForELementPresent(By.xpath(articleTitleXpath), "Cannot find saved article by title " + articleTitle, 15);
    }

    public void waitForArticleToDisappearByTitle(String articleTitle){

        String articleTitleXpath = getSavedArticleXpathByTitleTpl(articleTitle);
        this.waitForELementNotPresent(By.xpath(articleTitleXpath), "Saved article still present with title " + articleTitle, 15);
    }


    public void swipeToArticleToDelete(String articleTitle){

        this.waitForArticleToAppearByTitle(articleTitle);

        String articleTitleXpath = getSavedArticleXpathByTitleTpl(articleTitle);

        this.swipeElementToLeft(
                By.xpath(articleTitleXpath),
                "Cannot find saved article"
        );
        this.waitForArticleToDisappearByTitle(articleTitle);
    }

    public void waitForArticleToAppearByTitleAndClick(String articleTitle){

        String articleTitleXpath = getSavedArticleXpathByTitleTpl(articleTitle);
        // this.waitForELementPresent(By.xpath(articleTitleXpath), "Cannot find saved article by title" + articleTitle, 15);
        this.waitForElementAndClick(By.xpath(articleTitleXpath), "Cannot find saved article by title" + articleTitle, 15);
    }
}
