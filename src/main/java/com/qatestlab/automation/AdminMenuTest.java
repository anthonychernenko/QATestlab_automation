package com.qatestlab.automation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

public class AdminMenuTest {

    public static void main(String[] args) throws Exception {
        WebDriver driver = Properties.getDriver();
        driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
        driver.get(Properties.getURL());

        LoginTest.login(driver);

        String menuElements= "nav>ul>li>a>span";
        for (int i = 0; i < driver.findElements(By.cssSelector(menuElements)).size(); i++) {
            driver.findElements(By.cssSelector(menuElements)).get(i).click();
            String title = driver.getTitle();
            String url = driver.getCurrentUrl().split("&token")[0];
            System.out.println(title);
            driver.navigate().refresh();
            isCorrect(title, driver.getTitle(), url, driver.getCurrentUrl().split("&token")[0]);
        }

        driver.quit();
    }

    private static void isCorrect(String oldTitle, String currentTitle, String oldURL, String newURL) throws Exception {
        if (!oldTitle.equals(currentTitle) || !oldURL.equals(newURL)) {
            throw new Exception();
        }
    }
}
