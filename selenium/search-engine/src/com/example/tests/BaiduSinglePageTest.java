package com.example.tests;

import static com.example.tests.WebDriverUtil.*;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Node;
import org.jsoup.select.NodeVisitor;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Test the Baidu Search Website
 * @author ngpanwei
 */
public class BaiduSinglePageTest {
	private static WebDriver driver;
	private static String baseUrl;
	private static StringBuffer verificationErrors = new StringBuffer();
	private static boolean acceptNextAlert = true;

	@BeforeClass
	public static void searchWithBaidu() throws Exception {
		driver = new FirefoxDriver();
		baseUrl = "http://www.baidu.com/";
		driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
		driver.get(baseUrl);
		driver.findElement(By.id("kw1")).clear();
		driver.findElement(By.id("kw1")).sendKeys("Selenium");
		driver.findElement(By.id("su1")).click();
	}

	@Before
	public void resetTimeouts() {
		driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
	}
	/**
	 * Verify that there are search 10 results.
	 * @throws Exception
	 */
	@Test
	public void verifySearchResults() throws Exception {
		List<WebElement> elements = driver.findElements(By.id("content_left")) ;
		verifyThat("div id='content_left' must exist",elements.size()==1) ;
		WebElement contentElement = elements.get(0) ;
		List<WebElement> resultElements = contentElement.findElements(By.className("c-container")) ;
		verifyThat("There must be 10 results on this page with class='c-content'",resultElements.size()==10) ;
		for(WebElement resultElement : resultElements) {
			String xPath = getElementXPath(driver, resultElement) ;
			System.err.println(xPath) ;
			driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
			WebElement resultTitleElement = resultElement.findElement(By.className("t")) ;
			System.err.println("\t"+resultTitleElement.getText()) ;
		}
	}
	/**
	 * Test the content of a sea search result.
	 * @throws Exception
	 */
	@Test
	public void verifySearchResultData() throws Exception {
		WebElement resultElement = driver.findElement(By.id("5")) ;
		System.err.println(resultElement.getText());
		String innerHTML = getElementInnerHTML(driver, resultElement) ;
		Document doc = Jsoup.parse(innerHTML) ;
		List<Node> nodes = new ArrayList<Node>() ;
		doc.traverse(new NodeVisitor() {
			public void head(Node node, int count) {
				System.err.println("+++ " + node.nodeName() + " " + count) ;
				// TODO Auto-generated method stub
			}
			public void tail(Node arg0, int arg1) {
				// TODO Auto-generated method stub
			}
		}) ;
	}
	@Test
	public void verifySearchAbstract() throws Exception {
		List<WebElement> elements = driver.findElements(By.id("content_left")) ;
		verifyThat("div id='content_left' must exist",elements.size()==1) ;
		WebElement contentElement = elements.get(0) ;
		List<WebElement> resultElements = contentElement.findElements(By.className("c-abstract")) ;
		System.err.println("Abstacts "+resultElements.size());
		verifyThat("There must be more than 1 abstracts on this page with class='c-abstract'",resultElements.size()>1) ;
		for(WebElement resultElement : resultElements) {
			String xPathTitle = getElementXPath(driver, resultElement) ;
			System.err.println(xPathTitle) ;
		}
	}
	@Test
	public void verifyLinks() throws Exception {
		assertTrue(isElementPresent(By
				.linkText("Selenium - Web Browser Automation")));
	}

	@AfterClass
	public static void tearDown() throws Exception {
		driver.quit();
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			fail(verificationErrorString);
		}
	}


	private boolean isElementPresent(By by) {
		try {
			driver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	private boolean isAlertPresent() {
		try {
			driver.switchTo().alert();
			return true;
		} catch (NoAlertPresentException e) {
			return false;
		}
	}

	private String closeAlertAndGetItsText() {
		try {
			Alert alert = driver.switchTo().alert();
			String alertText = alert.getText();
			if (acceptNextAlert) {
				alert.accept();
			} else {
				alert.dismiss();
			}
			return alertText;
		} finally {
			acceptNextAlert = true;
		}
	}
}
