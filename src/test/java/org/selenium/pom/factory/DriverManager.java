package org.selenium.pom.factory;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;
import java.time.Duration;

public class DriverManager {

    public WebDriver initializeDriver(){
        WebDriverManager.chromedriver().cachePath("Drivers").setup();
        ChromeOptions options = new ChromeOptions();
        options.addExtensions(new File("/home/morore/Downloads/extension_4_6_9_0.crx"));

        WebDriver driver = new ChromeDriver(options);
        driver.manage().window().maximize();
//
//        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        return driver;
    }
}
