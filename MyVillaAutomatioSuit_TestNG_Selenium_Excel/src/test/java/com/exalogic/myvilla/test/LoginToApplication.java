package com.exalogic.myvilla.test;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.exalogic.myvilla.pagefactory.WelcomePagePO;
import com.exalogic.myvilla.util.CommonUtil;
import com.exalogic.myvilla.util.SeleniumUtil;
import com.exalogic.myvilla.util.Xls_Reader;


public class LoginToApplication {
	 private static final Logger LOGGER=LogManager.getLogger(LoginToApplication.class);
	 SeleniumUtil seleniumUtil;
	 private Xls_Reader xlread;
	 
	@BeforeMethod
	public void beforeMonth(){
		LOGGER.info("inside before method");
		seleniumUtil= SeleniumUtil.getInstance();
		 xlread=new Xls_Reader();
	}
	
	@Test
	public void logintest(){
		LOGGER.info("user is logged into the application"+LOGGER.getClass().getCanonicalName());

		int rowNum=xlread.searchTestScenario("TestData", "logintest");
		String runMode=xlread.getCellData("TestData", "RequiredToExecute",rowNum );
		
		CommonUtil.validateRunmodes("logintest", "TestData", runMode);
		LOGGER.info("read excel");
		
		CommonUtil.login(xlread.getCellData("TestData", "UserEmail", rowNum),xlread.getCellData("TestData", "Password", rowNum));
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
