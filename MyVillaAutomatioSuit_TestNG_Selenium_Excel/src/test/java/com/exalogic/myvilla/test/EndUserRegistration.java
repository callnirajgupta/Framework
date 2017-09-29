package com.exalogic.myvilla.test;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.exalogic.myvilla.pagefactory.LoginPO;
import com.exalogic.myvilla.pagefactory.WelcomePagePO;
import com.exalogic.myvilla.util.CommonUtil;
import com.exalogic.myvilla.util.MyVillaConstant;
import com.exalogic.myvilla.util.SeleniumUtil;
import com.exalogic.myvilla.util.Xls_Reader;


public class EndUserRegistration {
	 private static final Logger LOGGER=LogManager.getLogger(EndUserRegistration.class);
	 SeleniumUtil seleniumUtil;
	 private Xls_Reader xlread;
	 
	@BeforeMethod
	public void beforeMethod(){
		LOGGER.info("inside before method");
		seleniumUtil= SeleniumUtil.getInstance();
		 xlread=new Xls_Reader();
	}
	
	
	//represents individual test cases
	@Test
	public void endUserRegistration(){
		LOGGER.info("user is logged into the application"+LOGGER.getClass().getCanonicalName());
		int rowNum=xlread.searchTestScenario("TestData", "endUserRegistration");
		String runMode=xlread.getCellData("TestData", "RequiredToExecute",rowNum );
		
		CommonUtil.validateRunmodes("endUserRegistration", "TestData", runMode);
	
		String userName=xlread.getCellData("TestData", "UserName", rowNum);
		
		String userEmail=xlread.getCellData("TestData", "UserEmail", rowNum);
		System.out.println("################-email-"+userEmail);
		String phoneNumber=xlread.getCellData("TestData", "PhoneNumber", rowNum);
		System.out.println("################phone-"+phoneNumber);
		String password=xlread.getCellData("TestData", "Password", rowNum);
		String confirmPassword=xlread.getCellData("TestData", "ConfirmPassword", rowNum);
		
		//u can put login in @BeforeMethod if ur login is common across all the test cases in the class/scenario/module
		
		CommonUtil.registerEndUser(userName,phoneNumber,userEmail,password,confirmPassword);
		Assert.assertEquals("the registration success message is not matching", MyVillaConstant.REGISTRATION_SUCCESS_MESSAGE, SeleniumUtil.getText(By.id(LoginPO.LOGIN_PAGE_REGISTRATION_SUCCESS_MESSAGE_ID)));
		LoginPO.clickBackToLogin();
		LoginPO.loginToApplication(xlread.getCellData("TestData", "UserEmail", rowNum),xlread.getCellData("TestData", "Password", rowNum));
		
		SeleniumUtil.ValidateWebElementPresence(By.xpath(WelcomePagePO.WELCOMEPAGE_WELCOME_USER_XPATH.replace("replace", xlread.getCellData("TestData", "UserName", rowNum))));
		LOGGER.debug("############  LOGIN SUCCESSFUL for user -   ########################--" +"Admin");
		
		CommonUtil.logOut();
	
		
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
