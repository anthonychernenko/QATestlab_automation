package com.qatestlab.automation;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

import static com.qatestlab.automation.LoginTest.login;

public class AddCategoryTest {
    public static void main(String[] args) throws Exception {
        WebDriver driver = Properties.getWrappedDriver();
        driver.get(Properties.getURL());
        driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);

        login(driver);
        addCategory(driver);

        driver.quit();
    }

    private static void addCategory(WebDriver driver) throws Exception {
        WebDriverWait wait = new WebDriverWait(driver, 5);

        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.cssSelector("#subtab-AdminCatalog"))));
        new Actions(driver).moveToElement(driver.findElement(By.cssSelector("#subtab-AdminCatalog"))).build().perform();
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("#subtab-AdminCatalog>ul>#subtab-AdminCategories"))));
        driver.findElement(By.cssSelector("#subtab-AdminCatalog>ul>#subtab-AdminCategories")).click();
        driver.findElement(By.cssSelector("#page-header-desc-category-new_category>.process-icon-new")).click();

        driver.findElement(By.id("name_1")).sendKeys("newCategory");
        WebElement element = driver.findElement(By.id("category_form_submit_btn"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        element.click();

        isCorrect(driver.findElement(By.cssSelector(".bootstrap>.bootstrap>.alert.alert-success")).getText());

        driver.findElements(By.cssSelector(".icon-caret-up")).get(1).click();

        Boolean containCategory = false;

        for (WebElement e : driver.findElements(By.cssSelector("table>tbody>tr>.pointer"))) {
            if (e.getText().contains("newCategory")) containCategory = true;
        }

        if (!containCategory) throw new Exception();
    }

    private static void isCorrect(String alertMessage) throws Exception {
        if (!alertMessage.contains("Создано")) {
            throw new Exception();
        }
    }
}
