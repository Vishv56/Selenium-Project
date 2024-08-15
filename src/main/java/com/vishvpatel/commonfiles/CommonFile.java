package com.vishvpatel.commonfiles;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;


public class CommonFile 
{
	public static WebDriver driver;
	public static ExtentReports extent;
	public static ExtentSparkReporter spark;
	public static ExtentTest test;
	public static Properties prop;



	public static void chromeDriverIn()
	{
		driver=new ChromeDriver();
		driver.manage().window().maximize();
	}

	public static void edgeDriverIn()
	{
		driver=new EdgeDriver();
		driver.manage().window().maximize();
	}
	public static void firefocDriverIn()
	{
		driver=new FirefoxDriver();
		driver.manage().window().maximize();
	}

	public static void openBrowser(String browser) 
	{
		if (browser.equalsIgnoreCase("chrome"))
		{
			driver = new ChromeDriver();
			driver.manage().window().maximize();
		}
		else if (browser.equalsIgnoreCase("edge"))
		{
			driver = new EdgeDriver();

			driver.manage().window().maximize();
		}
		else if (browser.equalsIgnoreCase("firefox"))
		{
			driver = new FirefoxDriver();

			driver.manage().window().maximize();
		}
	}

	public static void loadConfigFile() throws IOException 
	{
		prop = new Properties();
		File file = new File("./config/config.properties");
		FileInputStream fis = new FileInputStream(file); 
		prop.load(fis);
	}

	public static void impWait(int waitSeconds)
	{
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(waitSeconds));
	}

	public static String getCellData(String excelFilePath,String sheetName ,int rowNum, int colNum) throws IOException
	{
		FileInputStream fs = new FileInputStream(excelFilePath);
		try (XSSFWorkbook workbook = new XSSFWorkbook(fs)) {
			XSSFSheet sheet =  workbook.getSheet(sheetName);
			XSSFCell cell = sheet.getRow(rowNum).getCell(colNum);
			DataFormatter formatter = new DataFormatter();
			String str = formatter.formatCellValue(cell);
			fs.close();
			if(str=="")
			{
				System.out.println("Value not found for index : " +rowNum+ ", "+colNum );
			}
			return str;
		}
	}

	public static void setupreport()
	{

		extent = new ExtentReports();
		spark = new ExtentSparkReporter("MySpark.html");
		extent.attachReporter(spark);
		//test =extent.createTest("Login Test Report");
		//extent.attachReporter(spark);

	}
	public static String takeScreenshot(WebDriver driver, String screenshotName) 
	{
		// Take screenshot and store it as a file format
		File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

		// Define the destination file path
		String destFile = System.getProperty("user.dir") + "/screenshots/" + screenshotName + ".png";

		// Copy the screenshot to the destination file
		try {
			FileUtils.copyFile(srcFile, new File(destFile));
		} catch (IOException e) {
			e.printStackTrace();
		}

		// Return the path of the screenshot
		return destFile;
	}


	public static void attScreenShort(ITestResult result) throws InterruptedException
	{
		if(result.getStatus()==1)
		{
			String methodName= result.getMethod().getMethodName() + "Pass";
			Thread.sleep(2000);
			takeScreenshot(driver,methodName);
			test.pass("Test Case Is Pass");
			test.addScreenCaptureFromPath("./screenshots/"+ methodName +".png");
		}
		else
		{
			String methodName= result.getMethod().getMethodName() + "Fail";
			Thread.sleep(2000);
			takeScreenshot(driver,methodName);
			test.addScreenCaptureFromPath("./screenshots/"+ methodName +".png");
			test.log(Status.FAIL, result.getThrowable());

		}

	}

}
