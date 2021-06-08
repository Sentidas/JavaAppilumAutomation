package RefactoringEx7.tests;

import RefactoringEx7.lib.CoreTestCase;
import RefactoringEx7.lib.Platform;
import RefactoringEx7.lib.ui.ArticlePageObject;
import RefactoringEx7.lib.ui.MyListPageObject;
import RefactoringEx7.lib.ui.NavigationUI;
import RefactoringEx7.lib.ui.SearchPageObject;
import RefactoringEx7.lib.ui.factories.ArticlePageObjectFactory;
import RefactoringEx7.lib.ui.factories.MyListPageObjectFactory;
import RefactoringEx7.lib.ui.factories.NavigationUIFactory;
import RefactoringEx7.lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;

import javax.xml.transform.sax.SAXResult;

public class MyListTests extends CoreTestCase {

    private static final String nameOfFolder = "My stories";

    @Test
    public void testSaveTwoArticleInMyListAndDeleteOne_ex5_ex11() {
        String articleTitle = null;
        String articleTitle2 = null;

        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);

        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Lisbon");
        searchPageObject.clickByArticleWithSubstring("Capital city of Portugal");

        ArticlePageObject articlePageObject = ArticlePageObjectFactory.get(driver);

        if (Platform.getInstance().isAndroid()) {
            articlePageObject.waitForTitleElement();
            articleTitle = articlePageObject.getArticleTitle();
            articlePageObject.addArticleToNewList(nameOfFolder);
        } else {
            articlePageObject.addArticlesToMySavedIOS();
            articlePageObject.closeButtonSyncMySavedArticlesMyLists();
        }
        articlePageObject.closeArticle();
        searchPageObject.initSearchInput();

        if (Platform.getInstance().isIOS()) {
            searchPageObject.clickCancelSearch();
        }
        searchPageObject.typeSearchLine("Java");
        searchPageObject.clickByArticleWithSubstring("Object-oriented programming language");
        articlePageObject.waitForTitleElement();
        articleTitle2 = articlePageObject.getArticleTitle();

        if (Platform.getInstance().isAndroid()) {
            articlePageObject.addArticleToPresentList(nameOfFolder);
        } else  {
            articlePageObject.addArticlesToMySavedIOS();
        }
        articlePageObject.closeArticle();

        NavigationUI navigationUI = NavigationUIFactory.get(driver);
        navigationUI.clickMyLists();

        MyListPageObject myListPageObject = MyListPageObjectFactory.get(driver);

        if (Platform.getInstance().isAndroid()) {
            myListPageObject.openFolderByName(nameOfFolder);
            myListPageObject.swipeToArticleToDelete(articleTitle2);
            myListPageObject.waitForArticleToAppearByTitleAndClick(articleTitle);
            myListPageObject.waitForArticleToAppearByTitle(articleTitle);
        } else {
            myListPageObject.swipeToArticleToDelete(articleTitle2);
            searchPageObject.waitForArticleWithName("Lisbon");
        }
    }

    @Test
    public void testSaveTwoArticleInMyListAndDeleteOne_ex5_onlyForAndroid() {


        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);

        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Lisbon");
        searchPageObject.clickByArticleWithSubstring("Capital city of Portugal");

        ArticlePageObject articlePageObject = ArticlePageObjectFactory.get(driver);

        articlePageObject.waitForTitleElement();
        String articleTitle = articlePageObject.getArticleTitle();
        articlePageObject.addArticleToNewList(nameOfFolder);
        articlePageObject.closeArticle();
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.clickByArticleWithSubstring("Object-oriented programming language");
        articlePageObject.waitForTitleElement();
        String articleTitle2 = articlePageObject.getArticleTitle();
        articlePageObject.addArticleToPresentList(nameOfFolder);

        articlePageObject.closeArticle();
        NavigationUI navigationUI = NavigationUIFactory.get(driver);
        navigationUI.clickMyLists();

        MyListPageObject myListPageObject = MyListPageObjectFactory.get(driver);

        myListPageObject.openFolderByName(nameOfFolder);
        myListPageObject.swipeToArticleToDelete(articleTitle2);
        myListPageObject.waitForArticleToAppearByTitleAndClick(articleTitle);
        myListPageObject.waitForArticleToAppearByTitle(articleTitle);

}

    @Test
    public void testSaveTwoArticleInMyListAndDeleteOne_ex11_onlyForIos() {
        String articleTitle = null;
        String articleTitle2 = null;

        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);

        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Lisbon");
        searchPageObject.clickByArticleWithSubstring("Capital city of Portugal");

        ArticlePageObject articlePageObject = ArticlePageObjectFactory.get(driver);

        articlePageObject.addArticlesToMySavedIOS();
        articlePageObject.closeButtonSyncMySavedArticlesMyLists();
        articlePageObject.closeArticle();
        searchPageObject.initSearchInput();
        searchPageObject.clickCancelSearch();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.clickByArticleWithSubstring("Object-oriented programming language");
        articlePageObject.waitForTitleElement();
        articleTitle2 = articlePageObject.getArticleTitle();

        articlePageObject.addArticlesToMySavedIOS();

        articlePageObject.closeArticle();
        NavigationUI navigationUI = NavigationUIFactory.get(driver);
        navigationUI.clickMyLists();

        MyListPageObject myListPageObject = MyListPageObjectFactory.get(driver);

        myListPageObject.swipeToArticleToDelete(articleTitle2);
        searchPageObject.waitForArticleWithName("Lisbon");
    }
}

