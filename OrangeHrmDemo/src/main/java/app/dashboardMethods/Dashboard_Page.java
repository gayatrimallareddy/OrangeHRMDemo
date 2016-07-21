package app.dashboardMethods;

import java.util.List;

import org.apache.poi.ss.usermodel.Sheet;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;

import com.reusableMethods.Excel_Methods;

import app.reusableMethods.Application_Methods;

public class Dashboard_Page extends Application_Methods{

	Excel_Methods excelObj = new Excel_Methods();

	public void dashboard() throws Exception{
		String[] subMenus = MenuBar("Dashboard");
		Sheet sheet = excelObj.Read_Excel_Sheet("OrangeHRM.xlsx", "Menu_Bar");
		String[] excelSubMenus = excelObj.Read_Column(sheet, "Dashboard");
		if(subMenus.length!=0){
			for(int i=0;i<subMenus.length;i++){
				if(subMenus[i].equals(excelSubMenus[i])){
					System.out.println(subMenus[i] + " Is Verified.");
				}
				else {
					System.out.println(subMenus[i] + " Is Not Verified.");
				}
			}
		}
	}

	public void legend() throws Exception{
		List<WebElement> legends = cssSelectorElements("legendsCss");
		Sheet sheet = excelObj.Read_Excel_Sheet("OrangeHRM.xlsx", "Dashboard");
		String[] excelLegends = excelObj.Read_Column(sheet, "Legends");
		int i=0;
		for(WebElement legend : legends){
			String legendText = legend.getText();
			if(legendText.equalsIgnoreCase(excelLegends[i])){
				Reporter.log(legendText + " Is Verified.");
				System.out.println(legendText + " Is Verified.");
			}
			else{
				Reporter.log(legendText + " Is Not Verified.");
				System.out.println(legendText + " Is Not Verified.");
			}
			i++;
		}
	}

}

