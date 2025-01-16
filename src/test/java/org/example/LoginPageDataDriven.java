package org.example;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.*;
import java.time.Duration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;

public class LoginPageDataDriven {
    static WebDriver driver = new ChromeDriver();
    static XSSFRow row;
    static XSSFCell cell;
    static int columnIndex;
    static DataFormatter formatedData;
    static Select conDropDown;
    static String arrOfDOB[];
    static Random randomNum;
    static WebDriverWait wait;
    HashMap<Integer, String> result = new HashMap<>();

    @BeforeTest
    public void initialSetup() {
        driver.get("https://the-internet.herokuapp.com/login");
        driver.manage().window().maximize();
    }

    @AfterTest
    public void quit() {
        driver.quit();
    }



    // Methods to wait for Element to be clickable
    public static void elementToBeClickable(By expression){
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(expression));
    }


    // Reading from and xlsx, passing it to login page and storing the result
    @SuppressWarnings("resource")
    @Test(priority = 1)
    public void login() throws IOException {
        //  Creating an input stream to the xlsx file

        InputStream excelFileToRead = new FileInputStream(System.getProperty("user.dir")+"//"+"test-data.xlsx");

        XSSFWorkbook workbook = new XSSFWorkbook(excelFileToRead);
        XSSFSheet sheet = workbook.getSheetAt(0);

        Iterator<Row> rows = sheet.iterator();

        // loop through all the rows in the xlsx file
        while(rows.hasNext()){
            row = (XSSFRow) rows.next();
            Iterator<Cell> cells = row.iterator();

            if(row.getRowNum() == 0){
                continue; // skip first row as it contains column names
            }

            // loop through all the cells in the row
            while(cells.hasNext()){
                cell = (XSSFCell) cells.next();
                formatedData = new DataFormatter();
                columnIndex = cell.getColumnIndex();

                switch (columnIndex){
                    case 0:
                        // username column
                        elementToBeClickable(By.id("username"));
                        driver.findElement(By.id("username")).sendKeys(formatedData.formatCellValue(cell));
                        break;
                    case 1:
                        // Password Column
                        elementToBeClickable(By.id("password"));
                        driver.findElement(By.id("password")).sendKeys(formatedData.formatCellValue(cell));
                        break;
                }
            }
            //Clicking on Login button
            elementToBeClickable(By.xpath("//form[@id='login']//button"));
            driver.findElement(By.xpath("//form[@id='login']//button")).click();

            String flashMessage = driver.findElement(By.id("flash")).getText();

            if(flashMessage.contains("secure")){
                result.put(row.getRowNum(), "Passed");
            } else if (flashMessage.contains("invalid")){
                result.put(row.getRowNum(), "Failed");
            }
        }
        //close the xlsx file
        excelFileToRead.close();
        workbook.close();
    }

    // Writing the result into Xlsx
    @SuppressWarnings("resource")
    @Test(priority = 2)
    public void writeLoginResult() throws IOException {
        File exfile = new File(System.getProperty("user.dir") + "//" + "test-data.xlsx");
        FileInputStream file = new FileInputStream(exfile);

        XSSFWorkbook workbook = new XSSFWorkbook(file);
        XSSFSheet sheet = workbook.getSheetAt(0);

        for (Map.Entry<Integer, String> entry : result.entrySet()) {
            Row row = sheet.getRow(entry.getKey());
            Cell cell = row.getCell(2);

            if (cell == null) {
                cell = row.createCell(2);
            }
            cell.setCellValue(entry.getValue());
        }
        file.close();
        FileOutputStream outFile = new FileOutputStream(exfile);
        workbook.write(outFile);
        outFile.close();
    }

}
