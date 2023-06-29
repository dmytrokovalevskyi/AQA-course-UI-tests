package qa.projects;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.*;

public class BasePage {
    public static String baseUrl = "https://rozetka.com.ua/ua/";
    public static SelenideElement openCartButton = $("button.header__button.ng-star-inserted.header__button--active");
    public static SelenideElement openCartButtonGreenBadge = $("rz-header .badge.badge--green.ng-star-inserted");
    public static SelenideElement searchInput = $("input,[name=\"search\"]");
    public static SelenideElement cartModalActionsButtonFirstProduct = $("ul.cart-list #cartProductActions0");
    public static SelenideElement cartModalActionsButtonFirstProductRemoveButton = $("ul.cart-list #cartProductActions0 > ul rz-trash-icon button");
    public static SelenideElement cartModalText = $("h4.cart-dummy__heading");
    public static ElementsCollection cart = $$("ul.cart-list");

    public static void openBaseUrl() {
        open(baseUrl);
    }
    public static void searchForValue(String value) {
        searchInput.setValue(value);
        searchInput.pressEnter();
    }
}
