package com.exalogic.myvilla.util;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.SkipException;

import com.exalogic.myvilla.pagefactory.HomePagePO;
import com.exalogic.myvilla.pagefactory.LoginPO;
import com.exalogic.myvilla.pagefactory.WelcomePagePO;


public class CommonUtil { 
	
	private static final Logger LOGGER = LogManager.getLogger(CommonUtil.class);
		
    static WebDriver driver;
	
	public static void logOut(){
		
		LOGGER.info("############  INSIDE LOGOUT  ########################");
		//sign out could be different in diff pages...u can driver.findelement(by)....and pass the locator to this sign out method
		WelcomePagePO.clickLogOutOut();
		SeleniumUtil.ValidateWebElementVisible(By.cssSelector(HomePagePO.HOME_PAGE_GUEST_LOGIN_CSS));
		LOGGER.debug("############  LOGOUT SUCCESSFUL  ########################");
		
				
		
	}

	public  static void login(String userId, String password) {		
		LOGGER.info("############  INSIDE LOGIN ########################");
		driver=SeleniumUtil.getInstance().getDriver();//if u need  to it..
		SeleniumUtil.maximizeBrowser();
		SeleniumUtil.ImplicitWait();		
		SeleniumUtil.launchApplication();
		Assert.assertEquals("the page title is not matching", HomePagePO.HOME_PAGE_TITLE, SeleniumUtil.getTitle());		
		HomePagePO.clickGuestLoginHomePage();
		SeleniumUtil.ValidateWebElementPresence(By.cssSelector(LoginPO.LOGIN_PAGE_EMAIL_CSS));
	
		LoginPO.loginToApplication(userId, password);		
		
		
	}
	
	public  static void registerEndUser(String userName,String phone,String userEmail,String password,String confirmPassword) {		
		LOGGER.info("############  registerEndUser ########################");
		driver=SeleniumUtil.getInstance().getDriver();//if u need  to it..
		SeleniumUtil.maximizeBrowser();
		SeleniumUtil.ImplicitWait();		
		SeleniumUtil.launchApplication();
		Assert.assertEquals("the page title is not matching", HomePagePO.HOME_PAGE_TITLE, SeleniumUtil.getTitle());		
		HomePagePO.clickGuestLoginHomePage();
		SeleniumUtil.ValidateWebElementPresence(By.cssSelector(LoginPO.LOGIN_PAGE_EMAIL_CSS));
		LoginPO.clickRegistrationLink();
		SeleniumUtil.ValidateWebElementVisible(By.xpath(LoginPO.LOGIN_PAGE_REGISTRATION_TEXT_XPATH));	
		LoginPO.registrationEndUser(userName,phone,userEmail,password,confirmPassword);	
		//Assert.assertEquals("the registration success message is not matching", MyVillaConstant.REGISTRATION_SUCCESS_MESSAGE, SeleniumUtil.getText(By.id(LoginPO.LOGIN_PAGE_REGISTRATION_SUCCESS_MESSAGE_ID)));	
			
	}

	
	public static void navigateToHomePage(){
		LOGGER.info("############  navigateToHomePage ########################");
		driver=SeleniumUtil.getInstance().getDriver();//if u need  to it..
		SeleniumUtil.maximizeBrowser();
		SeleniumUtil.ImplicitWait();		
		SeleniumUtil.launchApplication();
		Assert.assertEquals("the page title is not matching", HomePagePO.HOME_PAGE_TITLE, SeleniumUtil.getTitle());
	}
	
	
	public static void clientRegistartion(){
		
	}
	public static void validateRunmodes(String testName,String suiteName,String dataRunmode){
		LOGGER.debug("Validating runmode for "+testName+" in suite "+ suiteName);
		if(!(dataRunmode.equalsIgnoreCase("Y") || dataRunmode.equalsIgnoreCase("YES") )){
			LOGGER.debug("Skipping the test "+testName+" inside the suite "+ suiteName);
			throw new SkipException("Skipping the test "+testName+" inside the suite "+ suiteName);
		}
		
	}
	
	


}
