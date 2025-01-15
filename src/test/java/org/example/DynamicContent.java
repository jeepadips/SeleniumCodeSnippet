package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class DynamicContent {
    WebDriver driver = new ChromeDriver();

    @BeforeTest
    public void initialSetup() {
        driver.get("https://admin:admin@the-internet.herokuapp.com/dynamic_content");
        driver.manage().window().maximize();
    }

    @AfterTest
    public void quit() {
        driver.quit();
    }


    @Test(priority = 1)
    public void verifyImageChangesOnRefresh(){
        String imgPathBeforeRefresh = driver.findElement(By.xpath("(//div[@class='large-2 columns'])[1]//img"))
                .getAttribute("src");
        driver.navigate().refresh();

        String imgPathAfterRefresh = driver.findElement(By.xpath("(//div[@class='large-2 columns'])[1]//img"))
                .getAttribute("src");

        Assert.assertNotNull(imgPathBeforeRefresh,imgPathAfterRefresh);
    }

    // Expecting Stale element Reference Execption to occur
    @Test(expectedExceptions = { StaleElementReferenceException.class})
    public void exceptionTest(){
        WebElement imgPathBeforeRefresh = driver.findElement(By.xpath("(//div[@class='large-2 columns'])[1]//img"));
        imgPathBeforeRefresh.getAttribute("src");
        driver.navigate().refresh();
        imgPathBeforeRefresh.getAttribute("src");
    }


}
