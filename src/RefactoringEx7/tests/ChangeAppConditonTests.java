package RefactoringEx7.tests;

import RefactoringEx7.lib.CoreTestCase;
import RefactoringEx7.lib.ui.ArticlePageObject;
import RefactoringEx7.lib.ui.SearchPageObject;
import RefactoringEx7.lib.ui.factories.ArticlePageObjectFactory;
import RefactoringEx7.lib.ui.factories.SearchPageObjectFactory;
import org.junit.Assert;
import org.junit.Test;

public class ChangeAppConditonTests extends CoreTestCase {

    @Test
    public void testChangeScreenOrientaionOnSearchResults() {

        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);

        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.clickByArticleWithSubstring("Object-oriented programming language");

        ArticlePageObject articlePageObject = ArticlePageObjectFactory.get(driver);

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

    //@Test
   // public void testCheckSearchArticleInBackground() {

    //    SearchPageObject searchPageObject = new SearchPageObject(driver);
    //    searchPageObject.initSearchInput();
     //   searchPageObject.typeSearchLine("Havana");
     //   searchPageObject.waitForSearchResult("Capital and largest city of Cuba");
     //   this.backgroundApp(2);

      //  searchPageObject.waitForSearchResult("Capital and largest city of Cuba");
   // }


}
