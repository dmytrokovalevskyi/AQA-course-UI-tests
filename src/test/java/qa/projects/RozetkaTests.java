package qa.projects;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import qa.projects.pages.BasePage;
import qa.projects.pages.CategoriesListPage;
import qa.projects.pages.ProductsListPage;

import static com.codeborne.selenide.Selenide.*;
import static org.testng.Assert.assertTrue;

public class RozetkaTests {
    @BeforeMethod
    public void beforeMethod() {
        BasePage.openBaseUrl();
    }

    @Test(description = "Кошик порожній.")
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

    @Test(description = "Пошук Apple містить 20 категорій.")
    public void secondTest() {
        BasePage.searchForValue("Apple");
        CategoriesListPage.categories.shouldHave(CollectionCondition.size(20));
        CategoriesListPage.selectCategory("iPhone");
        CategoriesListPage.categoryTitle.shouldHave(Condition.text("Apple"));
    }

    @Test(description = "Фільтрація зменшує кількість відображених товарів.")
    public void thirdTest() {
        BasePage.searchForValue("iphone 13");
        ProductsListPage.assertSelectedSeriesFilter("iPhone 13");
        int productsBeforeFiltering = ProductsListPage.foundProducts.size();
        ProductsListPage.rozetkaSellerFilter.click();
        ProductsListPage.assertSelectedSellerFilter("Rozetka");
        ProductsListPage.foundProducts.shouldHave(CollectionCondition.sizeLessThanOrEqual(productsBeforeFiltering));
    }

    @Test(description = "Розмір картки товару змінюється відповідно налаштувань відображення.")
    public void fourthTest() {
        BasePage.searchForValue("iphone 13");
        int height = ProductsListPage.productsCards.get(0).getSize().getHeight();
        int width = ProductsListPage.productsCards.get(0).getSize().getWidth();
        ProductsListPage.bigSellsButton.click();
        assertTrue(ProductsListPage.productsCards.get(0).getSize().getHeight() != height && ProductsListPage.productsCards.get(0).getSize().getWidth() != width);
    }

    @Test(description = "Сортування від дорогих до дешевих працює.")
    public void fifthTest() {
        BasePage.searchForValue("iphone");
        ProductsListPage.selectSorting(ProductsListPage.sortingOptions.HIGHTOLOW);
        sleep(120);
        int firstProductPrice = ProductsListPage.getProductPrice(1);
        int secondProductPrice = ProductsListPage.getProductPrice(2);
        assertTrue(firstProductPrice >= secondProductPrice, "Сортування [Від дорогих до дешевих] не відпрацювало");
    }
}