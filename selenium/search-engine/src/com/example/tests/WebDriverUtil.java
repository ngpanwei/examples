package com.example.tests;

import static org.junit.Assert.assertTrue;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * A set of utilities to interact with the browser.
 * @author ngpanwei
 */
public class WebDriverUtil {
	/**
	 * Get the innerHTML of an element.
	 * @param driver
	 * @param element
	 * @return the innerHTML
	 * @see http://stackoverflow.com/questions/7263824/get-html-source-of-webelement-in-selenium-webdriver-python
	 */
	public static String getElementInnerHTML(WebDriver driver, WebElement element) {
		return (String)((JavascriptExecutor)driver).executeScript(
				"return arguments[0].innerHTML;", element);
	}
	/**
	 * Get XPath of an Element
	 * @param driver
	 * @param element
	 * @return
	 * @see http://stackoverflow.com/questions/4176560/webdrvier-get-elements-xpath
	 */
	public static String getElementXPath(WebDriver driver,WebElement element)
	{
	    return (String) ((JavascriptExecutor) driver).executeScript(
	    "getXPath=function(node)" +
	    "{" +
	        "if (node.id !== '')" +
	        "{" +
	            "return '//' + node.tagName.toLowerCase() + '[@id=\"' + node.id + '\"]'" +
	        "}" +

	        "if (node === document.body)" +
	        "{" +
	            "return node.tagName.toLowerCase()" +
	        "}" +

	        "var nodeCount = 0;" +
	        "var childNodes = node.parentNode.childNodes;" +

	        "for (var i=0; i<childNodes.length; i++)" +
	        "{" +
	            "var currentNode = childNodes[i];" +

	            "if (currentNode === node)" +
	            "{" +
	                "return getXPath(node.parentNode) + '/' + " + 
	                        "node.tagName.toLowerCase() + "+
	                        "'[' + (nodeCount+1) + ']'" +
	            "}" +

	            "if (currentNode.nodeType === 1 && " +
	                "currentNode.tagName.toLowerCase() === node.tagName.toLowerCase())" +
	            "{" +
	                "nodeCount++" +
	            "}" +
	        "}" +
	    "};" +

	    "return getXPath(arguments[0]);", element);
	}
	public static void verifyThat(String message,boolean check) {
		assertTrue(message,check) ;
		System.err.println("Verification OK : "+message) ;
		
	}	
}
