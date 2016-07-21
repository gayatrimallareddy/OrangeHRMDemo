package app.directoryTests;

import org.testng.annotations.Test;

import app.directoryMethods.Directory_Page;

public class Directory_Search {
	
	Directory_Page directoryObj = new Directory_Page();
	
	@Test(priority = 1)
	public void searchByName() throws Exception {
		directoryObj.Login("admin");
		directoryObj.MenuBar("Directory");
		directoryObj.verifyEmployees();
		directoryObj.searchUser("Linda Anderson", null, null);
		directoryObj.searchButton();
		Thread.sleep(2000);
		System.out.println();
	}
	
	@Test(priority = 3)
	public void searchByLocation() throws Exception {
		directoryObj.resetButton();
		directoryObj.verifyLocation();
		directoryObj.searchUser(null , null, "    Texas R&D");
		directoryObj.searchButton();
		Thread.sleep(2000);
		System.out.println();
	}
	
	@Test(priority = 2)
	public void searchByTitle() throws Exception {
		directoryObj.resetButton();
		directoryObj.verifyTitles();
		directoryObj.searchUser(null, "HR Manager", null);
		directoryObj.searchButton();
		Thread.sleep(2000);
		System.out.println();
	}
	
	@Test(priority = 4)
	public void searchByNameTitle() throws Exception{
		directoryObj.resetButton();
		directoryObj.verifyTitles();
		directoryObj.searchUser("Linda Anderson", "HR Manager", null);
		directoryObj.searchButton();
		Thread.sleep(2000);
		System.out.println();
	}
	
	@Test(priority = 6)
	public void searchByNameLocation() throws Exception{
		directoryObj.resetButton();
		directoryObj.verifyTitles();
		directoryObj.searchUser("Linda Anderson", null, "    Texas R&D");
		directoryObj.searchButton();
		Thread.sleep(2000);
		System.out.println();
	}
	
	@Test(priority = 5)
	public void searchByTitleLocation() throws Exception{
		directoryObj.resetButton();
		directoryObj.verifyTitles();
		directoryObj.searchUser(null, "HR Manager", "    Texas R&D");
		Thread.sleep(2000);
		System.out.println();
	}
	
	@Test(priority = 7)
	public void searchByNameTitleLocation() throws Exception{
		directoryObj.resetButton();
		directoryObj.verifyTitles();
		directoryObj.searchUser("Linda Anderson", "HR Manager", "    Texas R&D");
		directoryObj.searchButton();
		Thread.sleep(2000);
		directoryObj.Logout();
		directoryObj.CloseBrowser();
	}
}
