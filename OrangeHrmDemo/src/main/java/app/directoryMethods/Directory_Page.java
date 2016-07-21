package app.directoryMethods;

import java.util.List;

import org.apache.poi.ss.usermodel.Sheet;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Reporter;

import com.reusableMethods.Excel_Methods;

import app.reusableMethods.Application_Methods;

public class Directory_Page extends Application_Methods{
	
	Excel_Methods excelObj = new Excel_Methods();
	
	public void searchUser(String name, String title, String location) {
		init();
		if(name!=null) {
			if(cssSelector("nameSearchCss").isDisplayed()){
				cssSelector("nameSearchCss").sendKeys(name);
				Reporter.log("Name Has Been Entered.");
				if(name.equalsIgnoreCase(cssSelector("empListCss").getText())){
					Reporter.log(name+" Has Been Displayed.");
				}
				else{
					Reporter.log(name+" Is Not Displayed.");
				}
			}
			else{
				Reporter.log("Name Filed Doesn't Exists.");
			}
		}
		
		if(title!=null) {
			if(cssSelector("userTitleCss").isDisplayed()){
				WebElement titles = cssSelector("userTitleCss");
				Select select = new Select(titles);
				select.selectByVisibleText(title);
				Reporter.log("Title Has Been Selected.");
			}
			else{
				Reporter.log("Title Dropdown Field Doesn't Exists.");
			}
		}
		
		if(location!=null){
			if(cssSelector("userTitleCss").isDisplayed()){
				WebElement locations = cssSelector("userLocationCss");
				Select select = new Select(locations);
				select.selectByVisibleText(location);
				Reporter.log("Location Has Been Selected.");
			}
			else{
				Reporter.log("Location Dropdown Field Doesn't Exists.");
			}
		}
	}

	public void nameField(String name) {
		init();
		if(cssSelector("nameSearchCss").isDisplayed()){
			cssSelector("nameSearchCss").sendKeys(name);
			Reporter.log("Name Has Been Entered.");
			if(name.equalsIgnoreCase(cssSelector("empListCss").getText())){
				Reporter.log(name+" Has Been Displayed.");
			}
			else{
				Reporter.log(name+" Is Not Displayed.");
			}
		}
		else{
			Reporter.log("Name Filed Doesn't Exists.");
		}
	}
	
	public void titleList(String userTitle) {
		init();
		if(cssSelector("userTitleCss").isDisplayed()){
			WebElement titles = cssSelector("userTitleCss");
			Select select = new Select(titles);
			select.selectByVisibleText(userTitle);
			Reporter.log("Title Has Been Selected.");
		}
		else{
			Reporter.log("Title Dropdown Field Doesn't Exists.");
		}
	}
	
	public void locationList(String userLocation) {
		init();
		if(cssSelector("userLocationCss").isDisplayed()){
			WebElement locations = cssSelector("userLocationCss");
			Select select = new Select(locations);
			select.selectByVisibleText(userLocation);
			Reporter.log("Location Has Been Selected.");
		}
		else{
			Reporter.log("Location Dropdown Field Doesn't Exists.");
		}
	}
	
	public void searchButton() {
		init();
		if(cssSelector("searchButtonCss").isDisplayed()){
			cssSelector("searchButtonCss").click();
			Reporter.log("Search Button Clicked");
		}
		else{
			Reporter.log("Search Button Doesn't Exists.");
		}
	}
	
	public void resetButton() {
		init();
		if(cssSelector("resetButtonCss").isDisplayed()){
			cssSelector("resetButtonCss").click();
			Reporter.log("Reset Button Clicked");
		}
		else{
			Reporter.log("Reset Button Doesn't Exists");
		}
	}
	
	public void verifyEmployees() throws Exception {
		init();
		List<WebElement> employees = cssSelectorElements("empListCss");
		Sheet sheet = excelObj.Read_Excel_Sheet("OrangeHRM.xlsx", "Directory");
		String[] excelEmployees = excelObj.Read_Column(sheet, "Name");
		int i=0;
		for(WebElement employee : employees){
			String empName = employee.getText();
			if(empName.equalsIgnoreCase(excelEmployees[i])){
				System.out.println(empName + " Is Displayed.");
				Reporter.log(empName + " Is Displayed.");
			}
			else{
				System.out.println(empName + " Is Displayed As " + excelEmployees[i]);
				Reporter.log(empName + " Is Displayed As " + excelEmployees[i]);
			}
			i++;
		}
	}
	
	public void verifyTitles() throws Exception {
		init();
		List<WebElement> titles = cssSelectorElements("titlesListCss");
		Sheet sheet = excelObj.Read_Excel_Sheet("OrangeHRM.xlsx", "Directory");
		String[] excelTitles = excelObj.Read_Column(sheet, "Title");
		int i=0;
		for(WebElement titleElement : titles){
			String title = titleElement.getText();
			if(title.equalsIgnoreCase(excelTitles[i])){
				System.out.println(title + " Is Displayed.");
				Reporter.log(title + " Is Displayed.");
			}
			else{
				System.out.println(title + " Is Displayed As " + excelTitles[i]);
				Reporter.log(title + " Is Displayed As " + excelTitles[i]);
			}
			i++;
		}
	}
	
	public void verifyLocation() throws Exception {
		init();
		List<WebElement> locations = cssSelectorElements("locationsListCss");
		Sheet sheet = excelObj.Read_Excel_Sheet("OrangeHRM.xlsx", "Directory");
		String[] excelLocations = excelObj.Read_Column(sheet, "Location");
		int i=0;
		for(WebElement locationElement : locations) {
			String location = locationElement.getText();
			if(location.contains(excelLocations[i])){
				System.out.println(location + " Is Displayed.");
				Reporter.log(location + " Is Displayed.");
			}
			else{
				System.out.println(location + " Is Displayed As " + excelLocations[i]);
				Reporter.log(location + " Is Displayed As " + excelLocations[i]);
			}
		}
	}
	
}
