package RefactoringEx7.tests;

import RefactoringEx7.lib.CoreTestCase;
import RefactoringEx7.lib.ui.ArticlePageObject;
import RefactoringEx7.lib.ui.SearchPageObject;
import org.junit.Test;

public class ArticleTests extends CoreTestCase {


    @Test
    public void testTitlePresentWithoutWait_ex6() {
        SearchPageObject searchPageObject = new SearchPageObject(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Havana");
        searchPageObject.clickByArticleWithSubstring("Capital and largest city of Cuba");
        ArticlePageObject articlePageObject = new ArticlePageObject(driver);
        articlePageObject.assertElementPresent();
    }


    @Test
    public void testCompareArticleTitle() {

        SearchPageObject searchPageObject = new SearchPageObject(driver);

        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Havana");
        searchPageObject.clickByArticleWithSubstring("Capital and largest city of Cuba");

        ArticlePageObject articlePageObject = new ArticlePageObject(driver);
        String articleTitle = articlePageObject.getArticleTitle();

        assertEquals(
                "We see unexpected title!",
                "Havana",
                articleTitle
        );
    }

    @Test
    public void testSwipeArticle() {

        SearchPageObject searchPageObject = new SearchPageObject(driver);

        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Appium");
        searchPageObject.clickByArticleWithSubstring("Appium");

        ArticlePageObject articlePageObject = new ArticlePageObject(driver);
        articlePageObject.waitForTitleElement();
        articlePageObject.swipeToFooter();
    }
}
