package RefactoringEx7.tests;

import RefactoringEx7.lib.CoreTestCase;
import RefactoringEx7.lib.ui.ArticlePageObject;
import RefactoringEx7.lib.ui.MyListPageObject;
import RefactoringEx7.lib.ui.NavigationUI;
import RefactoringEx7.lib.ui.SearchPageObject;
import org.junit.Test;

public class MyListTests extends CoreTestCase {

    @Test
    public void testSaveTwoArticleInMyListAndDeleteOne_ex5() {

        SearchPageObject searchPageObject = new SearchPageObject(driver);

        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Havana");
        searchPageObject.clickByArticleWithSubstring("Capital and largest city of Cuba");

        ArticlePageObject articlePageObject = new ArticlePageObject(driver);

        articlePageObject.waitForTitleElement();
        String articleTitle = articlePageObject.getArticleTitle();
        String nameOfFolder = "viaje";
        articlePageObject.addArticleToNewList(nameOfFolder);
        articlePageObject.closeArticle();
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Lisbon");
        searchPageObject.clickByArticleWithSubstring("Capital city of Portugal");
        articlePageObject.waitForTitleElement();
        String articleTitle2 = articlePageObject.getArticleTitle();
        articlePageObject.addArticleToPresentList(nameOfFolder);
        articlePageObject.closeArticle();

        NavigationUI navigationUI = new NavigationUI(driver);
        navigationUI.clickMyLists();

        MyListPageObject myListPageObject = new MyListPageObject(driver);
        myListPageObject.openFolderByName(nameOfFolder);
        myListPageObject.swipeToArticleToDelete(articleTitle);
        myListPageObject.waitForArticleToAppearByTitleAndClick(articleTitle2);
        myListPageObject.waitForArticleToAppearByTitle(articleTitle2);
    }

}
