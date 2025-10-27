package com.mycompany.projetosigaa;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;

public class BaseTest extends DriverFactory {

    protected WebDriver driver;
    protected WebDriverWait wait;

    @BeforeMethod(alwaysRun = true)
    public void setUp() throws Exception {
        // Pega o driver do Factory antes de cada método de teste
        this.driver = DriverFactory.getDriver();
        // Inicializa o WebDriverWait
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }
}
