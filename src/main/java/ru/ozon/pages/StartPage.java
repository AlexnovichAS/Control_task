package ru.ozon.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class StartPage extends BasePage{
    /**
     * Поиск поля ввода
     * @author Алехнович Александр
     */
    @FindBy(xpath = "//input[@placeholder='Искать на Ozon']")
    private WebElement input;

    /**
     * Поиск кнопки "Найти"
     * @author Алехнович Александр
     */
    @FindBy(xpath = "//button[@type='submit']")
    private WebElement search;

    /**
     * Поиск кнопки "Найти"
     * @author Алехнович Александр
     * @param product - значение вводимое в поле поиска
     * @return SearсhResultsPage - переходим на страницу результатов поиска {@link SearсhResultsPage}
     */
    public StartPage find(String product) {
        input.sendKeys(product);
        search.click();
        return this;
    }
}
