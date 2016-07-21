package com.reusableMethods;

import java.io.FileInputStream;
import java.util.List;
import java.util.Properties;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class General_Methods {
		
	public static WebDriver driver;
	FileInputStream fis = null;
	public Properties config = new Properties();
	public Properties OR = new Properties();
	
	public void init() {
		try {
			FileInputStream fisConfig = new FileInputStream(System.getProperty("user.dir")+"\\src\\app\\property\\files\\config.properties");
			FileInputStream fisOr = new FileInputStream(System.getProperty("user.dir")+"\\src\\app\\property\\files\\OR.properties");
			config.load(fisConfig);
			OR.load(fisOr);
		} catch (Exception E) {
			System.out.println("Exception : " + E);
		}
	}
	
	public void browser(){
		init();
		if(config.getProperty("browserType").equals("Firefox")) {
			driver = new FirefoxDriver();
		}
		else if(config.getProperty("browserType").equals("Chrome")) {
			driver = new ChromeDriver();
		}
		else if(config.getProperty("browserType").equals("IE")) {
			driver = new InternetExplorerDriver();
		}
		else{
			System.out.println("Please choose Firefox or Chrome or IE");
		}
		driver.manage().window().maximize();
	}
	
	public WebElement id(String idVar){
		try {
			return driver.findElement(By.id(OR.getProperty(idVar)));
		}
		catch(Exception E) {
			System.out.println("Exception : " + E);
			return null;
		}
	}
	
	public WebElement name(String nameVar){
		try {
			return driver.findElement(By.name(OR.getProperty(nameVar)));
		}
		catch(Exception E) {
			System.out.println("Exception : " + E);
			return null;
		}
	}
	
	public WebElement className(String classNameVar){
		try {
			return driver.findElement(By.className(OR.getProperty(classNameVar)));
		}
		catch(Exception E) {
			System.out.println("Exception : " + E);
			return null;
		}
	}
	
	public WebElement linkText(String linkTextVar){
		try {
			return driver.findElement(By.linkText(OR.getProperty(linkTextVar)));
		}
		catch(Exception E) {
			System.out.println("Exception : " + E);
			return null;
		}
	}
	
	public WebElement cssSelector(String cssSelectorVar){
		try {
			return driver.findElement(By.cssSelector(OR.getProperty(cssSelectorVar)));
		}
		catch(Exception E) {
			System.out.println("Exception : " + E);
			return null;
		}
	}
	
	public List<WebElement> cssSelectorElements(String cssSelectorVar){
		try {
			return driver.findElements(By.cssSelector(OR.getProperty(cssSelectorVar)));
		}
		catch(Exception E) {
			System.out.println("Exception : " + E);
			return null;
		}
	}
	
	public WebElement xpath(String xpathVar){
		try {
			return driver.findElement(By.xpath(OR.getProperty(xpathVar)));
		}
		catch(Exception E) {
			System.out.println("Exception : " + E);
			return null;
		}
	}
	
	public void CloseBrowser(){
		driver.quit();
	}
	
}
