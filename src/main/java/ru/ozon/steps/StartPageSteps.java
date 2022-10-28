package ru.ozon.steps;

import io.cucumber.java.ru.И;
import ru.ozon.managers.PageManager;

public class StartPageSteps {
    private final PageManager pageManager = PageManager.getPageManager();

    @И("^Выполните поиск по (.+)$")
    public void find(String string) {
        pageManager.getStartPage().find(string);
    }
}
