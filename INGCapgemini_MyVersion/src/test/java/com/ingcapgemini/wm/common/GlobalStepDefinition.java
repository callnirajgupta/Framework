package com.ingcapgemini.wm.common;

import java.util.Collection;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import com.ingcapgemini.wm.pageobjectmodel.WelcomePage_PO;
//import com.ingcapgemini.wm.story.leave.ApplyLeave;
import com.ingcapgemini.wm.util.CommonUtil;
//import com.ingcapgemini.wm.util.SeleniumUtil;
import com.ingcapgemini.wm.util.SeleniumUtil;
import com.ingcapgemini.wm.util.Xls_Reader;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import gherkin.formatter.model.Feature;

public class GlobalStepDefinition {
	 
	 SeleniumUtil seleniumUtil;
	 
	private static final Logger LOGGER=LogManager.getLogger(GlobalStepDefinition.class);
	public static int rowNum;
	public static String sheetName;
	private Xls_Reader xlread;
	private static String runMode;
	public static Scenario scenario;
	String userName;
	static int count=0;
	 @Before(order = 0) 
	public void Setup(Scenario scenario) {
		 
		seleniumUtil= SeleniumUtil.getInstance();
		System.out.println("before setup");
		//GlobalStepDefinition.scenario=scenario;
		System.out.println("print sceanrio Name"+scenario.getName());
		count++;System.out.println("print count"+count);
		
		/*String scenarioName = scenario.getName();
		System.out.println("print scenario name " + scenarioName);

		Collection<String> col = scenario.getSourceTagNames();
		// Iterator it= col.iterator();
		for (String tagname : col) {

			System.out.println("print tagname " + tagname);

		}
*/
		
		sheetName=scenario.getId().split(";")[0];
		// String scenarioName=scenario.getId().split(";")[1];

		String scenarioName=scenario.getName();
		 System.out.println("print featureName name-" + sheetName);
		System.out.println("print scenario name-" + scenarioName);

		 xlread=new Xls_Reader();
		rowNum=xlread.searchTestScenario(sheetName.toUpperCase(), scenarioName);
		System.out.println("print the row number "+rowNum);
		
		 runMode=xlread.getCellData(sheetName.toUpperCase(), "RunMode", rowNum);
		 
		 userName=xlread.getCellData(sheetName.toUpperCase(), "UserName", rowNum);
		/*if(runMode.equalsIgnoreCase("N")){
		throw new SkipException("Skipping the test "+scenarioName+" inside the suite "+ sheetName);
		}*/
	  
	 }
	 
	 @Before("@ScenarioTablemutiplecolumn") 
		public void Setup1(Feature feature) {
			 
			System.out.println("print feature name"+feature);
		  
		 }
		 
	 
	 
	 
	 
	
	 @Given("^user \"(.*?)\" login into the application with correct credentials$")
		public void user_with_password_login_into_the_application_DataTable(String arg1)  {
		    // Write code here that turns the phrase above into concrete actions
		 //good to have...gets fulls path of the class
		 if(runMode.equalsIgnoreCase("Y")){
			 scenario.write(" inside annotation");
		 
			LOGGER.info("user "+arg1+" is logged into the application"+LOGGER.getClass().getCanonicalName());
			int row=xlread.searchTestScenario("Login", arg1);
			String userName=xlread.getCellData("Login", "UserName", row);
			String password=xlread.getCellData("Login", "Password", row);
			CommonUtil.login(userName,password);
			SeleniumUtil.ValidateWebElementPresence(By.cssSelector(WelcomePage_PO.WELCOMEPAGE_SIGNOUT_CSS));
			LOGGER.debug("############  LOGIN SUCCESSFUL for user -   ########################--" +arg1);
			
		 }
			
		   	}
	
	 
	 
	 @Given("^User \"(.*?)\" with password \"(.*?)\" login into the application with correct credentials$")
		public void user_with_password_login_into_the_application(String arg1, String arg2)  {
		    // Write code here that turns the phrase above into concrete actions
		 //good to have...gets fulls path of the class
			LOGGER.info("user "+arg1+" is logged into the application"+LOGGER.getClass().getCanonicalName());		
			
			CommonUtil.login(arg1,arg2);
			SeleniumUtil.ValidateWebElementPresence(By.cssSelector(WelcomePage_PO.WELCOMEPAGE_SIGNOUT_CSS));
			LOGGER.debug("############  LOGIN SUCCESSFUL for user -   ########################--" +arg1);
			
			
			
		   	}
		
		@When("^User successfully log out of the application after clicking on log out button$")
		public void user_successfully_log_out_of_the_application_after_clicking_on_log_out_button()  {
		    CommonUtil.logOut();
		   
		}
		
		
		
	 
	 
	/*	@Given("^User \"(.*?)\" with password \"(.*?)\" login into the application$")
		public void user_with_password_login_into_the_application(String arg1, String arg2) throws Throwable {
		    // Write code here that turns the phrase above into concrete actions
			LOGGER.info("user "+arg1+" is logged into the application"+LOGGER.getClass().getCanonicalName());
			CommonUtil.login(arg1,arg2);
			
		   
		}*/
		
		
	
		/*@Given("^user \"(.*?)\" is logged into the application$")
		public void user_is_logged_into_the_application(String userId) throws Throwable {
		    // Write code here that turns the phrase above into concrete actions
			LOGGER.info("user "+userId+" is logged into the application"+LOGGER.getClass().getCanonicalName());
			CommonUtil.login(userId,pwd);
				
		    
		}*/	
	 
	@After(order = 0)
	public void TearDown(Scenario scenario) {
		System.out.println("Tear down"+scenario.getId());
		if (SeleniumUtil.driverStatus==true){
		if (scenario.isFailed()) {
			System.out.println("###################  i am inside screen shot after fail ####################");
			final byte[] screenshot = ((TakesScreenshot) seleniumUtil.getDriver()).getScreenshotAs(OutputType.BYTES);
			scenario.embed(screenshot, "image/png"); // stick it in the report
		}
		
		
		}
		
		scenario.write("hello");
		
		//set driver object to null
		if (SeleniumUtil.driverStatus==true){
			System.out.println("######################  i am inside kill browser ############################");
		SeleniumUtil.closeBrowser();
		SeleniumUtil.setDriver(null);
		}
		
	}

	public static String getRunMode() {
		return runMode;
	}
	

	
}
