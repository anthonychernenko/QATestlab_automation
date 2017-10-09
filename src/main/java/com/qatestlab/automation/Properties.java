package com.qatestlab.automation;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

class Properties {
    static WebDriver getDriver() {
        try {
            return new ChromeDriver();
        } catch (Exception e) {
            throw new UnsupportedOperationException("Method doesn't return WebDriver instance");
        }
    }

    static String getURL() {
        return "http://prestashop-automation.qatestlab.com.ua/admin147ajyvk0/";
    }

    static WebDriver getWrappedDriver() {
        WebDriver driver = getDriver();
        EventFiringWebDriver wrappedDriver = new EventFiringWebDriver(driver);

        wrappedDriver.register(new EventHandler());

        return wrappedDriver;
    }
}
