package qa.projects;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class CategoriesListPage {
    public static ElementsCollection categories = $$("li.portal-grid__cell.ng-star-inserted");
    public static SelenideElement iphoneCategory = $(".tile-cats__heading.tile-cats__heading_type_center.ng-star-inserted");
    public static SelenideElement categoryTitle = $(".catalog-heading.ng-star-inserted");

}
