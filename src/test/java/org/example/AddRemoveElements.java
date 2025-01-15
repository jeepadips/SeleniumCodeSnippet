package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.List;

public class AddRemoveElements {
    WebDriver driver = new ChromeDriver();

    @BeforeTest
    public void initialSetup() {
        driver.get("https://the-internet.herokuapp.com/add_remove_elements/");
        driver.manage().window().maximize();
    }

    @AfterTest
    public void quit() {
        driver.quit();
    }

    // Check the availability of "Add/Remove Element" button
    @Test(priority = 1)
    public void checkAddButtonAvailability(){
        boolean addButtonDisplayed = driver.findElement(By.xpath("//button[contains(text(),'Add Element')]"))
                .isDisplayed();
        boolean addButtonEnabled = driver.findElement(By.xpath("//button[contains(text(),'Add Element')]")).isEnabled();
        Assert.assertEquals((addButtonDisplayed && addButtonEnabled), true);
    }

    // click on Add Element Button
    @Test(priority = 2)
    public void clickAddButton(){
        driver.findElement(By.xpath("//button[contains(text(),'Add Element')]")).click();
    }

    // Click Add Element Button using Mouse Actions
    @Test(priority = 3)
    public void clickAddButtonUsingMouseActions(){
        WebElement addButton = driver.findElement(By.xpath("//button[contains(text(),'Add Element')]"));
        Actions builder = new Actions(driver);
        Action actions = builder.moveToElement(addButton).click().build();
        actions.perform();
    }

    //Click Add Element Button using Keyboard keys
    @Test(priority = 4)
    public void clickAddButtonUsingKeyboardKeys(){
        WebElement addButton = driver.findElement(By.xpath("//button[contains(text(),'Add Element')]"));

        addButton.sendKeys(Keys.TAB);
        addButton.sendKeys(Keys.ENTER);
    }

    // To Check Availability of Delete Button
    @Test(priority = 5)
    public void isDeleteButtonDisplayed(){
        boolean deleteButton = driver.findElement(By.xpath("//button[@class='added-manually']")).isDisplayed();
        Assert.assertTrue(deleteButton);
    }

    // To count the number of Delete Buttons available
    @Test(priority = 6)
    public void countNumberOfDeleteButton(){
        clickAddButton();
        List<WebElement> listOfDeleteButton = driver.findElements(By.xpath("//button[@class='added-manually']"));
        Assert.assertEquals(listOfDeleteButton.size(), 4);
    }

    // Method to delete the buttons "delete
    @Test(priority = 7)
    public void deleteElement(){
        List<WebElement> listOfDeleteButton = driver.findElements(By.xpath("//button[@class='added-manually']"));

        for (WebElement button : listOfDeleteButton) {
             button.click();
        }
    }
}
