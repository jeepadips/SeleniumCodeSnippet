package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class DropDown {
    WebDriver driver = new ChromeDriver();

    @BeforeTest
    public void initialSetup() {
        driver.get("https://admin:admin@the-internet.herokuapp.com/dropdown");
        driver.manage().window().maximize();
    }

    @AfterTest
    public void quit() {
        driver.quit();
    }


    @Test(priority = 1)
    public void selectDropdownUsingSelectByValue(){
        WebElement dropdownElement = driver.findElement(By.id("dropdown"));
        Select dropdown = new Select(dropdownElement);

        dropdown.selectByValue("1");

        String attribute = driver.findElement(By.xpath("//option[contains(text(),'Option 1')]"))
                .getAttribute("selected");
        Assert.assertEquals(attribute, "true");
    }

    @Test(priority = 2)
    public void selectDropdownUsingSelectByIndex(){
        WebElement dropdownElement = driver.findElement(By.id("dropdown"));
        Select dropdown = new Select(dropdownElement);

        dropdown.selectByIndex(2);

        String attribute = driver.findElement(By.xpath("//option[contains(text(),'Option 2')]"))
                .getAttribute("selected");
        Assert.assertEquals(attribute, "true");
    }

    @Test(priority = 3)
    public void selectDropdownUsingSelectByVisibleText(){
        WebElement dropdownElement = driver.findElement(By.id("dropdown"));
        Select dropdown = new Select(dropdownElement);

        dropdown.selectByVisibleText("Option 1");

        String attribute = driver.findElement(By.xpath("//option[contains(text(),'Option 1')]"))
                .getAttribute("selected");
        Assert.assertEquals(attribute, "true");
    }

}
