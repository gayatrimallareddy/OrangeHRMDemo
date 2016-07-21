package app.dashboardTests;

import org.testng.annotations.Test;

import app.dashboardMethods.Dashboard_Page;

public class Dashboard_Legend_Verification {
	
	Dashboard_Page dashboardObj = new Dashboard_Page();
	
	@Test
	public void legendVerification() throws Exception {
		dashboardObj.Login("admin");
		dashboardObj.dashboard();
		Thread.sleep(2000);
		dashboardObj.legend();
		dashboardObj.Logout();
		dashboardObj.CloseBrowser();
	}
}
