package ru.ozon.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class SearсhResultsPage extends BasePage{
    /**
     * Поиск поля ввода цены "до"
     * @author Алехнович Александр
     */
    @FindBy(xpath = "//div[contains(@class,'filter-block')]//div[contains(text(),'Цена')]/..//p[normalize-space(text())='до']//preceding-sibling::input")
    private WebElement inputPriceUpTo;

    /**
     * Поиск checkBox "Высокий рейтинг"
     * @author Алехнович Александр
     */
    @FindBy(xpath = "//div[contains(@class,'filter-block')]//span[contains(text(),'Высокий рейтинг')]/../../../input")
    private WebElement checkBoxHighRating;

    /**
     * Поиск кнопки "Найти"
     * @author Алехнович Александр
     * @param product - значение вводимое в поле поиска
     * @return SearсhResultsPage - переходим на страницу результатов поиска {@link SearсhResultsPage}
     */
    public SearсhResultsPage menuSetting(String product) {
        inputPriceUpTo.sendKeys("150000");
        checkBoxHighRating.click();
        return pageManager.getSearсhResultsPage();
    }
}
