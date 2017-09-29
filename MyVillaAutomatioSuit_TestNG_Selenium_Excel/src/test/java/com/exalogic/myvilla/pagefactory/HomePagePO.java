package com.exalogic.myvilla.pagefactory;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;

import com.exalogic.myvilla.util.SeleniumUtil;


public class HomePagePO {
	
	private static final Logger LOGGER=LogManager.getLogger(HomePagePO.class);
	public static final String HOME_PAGE_LOGO_CSS="a.navbar-brand>img";  
	public static final String HOME_PAGE_TITLE="MyVilla.com - Dubai’s top villas";
	public static final String HOME_PAGE_GUEST_LOGIN_CSS="li#login-logout>a>span.no-mobile";
	public static final String HOME_PAGE_CLIENT_REGISTRATION_LINK_XPATH="//a[contains(text(),'Client Registration')]";
	
	
	public static void clickGuestLoginHomePage(){		
		LOGGER.info("########### Inside clickSignIn_HomePage ###########");
		SeleniumUtil.clickWebElement(By.cssSelector(HOME_PAGE_GUEST_LOGIN_CSS));	
	}
	
	public static void clickClientRegistrationHomePage(){		
		LOGGER.info("########### clickClientRegistrationHomePage ###########");
		SeleniumUtil.scrollToViewElement(By.xpath(HOME_PAGE_CLIENT_REGISTRATION_LINK_XPATH));
		SeleniumUtil.clickWebElement(By.xpath(HOME_PAGE_CLIENT_REGISTRATION_LINK_XPATH));	
	}
	

}


