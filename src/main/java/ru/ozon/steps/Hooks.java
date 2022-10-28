package ru.ozon.steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import ru.ozon.managers.DriverManager;
import ru.ozon.managers.InitManager;
import ru.ozon.managers.TestPropManager;

import static ru.ozon.utils.PropConst.BASE_URL;

public class Hooks {

    private final DriverManager driverManager = DriverManager.getDriverManager();

    @Before
    public void before() {
        InitManager.initFramework();
        driverManager.getDriver().get(TestPropManager.getTestPropManager().getProperty(BASE_URL));

    }


    @After
    public void after() {
        InitManager.quitFramework();
    }
}
