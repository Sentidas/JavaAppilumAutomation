package RefactoringEx7;

import RefactoringEx7.lib.CoreTestCase;
import RefactoringEx7.lib.ui.*;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;


public class MyTestCases extends CoreTestCase {

    @Test
    public void testCheckTextInElement_ex2() {

        SearchPageObject searchPageObject = new SearchPageObject(driver);
        searchPageObject.checkTextInElement("Search Wikipedia");
    }

    @Test
    public void testCancelSearch_ex3() {

        SearchPageObject searchPageObject = new SearchPageObject(driver);

        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("appium");
        System.out.println(searchPageObject.getAmountOfFoundArticles());
        searchPageObject.waitForCancelButtonToAppear();
        searchPageObject.clickCancelSearch();
        searchPageObject.assertThereIsNoResultsOfSearch();
    }

    @Test
    public void testCheckTitleInArticles_ex4() {
        String valueSearch = "java";
        SearchPageObject searchPageObject = new SearchPageObject(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine(valueSearch);
        System.out.println(searchPageObject.getAmountOfFoundArticles());

        int recordsCount = (int) driver.findElements(By.id("org.wikipedia:id/page_list_item_container")).stream().count();
        System.out.println("число статей " + recordsCount);

        WebElement word1 = driver.findElement(By.xpath("//android.widget.LinearLayout[2]//android.widget.TextView"));
        System.out.println("Текст второго элемента " + word1.getText());

        int errors = searchPageObject.waitForElementsAndCheckNameinArticles(valueSearch);
        System.out.println("количество ошибок: " + errors);
        assertTrue("Not all titles of articles contain search_value",
                errors == 0);

    }
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

        NavigationUI navigationUI = new NavigationUI(driver);
        // navigationUI.clickMyLists();
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Lisbon");
        searchPageObject.clickByArticleWithSubstring("Capital city of Portugal");

        articlePageObject.waitForTitleElement();
        String articleTitle2 = articlePageObject.getArticleTitle();
        articlePageObject.addArticleToPresentList(nameOfFolder);
        articlePageObject.closeArticle();


        navigationUI.clickMyLists();

        MyListPageObject myListPageObject = new MyListPageObject(driver);
        myListPageObject.openFolderByName(nameOfFolder);
        myListPageObject.swipeToArticleToDelete(articleTitle);
        myListPageObject.waitForArticleToAppearByTitleAndClick(articleTitle2);
        myListPageObject.waitForArticleToAppearByTitle(articleTitle2);
    }

    @Test
    public void testTitlePresentWithoutWait_ex6() {
        SearchPageObject searchPageObject = new SearchPageObject(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Havana");
        searchPageObject.clickByArticleWithSubstring("Capital and largest city of Cuba");
        ArticlePageObject articlePageObject = new ArticlePageObject(driver);
        articlePageObject.assertElementPresent();
    }
}
