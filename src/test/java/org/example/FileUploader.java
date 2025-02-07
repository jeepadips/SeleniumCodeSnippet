package org.example;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;
import java.time.Duration;
import java.util.Hashtable;
import java.util.Map;

public class FileUploader {
    WebDriver driver = new ChromeDriver();

    @BeforeTest
    public void initialSetup() {



        driver.get("https://admin:admin@the-internet.herokuapp.com/upload");
        driver.manage().window().maximize();


    }

    @AfterTest
    public void quit() {
        driver.quit();
    }


    @Test(priority = 1)
    public void uploadDocument() {
        String fileName = "some-file.txt";
        driver.findElement(By.id("file-upload")).sendKeys("C:/Users/kavic/Downloads/" + fileName);
        driver.findElement(By.id("file-submit")).click();

        String uploadedFileName = driver.findElement(By.id("uploaded-files")).getText();
        Assert.assertEquals(uploadedFileName, fileName);
    }

    @Test(priority = 2)
    public void dropFile(){


        String fileName = "some-file.txt";
        WebElement droparea = driver.findElement(By.id("drag-drop-upload"));

        DropFile(new File("C:/Users/kavic/Downloads/" + fileName), droparea, 0, 0);

        String uploadedFilename = driver.findElement(By.xpath("//span[contains(text(),'some-file.txt')]")).getText();

        Assert.assertEquals(uploadedFilename, fileName);
    }

    public static void DropFile(File filePath, WebElement target, int offsetX, int offsetY) {
        if(!filePath.exists())
            throw new WebDriverException("File not found: " + filePath.toString());

        WebDriver driver = ((RemoteWebElement)target).getWrappedDriver();
        JavascriptExecutor jse = (JavascriptExecutor)driver;
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        String JS_DROP_FILE =
                "var target = arguments[0]," +
                        "    offsetX = arguments[1]," +
                        "    offsetY = arguments[2]," +
                        "    document = target.ownerDocument || document," +
                        "    window = document.defaultView || window;" +
                        "" +
                        "var input = document.createElement('INPUT');" +
                        "input.type = 'file';" +
                        "input.style.display = 'none';" +
                        "input.onchange = function () {" +
                        "  var rect = target.getBoundingClientRect()," +
                        "      x = rect.left + (offsetX || (rect.width >> 1))," +
                        "      y = rect.top + (offsetY || (rect.height >> 1))," +
                        "      dataTransfer = { files: this.files };" +
                        "" +
                        "  ['dragenter', 'dragover', 'drop'].forEach(function (name) {" +
                        "    var evt = document.createEvent('MouseEvent');" +
                        "    evt.initMouseEvent(name, !0, !0, window, 0, 0, 0, x, y, !1, !1, !1, !1, 0, null);" +
                        "    evt.dataTransfer = dataTransfer;" +
                        "    target.dispatchEvent(evt);" +
                        "  });" +
                        "" +
                        "  setTimeout(function () { document.body.removeChild(input); }, 25);" +
                        "};" +
                        "document.body.appendChild(input);" +
                        "return input;";

        WebElement input =  (WebElement)jse.executeScript(JS_DROP_FILE, target, offsetX, offsetY);
        input.sendKeys(filePath.getAbsoluteFile().toString());
        wait.until(ExpectedConditions.stalenessOf(input));
    }


}
