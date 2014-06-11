package com.example.tests;

import static com.example.tests.WebDriverUtil.getElementXPath;
import static com.example.tests.WebDriverUtil.verifyThat;
import static org.junit.Assert.fail;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BaiduMultiPageTest {
	private static final String SEARCH_KEY = "Selenium IDE Java Driver";
	private static WebDriver driver;
	private static String baseUrl;
	private static StringBuffer verificationErrors = new StringBuffer();

	@BeforeClass
	public static void searchWithBaidu() throws Exception {
		driver = new FirefoxDriver();
		baseUrl = "http://www.baidu.com/";
		driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
		driver.get(baseUrl);
		driver.findElement(By.id("kw1")).clear();
		driver.findElement(By.id("kw1")).sendKeys(SEARCH_KEY);
		driver.findElement(By.id("su1")).click();
	}
	
	@Test
	public void verifySearchResults() throws Exception {
		int page = 0 ;
		int count = 0 ;
		while(page++<10) {
			WebDriverWait wait = new WebDriverWait(driver,120);
			wait.until(ExpectedConditions.elementToBeClickable(By.linkText("下一页>")));
			List<WebElement> resultElements = driver.findElements(By.className("c-container")) ;
			verifyThat("There must be 10 results on this page with class='c-content'",resultElements.size()==10) ;
			for(WebElement resultElement : resultElements) {
				String xPath = getElementXPath(driver, resultElement) ;
				System.err.println(++count+" "+xPath) ;
				WebElement resultTitleElement = resultElement.findElement(By.className("t")) ;

				xPath = getElementXPath(driver, resultTitleElement) ;
				wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(xPath))) ;
				System.err.println("\t"+resultTitleElement.getText()) ;
			}
			driver.findElement(By.linkText("下一页>")).click() ;
		}
	}
	@AfterClass
	public static void tearDown() throws Exception {
		driver.quit();
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			fail(verificationErrorString);
		}
	}	
}
