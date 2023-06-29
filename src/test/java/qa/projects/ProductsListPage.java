package qa.projects;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class ProductsListPage {
    public static String smallProductClass;
    public static SelenideElement firstItemBuyBadge = $("rz-grid > ul > li:first-child > rz-catalog-tile [aria-label=\"Купити\"]");
    public static SelenideElement iphone13Filter = $("[data-id=\"iPhone 13\"]");
    public static SelenideElement rozetkaSellerFilter = $("[data-id=\"Rozetka\"]");
    public static SelenideElement product = $("rzgridtilelayout");
    public static SelenideElement sortingOptionsDropdown = $(".select-css.ng-untouched.ng-pristine.ng-valid.ng-star-inserted");
    public static SelenideElement sortingOptionsHigherToLower = $("[value=\"2: expensive\"]");
    public static SelenideElement firstProductPrice = $("li:first-child span.goods-tile__price-value:only-child");
    public static SelenideElement secondProductPrice = $("li:nth-child(2) span.goods-tile__price-value:only-child");
    public static ElementsCollection foundProducts = $$("li.catalog-grid__cell.catalog-grid__cell_type_slim.ng-star-inserted");
    public static ElementsCollection productsCards = $$("li.catalog-grid__cell");
    public static SelenideElement bigSellsButton = $("button[arial-label=\"Крупна плитка\"]");

}
