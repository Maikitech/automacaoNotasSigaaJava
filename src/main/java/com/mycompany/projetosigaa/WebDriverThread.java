package com.mycompany.projetosigaa;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.devtools.DevTools; 

public class WebDriverThread {

    private WebDriver driver;
    private DevTools devTools; 

    public WebDriverThread() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized", "--incognito", "--remote-allow-origins=*");
        this.driver = new ChromeDriver(options);
    }

    public WebDriver getDriver() {
        return this.driver;
    }

    public DevTools getDevTools() {
        return this.devTools;
    }

    public void quitDriver() {
        if (this.driver != null) {
            this.driver.quit();
        }
    }
}
