package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.List;

public class CheckBoxes {
    WebDriver driver = new ChromeDriver();

    @BeforeTest
    public void initialSetup() {
        driver.get("https://admin:admin@the-internet.herokuapp.com/checkboxes");
        driver.manage().window().maximize();
    }

    @AfterTest
    public void quit() {
        driver.quit();
    }

    // Check which checkbox is selected
    @Test(priority = 1)
    public void checkSelectedCheckbox(){
        boolean checkbox1 = driver.findElement(By.xpath("(//input[@type = 'checkbox'])[1]")).isSelected();
        Assert.assertFalse(checkbox1);

        boolean checkbox2 = driver.findElement(By.xpath("(//input[@type = 'checkbox'])[2]")).isSelected();
        Assert.assertTrue(checkbox2);
    }

    // Select Checkbox 1
    @Test(priority = 2)
    public void selectCheckbox1(){
        WebElement checkbox1 = driver.findElement(By.xpath("(//input[@type = 'checkbox'])[1]"));
        checkbox1.click();
        boolean elementSelected = checkbox1.isSelected();
        Assert.assertTrue(elementSelected);
    }

    //unselect all checkboxes
    @Test(priority = 3)
    public void unselectAllCheckbox(){
        List<WebElement> checkboxes = driver.findElements(By.xpath("//form[@id='checkboxes']"));

        for (WebElement checkbox : checkboxes){
            checkbox.click();
            boolean elementSelected = checkbox.isSelected();
            Assert.assertFalse(elementSelected);
        }

    }
}
