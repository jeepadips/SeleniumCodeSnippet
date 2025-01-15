package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.awt.*;
import java.time.Duration;

public class ExitIntent {
    WebDriver driver = new ChromeDriver();

    @BeforeTest
    public void initialSetup() {
        driver.get("https://admin:admin@the-internet.herokuapp.com/exit_intent");
        driver.manage().window().maximize();
    }

    @AfterTest
    public void quit() {
        driver.quit();
    }


    @Test
    public void mouseMove() throws AWTException {

        // Mouse Movement using Robot Class
        Robot robot = new Robot();
        robot.mouseMove(150,150);
        robot.mouseMove(125,125);

        driver.findElement(By.xpath("//p[contains(text(),'Close')]")).isDisplayed();

        String modalWindowTitle = driver.findElement(By.xpath("//div[@id='ouibounce-modal']//h3")).getText();
        Assert.assertEquals(modalWindowTitle, "THIS IS A MODAL WINDOW");

        driver.findElement(By.xpath("//p[contains(text(),'Close')]")).click();
    }




}
