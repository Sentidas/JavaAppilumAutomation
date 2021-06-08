package RefactoringEx7.tests;

import RefactoringEx7.lib.CoreTestCase;
import RefactoringEx7.lib.ui.SearchPageObject;
import RefactoringEx7.lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class SearchTests extends CoreTestCase {

    @Test
    public void testCheckTextInElement_ex2() {

        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        searchPageObject.checkTextInElement("Search Wikipedia");

    }

    @Test
    public void testCancelSearch_ex3() {

        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);

        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("java");
        System.out.println(searchPageObject.getAmountOfFoundArticles());
        searchPageObject.waitForCancelButtonToAppear();
        searchPageObject.clickCancelSearch();
        searchPageObject.assertThereIsNoResultsOfSearch();
    }

    @Test
    public void testCheckTitleInArticlesInSearchPage_ex4() {

        String valueSearch = "java";
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);

        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine(valueSearch);
        System.out.println(searchPageObject.getAmountOfFoundArticles());

        int errors = searchPageObject.waitForElementsAndCheckNameinArticles(valueSearch);
        System.out.println("количество ошибок: " + errors);
        assertTrue("Not all titles of articles contain search_value",
                errors == 0);

    }
}
