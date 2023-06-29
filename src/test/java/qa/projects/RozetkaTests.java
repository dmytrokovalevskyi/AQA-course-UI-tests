package qa.projects;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.*;
import static org.testng.Assert.assertTrue;

public class RozetkaTests {
    @BeforeMethod
    public void beforeMethod() {
        open("https://rozetka.com.ua/ua/");
    }

    @Test
    public void firstTest() {
        BasePage.openCartButtonGreenBadge.shouldNot(Condition.exist);
        BasePage.searchForValue("iphone");
        ProductsListPage.firstItemBuyBadge.click();
        BasePage.openCartButtonGreenBadge.should(Condition.exist);
        BasePage.openCartButton.click();
        BasePage.cart.shouldHave(CollectionCondition.size(1));
        BasePage.cartModalActionsButtonFirstProduct.click();
        BasePage.cartModalActionsButtonFirstProductRemoveButton.click();
        BasePage.cartModalText.shouldHave(Condition.text("Кошик порожній"));
    }

    @Test
    public void secondTest() {
        BasePage.searchForValue("Apple");
        CategoriesListPage.categories.shouldHave(CollectionCondition.size(20));
        CategoriesListPage.iphoneCategory.click();
        CategoriesListPage.categoryTitle.shouldHave(Condition.text("Apple"));
    }

    @Test
    public void thirdTest() {
        BasePage.searchForValue("iphone 13");
        ProductsListPage.iphone13Filter.shouldHave(Condition.attributeMatching("class", "checkbox-filter__link checkbox-filter__link--checked"));
        int productsBeforeFiltering = ProductsListPage.foundProducts.size();
        ProductsListPage.rozetkaSellerFilter.click();
        ProductsListPage.foundProducts.shouldHave(CollectionCondition.sizeLessThanOrEqual(productsBeforeFiltering));
    }

    @Test
    public void fourthTest() {
        BasePage.searchForValue("iphone 13");
        int height = ProductsListPage.productsCards.get(0).getSize().getHeight();
        int width = ProductsListPage.productsCards.get(0).getSize().getWidth();
        ProductsListPage.bigSellsButton.click();
        assertTrue(ProductsListPage.productsCards.get(0).getSize().getHeight() != height && ProductsListPage.productsCards.get(0).getSize().getWidth() != width);
    }

    @Test
    public void fifthTest() {
        BasePage.searchForValue("iphone");
        ProductsListPage.sortingOptionsDropdown.click();
        ProductsListPage.sortingOptionsHigherToLower.click();
        int firstProductPrice = Integer.parseInt(ProductsListPage.firstProductPrice.text().replaceAll("[^\\d.]", ""));
        int secondProductPrice = Integer.parseInt(ProductsListPage.secondProductPrice.text().replaceAll("[^\\d.]", ""));
        assertTrue(firstProductPrice > secondProductPrice, "Test passed.");
    }
}