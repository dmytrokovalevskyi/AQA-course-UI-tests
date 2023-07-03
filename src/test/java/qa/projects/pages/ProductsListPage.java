package qa.projects.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class ProductsListPage {
    public static SelenideElement firstItemBuyBadge = $("rz-grid > ul > li:first-child > rz-catalog-tile [aria-label=\"Купити\"]");
    public static ElementsCollection seriesFilters = $$("[data-filter-name='series'] a.checkbox-filter__link");
    public static ElementsCollection sellersFilters = $$("[data-filter-name='seller'] a.checkbox-filter__link");
    public static SelenideElement rozetkaSellerFilter = $("[data-id=\"Rozetka\"]");
    public static SelenideElement sortingOptionsDropdown = $(".select-css.ng-untouched.ng-pristine.ng-valid.ng-star-inserted");
    public static SelenideElement sortingOptionsHigherToLower = $("[value=\"2: expensive\"]");
    public static SelenideElement sortingOptionsLowerToHigher = $("[value=\"1: cheap\"]");
    public static SelenideElement sortingOptionsRelevance = $("[value=\"3: relevance\"]");
    public static ElementsCollection productPrice = $$("span.goods-tile__price-value");
    public static ElementsCollection foundProducts = $$("li.catalog-grid__cell.catalog-grid__cell_type_slim.ng-star-inserted");
    public static ElementsCollection productsCards = $$("li.catalog-grid__cell");
    public static SelenideElement bigSellsButton = $("button[arial-label=\"Крупна плитка\"]");
    public static enum sortingOptions {
        LOWTOHIGH,
        HIGHTOLOW,
        RELEVANCE
    }

    public static void selectSorting(sortingOptions option) {
        sortingOptionsDropdown.click();
        switch (option) {
            case RELEVANCE -> sortingOptionsRelevance.click();
            case HIGHTOLOW -> sortingOptionsHigherToLower.click();
            case LOWTOHIGH -> sortingOptionsLowerToHigher.click();
        }
    }

    public static int getProductPrice(int index) {
        return Integer.parseInt(ProductsListPage.productPrice.get(index).text().replaceAll("[^\\d.]", ""));
    }

    public static void assertSelectedSeriesFilter(String filterName) {
        seriesFilters.find(Condition.text(filterName)).shouldHave((Condition.attributeMatching("class", "checkbox-filter__link checkbox-filter__link--checked")));
    }

    public static void assertSelectedSellerFilter(String sellerFilterName) {
        sellersFilters.find(Condition.text(sellerFilterName)).shouldHave((Condition.attributeMatching("class", "checkbox-filter__link checkbox-filter__link--checked")));
    }

}
