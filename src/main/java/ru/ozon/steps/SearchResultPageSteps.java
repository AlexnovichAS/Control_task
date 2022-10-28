package ru.ozon.steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.ru.И;
import ru.ozon.managers.PageManager;

public class SearchResultPageSteps {
    private final PageManager pageManager = PageManager.getPageManager();

    @И("^Настроить меню поиска:$")
    public void menuSetting(DataTable mapFieldsAndValue) {
        mapFieldsAndValue.asMap(String.class, String.class).forEach((key, value) ->
                pageManager.getSearсhResultsPage().menuSetting((String) key, (String) value));
    }
    @И("Из результатов поиска добавьте в корзину первые {string} товаров:")
    public void из_результатов_поиска_добавьте_в_корзину_первые_товаров(String string) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
}
