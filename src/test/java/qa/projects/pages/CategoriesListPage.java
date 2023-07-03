package qa.projects.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class CategoriesListPage {
    public static ElementsCollection categories = $$("li.portal-grid__cell.ng-star-inserted");
    public static SelenideElement categoryTitle = $(".catalog-heading.ng-star-inserted");

    public static void selectCategory(String category) {
        categories.findBy(Condition.text(category)).click();
    }

}
