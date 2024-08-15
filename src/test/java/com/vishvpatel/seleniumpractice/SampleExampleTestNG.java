package com.vishvpatel.seleniumpractice;

import java.io.IOException;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.vishvpatel.commonfiles.CommonFile;
import com.vishvpatel.orangehrmpages.LoginPageMethodUsingFindBy;

public class SampleExampleTestNG extends CommonFile
{
	String aExcelFilePath;
	String sheetName;
	LoginPageMethodUsingFindBy lp;

	@BeforeClass
	void beforeClass() throws IOException
	{
		setupreport();
		chromeDriverIn();
		loadConfigFile();
		aExcelFilePath=prop.getProperty("TestDataExcelFilePathForOrange");
		sheetName = prop.getProperty("sheetNameForOrangeHrm");
		lp = new LoginPageMethodUsingFindBy(driver);
	}

	@Test
	public void titleVerify() throws InterruptedException, IOException
	{
		test=extent.createTest("Login Test By ITestResult ");

		impWait(10);
		driver.get(prop.getProperty("Orangeurl"));
		test.info("Open URL");

		lp.setUserName(getCellData(aExcelFilePath, sheetName, 0, 1));
		test.info("Enter UserName");

		lp.setPassword(getCellData(aExcelFilePath, sheetName, 1, 1));
		test.info("Enter Password");

		lp.clickSubmitButton();
		test.info("click Submit Button");

		Assert.assertEquals(driver.getCurrentUrl(),"https://opensource-demo.orangehrmlive.com/web/index.php/dashboard/index");
		test.info("Login Success");


	}


	@AfterMethod
	public void afterMethod(ITestResult result) throws InterruptedException
	{
		attScreenShort(result);
	}

	@AfterClass
	public void afterClass()
	{
		extent.flush();
		driver.quit();


	}

}
