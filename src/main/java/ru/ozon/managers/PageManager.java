package ru.ozon.managers;



import ru.ozon.pages.BasePage;
import ru.ozon.pages.SearсhResultsPage;
import ru.ozon.pages.StartPage;

import java.util.HashMap;
import java.util.Map;

/**
 * Класс для управления страничками
 *
 * @author Алехнович Александр
 */
public class PageManager {

    /**
     * Менеджер страничек
     *
     * @author Алехнович Александр
     */
    private static PageManager pageManager;

    private Map<String, BasePage> mapPages = new HashMap<>();

    /**
     * Конструктор специально был объявлен как private (singleton паттерн)
     *
     * @author Алехнович Александр
     * @see PageManager#getPageManager()
     */
    private PageManager() {
    }

    /**
     * Стартовая страничка
     * @author Алехнович Александр
     */
    private StartPage startPage;

    /**
     * Страница с результатами поиска
     * @author Алехнович Александр
     */
    private SearсhResultsPage searсhResultsPage;

    /**
     * Ленивая инициализация PageManager
     *
     * @return PageManager
     * @author Алехнович Александр
     */
    public static PageManager getPageManager() {
        if (pageManager == null) {
            pageManager = new PageManager();
        }
        return pageManager;
    }

    /**
     * Ленивая инициализация {@link SearсhResultsPage}
     *
     * @return StartPage
     * @author Алехнович Александр
     */
    public StartPage getStartPage() {
        if (startPage == null) {
            startPage = new StartPage();
        }
        return startPage;
    }

    /**
     * Ленивая инициализация {@link SearсhResultsPage}
     *
     * @return StartSearchPage
     * @author Алехнович Александр
     */
    public SearсhResultsPage getSearсhResultsPage() {
        if (searсhResultsPage == null) {
            searсhResultsPage = new SearсhResultsPage();
        }
        return searсhResultsPage;
    }

    /**
     * Инициализация страниц
     *
     * @return страницу
     * @author Алехнович Александр
     */
    public <T extends BasePage> T getPage(Class<T> ex) {
        if (mapPages.isEmpty() || mapPages.get(ex.getName()) == null) {
            try {
                mapPages.put(ex.getName(), ex.newInstance());
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return (T) mapPages.get(ex.getName());
    }

    void clearMapPage() {
        mapPages.clear();
    }
}
