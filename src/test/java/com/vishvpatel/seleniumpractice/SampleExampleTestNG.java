package com.vishvpatel.seleniumpractice;

import java.io.IOException;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.vishvpatel.commonfiles.CommonFile;

public class SampleExampleTestNG extends CommonFile
{
	@BeforeClass
	void beforeClass()
	{
		setupreport();
		edgeDriverIn();
	}

	@Test
	public void titleVerify() throws InterruptedException, IOException
	{
  
		test=extent.createTest("Title Check");
		
		driver.get("https://www.google.com/");
		test.pass("Open URL");
		
		driver.manage().window().maximize();
		test.pass("windo Max");
		
		String title = driver.getTitle();
		test.pass("Get Title");
		
		Assert.assertEquals(title,"Google");
		test.pass("title is same");
		
		Thread.sleep(2000);
		

	}
	@Test
	public void titleVerify1() throws InterruptedException, IOException
	{
  
		test=extent.createTest("Title Check1");
		
		driver.get("https://www.google.com/");
		test.pass("Open URL");
		
		driver.manage().window().maximize();
		test.pass("windo Max");
		
		String title = driver.getTitle();
		test.pass("Get Title");
		
		Assert.assertEquals(title,"Google");
		test.pass("title is same");
		
		Thread.sleep(2000);
		

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
