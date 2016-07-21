package app.reusableMethods;

import java.util.List;
import org.apache.poi.ss.usermodel.Sheet;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;

import com.reusableMethods.Excel_Methods;
import com.reusableMethods.General_Methods;

public class Application_Methods extends General_Methods{

	Excel_Methods excelObj = new Excel_Methods();
	
	public void Launch() throws Exception {
		browser();
		if(config.getProperty("URL") != null) {
			driver.get(config.getProperty("URL"));
			Reporter.log("Launched Successfully");
		} else{
			System.out.println("Check Your URL in preperties file");
			Reporter.log("Launch Failed");
		}
		
	}

	public void Login(String user) throws Exception {
		Launch();
		Sheet sheet = excelObj.Read_Excel_Sheet("OrangeHRM.xlsx", "Login_Credentials");
		String userName = excelObj.Read_Cell_Data(sheet, user, "User", "UserName");
		String password = excelObj.Read_Cell_Data(sheet, user, "User", "Password");
		String name = excelObj.Read_Cell_Data(sheet, user, "User", "Name");
		id("userNameId").sendKeys(userName);
		id("passwordId").sendKeys(password);
		cssSelector("loginButtonCss").click();
		String visibleText = id("welcomeAdminId").getText();
		if(visibleText.contains(name)) {
			Reporter.log("User Successfully Logged In");
		} 
		else {
			Reporter.log("User Failed To Login");
		}
		//driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		Thread.sleep(3000);
	}
	
	public String[] MenuBar(String menuBarTitle) {
		init();
		String[] subTitles = null;
		List<WebElement> menuBars = cssSelectorElements("menuBarCss");
		for(WebElement menuBar : menuBars){
			String btnText = menuBar.getText();
			if(btnText.equalsIgnoreCase(menuBarTitle)){
				menuBar.click();
				Reporter.log(btnText + " Is Verified.");
				System.out.println(btnText + " Is Verified.");
				List<WebElement> subMenuBars = cssSelectorElements("subMenuBarCss");
				int i=0;
				subTitles =new String[subMenuBars.size()];
				for(WebElement subMenuBar : subMenuBars) {
					subTitles[i] = subMenuBar.getText();
					i++;
				}
				break;
			}
		}
		return subTitles;
	}
	
	public void Logout(){
		init();
		id("welcomeAdminId").click();
		cssSelector("logoutLinkCss").click();
	}
	
}





