package com.exalogic.myvilla.test;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.exalogic.myvilla.pagefactory.ClientRegistrationPO;
import com.exalogic.myvilla.pagefactory.HomePagePO;
import com.exalogic.myvilla.pagefactory.LoginPO;
import com.exalogic.myvilla.pagefactory.WelcomePagePO;
import com.exalogic.myvilla.util.CommonUtil;
import com.exalogic.myvilla.util.MyVillaConstant;
import com.exalogic.myvilla.util.SeleniumUtil;
import com.exalogic.myvilla.util.Xls_Reader;

public class AgentRegistration {

	 private static final Logger LOGGER=LogManager.getLogger(AgentRegistration.class);
	 SeleniumUtil seleniumUtil;
	 private Xls_Reader xlread;
	 
	@BeforeMethod
	public void beforeMethod(){
		LOGGER.info("inside before method");
		seleniumUtil= SeleniumUtil.getInstance();
		 xlread=new Xls_Reader();
	}
	

	@Test
	public void clientRegistration(){
		LOGGER.info("user is logged into the application"+LOGGER.getClass().getCanonicalName());
		int rowNum=xlread.searchTestScenario("TestData", "clientRegistration");
		String runMode=xlread.getCellData("TestData", "RequiredToExecute",rowNum );
		
		CommonUtil.validateRunmodes("clientRegistration", "TestData", runMode);
	
		String companyName=xlread.getCellData("TestData", "CompanyName", rowNum);
		String firstName=xlread.getCellData("TestData", "FirstName", rowNum);
		String lastName=xlread.getCellData("TestData", "LastName", rowNum);
		String userEmail=xlread.getCellData("TestData", "UserEmail", rowNum);
		String phoneNumber=xlread.getCellData("TestData", "PhoneNumber", rowNum);
		String password=xlread.getCellData("TestData", "Password", rowNum);
		String confirmPassword=xlread.getCellData("TestData", "ConfirmPassword", rowNum);
		String country=xlread.getCellData("TestData", "Country", rowNum);
		
		CommonUtil.navigateToHomePage();
		HomePagePO.clickClientRegistrationHomePage();
		ClientRegistrationPO.clickRegisterWithUs();
		Assert.assertEquals("the registration text is not displaying", MyVillaConstant.REGISTRATION_TEXT, SeleniumUtil.getText(By.xpath(ClientRegistrationPO.CLIENT_REGISTRATION_PAGE_REGISTARTION_TEXT_XPATH)));
		ClientRegistrationPO.fillRegistartionForm(companyName, firstName, lastName, userEmail, password, confirmPassword, phoneNumber, country);
		LoginPO.loginToApplication(xlread.getCellData("TestData", "UserEmail", rowNum),xlread.getCellData("TestData", "Password", rowNum));
		
		//SeleniumUtil.ValidateWebElementPresence(By.xpath(WelcomePagePO.WELCOMEPAGE_WELCOME_USER_XPATH.replace("replace", xlread.getCellData("TestData", "UserName", rowNum))));
		//LOGGER.debug("############  LOGIN SUCCESSFUL for user -   ########################--" +"Admin");
		
		//CommonUtil.logOut();
	
		
	}
	
	
	
	@AfterMethod
	public void afterMethod(){
		if (SeleniumUtil.driverStatus==true){
		System.out.println("######################  i am inside kill browser ############################");
	SeleniumUtil.closeBrowser();
	SeleniumUtil.setDriver(null);
	seleniumUtil=null;
	}
	}

	
	
}
