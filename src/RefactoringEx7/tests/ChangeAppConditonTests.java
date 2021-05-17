package RefactoringEx7.tests;

import RefactoringEx7.lib.CoreTestCase;
import RefactoringEx7.lib.ui.ArticlePageObject;
import RefactoringEx7.lib.ui.SearchPageObject;
import org.junit.Assert;
import org.junit.Test;

public class ChangeAppConditonTests extends CoreTestCase {

    @Test
    public void testChangeScreenOrientaionOnSearchResults() {
        SearchPageObject searchPageObject = new SearchPageObject(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Havana");
        searchPageObject.clickByArticleWithSubstring("Capital and largest city of Cuba");

        ArticlePageObject articlePageObject = new ArticlePageObject(driver);

        String titleBeforeRotation = articlePageObject.getArticleTitle();

        this.rotateScreenLandscape();

        String titleAfterRotation = articlePageObject.getArticleTitle();

        Assert.assertEquals(
                "Article title have bee changed after screen rotation",
                titleBeforeRotation,
                titleAfterRotation
        );

        this.rotateScreenPortrait();

        String titleAfterSecondRotation = articlePageObject.getArticleTitle();

        Assert.assertEquals(
                "Article title have bee changed after screen rotation",
                titleBeforeRotation,
                titleAfterSecondRotation
        );

    }

    @Test
    public void testCheckSearchArticleInBackground() {

        SearchPageObject searchPageObject = new SearchPageObject(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Havana");
        searchPageObject.waitForSearchResult("Capital and largest city of Cuba");
        this.backgroundApp(2);

        searchPageObject.waitForSearchResult("Capital and largest city of Cuba");
    }


}
