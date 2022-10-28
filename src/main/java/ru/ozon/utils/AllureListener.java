package ru.ozon.utils;


import io.qameta.allure.Attachment;
import io.qameta.allure.junit5.AllureJunit5;
import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import ru.ozon.managers.DriverManager;

/**
 * Класс следит за тестом (при падении теста делается скриншот)
 *
 * @author Алехнович Александр
 */

public class AllureListener extends AllureJunit5 implements AfterTestExecutionCallback {

    @Attachment(value = "screnshot", type="image/png", fileExtension = "png")
    public static byte[] getScreenshot() {
        return ((TakesScreenshot) DriverManager.getDriverManager().getDriver()).getScreenshotAs(OutputType.BYTES);
    }
    @Override
    public void afterTestExecution(ExtensionContext extensionContext) throws Exception {
        if(extensionContext.getExecutionException().isPresent()){
           getScreenshot();
        }
    }
}
