package com.ingcapgemini.wm.module1;

import java.util.List;
import java.util.Map;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.ingcapgemini.wm.common.GlobalStepDefinition;
import com.ingcapgemini.wm.pageobjectmodel.Login_PO;
import com.ingcapgemini.wm.pageobjectmodel.WelcomePage_PO;
import com.ingcapgemini.wm.util.CommonUtil;
import com.ingcapgemini.wm.util.SeleniumUtil;

import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Login {
	
	private static final Logger LOGGER=LogManager.getLogger(Login.class);
	
	WebDriver driver;//use the code driver=SeleniumUtil.getInstance().getDriver(); inside the respective step definition
	
	@Given("^User \"(.*?)\" with password \"(.*?)\" login into the application with correct credentials InlineParameters$")
	public void user_with_password_login_into_the_application(String arg1, String arg2)  {
	    // Write code here that turns the phrase above into concrete actions
		LOGGER.info("user "+arg1+" is logged into the application"+LOGGER.getClass().getCanonicalName());
		CommonUtil.login(arg1,arg2);
		System.out.println("print the title "+SeleniumUtil.getTitle());
		SeleniumUtil.ValidateWebElementPresence(By.cssSelector(WelcomePage_PO.WELCOMEPAGE_SIGNOUT_CSS));
		LOGGER.debug("############  LOGIN SUCCESSFUL for user -   ########################--" +arg1);
		
	   	}

	
	@When("^verify that User successfully log out of the application$")
	public void user_successfully_log_out_of_the_application_after_clicking_on_log_out_button(){
		if(GlobalStepDefinition.getRunMode().equalsIgnoreCase("Y")){
	    CommonUtil.logOut();	    
	  
		}
	   
	}

	
	
	
	@Given("^User \"(.*?)\" login into the application with correct credentials ConfigFileParameterss$")
	public void user_login_into_the_application_with_correct_credentials_ConfigFileParameterss(String arg1) throws Throwable {
		   String user=SeleniumUtil.getConfigProperties().getProperty(arg1);
		   String password=SeleniumUtil.getConfigProperties().getProperty(arg1+"_password");
		    
		   CommonUtil.login(user,password);
		   SeleniumUtil.ValidateWebElementPresence(By.cssSelector(WelcomePage_PO.WELCOMEPAGE_SIGNOUT_CSS));
			LOGGER.debug("############  LOGIN SUCCESSFUL for user -   ########################--" +user);
		
		
	}


	@Given("^User \"(.*?)\" with password \"(.*?)\" login into the application with correct credentials ExamplesParameters$")
	public void user_with_password_login_into_the_application_with_correct_credentials_ExamplesParameters(String arg1, String arg2) throws Throwable {
	 
		CommonUtil.login(arg1,arg2);
		SeleniumUtil.ValidateWebElementPresence(By.cssSelector(WelcomePage_PO.WELCOMEPAGE_SIGNOUT_CSS));
		LOGGER.debug("############  LOGIN SUCCESSFUL for user -   ########################--" +arg1);
		
	}
	
	
	@Given("^User login into the application with as per data given DataTableType_KeyValue$")
	public void user_login_into_the_application_with_as_per_data_given_DataTableType_KeyValue(Map<String, String> table) {
		String userName= table.get("UserName");
		String password= table.get("Password");
		
		CommonUtil.login(userName,password);
		SeleniumUtil.ValidateWebElementPresence(By.cssSelector(WelcomePage_PO.WELCOMEPAGE_SIGNOUT_CSS));
		LOGGER.debug("############  LOGIN SUCCESSFUL for user -   ########################--" +userName);
		
		
	}
	
	
	@Given("^User login into the application with as per data given DataTableType_Matrix$")
	public void user_login_into_the_application_with_as_per_data_given_DataTableType_Matrix(DataTable table) throws Throwable {
		List<List<String>> data = table.raw();
		
		String userName= data.get(1).get(0);
		String password= data.get(1).get(1);
		
		CommonUtil.login(userName,password);
		SeleniumUtil.ValidateWebElementPresence(By.cssSelector(WelcomePage_PO.WELCOMEPAGE_SIGNOUT_CSS));
		LOGGER.debug("############  LOGIN SUCCESSFUL for user -   ########################--" +userName);

			
	}

	
	
	
	
	
	
	@Given("^User login into the application with as per data given DataTableType_SingleColumn$")
	public void user_login_into_the_application_with_as_per_data_given_DataTableType_SingleColumn(List<String> table) throws Throwable {
		
		/*for (String a : table)
			System.out.println("print " + a);*/
		String userName= table.get(1);
		String password= table.get(2);
		
		CommonUtil.login(userName,password);
		SeleniumUtil.ValidateWebElementPresence(By.cssSelector(WelcomePage_PO.WELCOMEPAGE_SIGNOUT_CSS));
		LOGGER.debug("############  LOGIN SUCCESSFUL for user -   ########################--" +userName);

		
		
	}
	
	

	
	
	
	

	
	
	
	
	

}
