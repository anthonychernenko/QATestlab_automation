package com.qatestlab.automation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

public class LoginTest {

    public static void main(String[] args) throws InterruptedException {
        WebDriver driver = Properties.getDriver();
        driver.get(Properties.getURL());
        driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);

        login(driver);
        logout(driver);

        driver.quit();
    }

    static void login(WebDriver driver) {
        driver.findElement(By.id("email")).sendKeys("webinar.test@gmail.com");
        driver.findElement(By.id("passwd")).sendKeys("Xcg7299bnSmMuRLp9ITw");
        driver.findElement(By.className("btn")).click();
    }

    private static void logout(WebDriver driver) {
        driver.findElements(By.className("imgm")).get(0).click();
        driver.findElement(By.id("header_logout")).click();
    }
}
