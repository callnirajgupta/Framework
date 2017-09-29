package com.exalogic.myvilla.pagefactory;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;

import com.exalogic.myvilla.util.SeleniumUtil;



public class WelcomePagePO {
	public static final String WELCOMEPAGE_WELCOME_USER_XPATH="//div/h3[contains(text(),'replace')]";
	public static final String WELCOMEPAGE_LOGOUT_CSS="a>span.fa.fa-power-off";// XPATH //a[contains(text(),'Sign out')]
	public static final String WELCOMEPAGE_ENDUSER_OPTION_CSS="span.fa.fa-angle-down";
	public static final String WELCOMEPAGE_SEARCH_ID="search_query_top";
	public static final String WELCOMEPAGE_SEARCH_CSS="button[name='submit_search']"; //XPATH "//button[@name='submit_search']"
	private static final Logger LOGGER=LogManager.getLogger(WelcomePagePO.class);
	
	public static void clickLogOutOut(){
		LOGGER.info("######  Inside clickSignOut ##############");
		SeleniumUtil.getWebElement(By.cssSelector(WELCOMEPAGE_ENDUSER_OPTION_CSS)).click();
		SeleniumUtil.ValidateWebElementVisible(By.cssSelector(WELCOMEPAGE_LOGOUT_CSS));
		SeleniumUtil.getWebElement(By.cssSelector(WELCOMEPAGE_LOGOUT_CSS)).click();
	}
	
	public static void searchItem(String item){
		
		LOGGER.info("######  Inside searchItem ##############");
		SeleniumUtil.getWebElement(By.id(WELCOMEPAGE_SEARCH_ID)).sendKeys(item);
		SeleniumUtil.getWebElement(By.cssSelector(WELCOMEPAGE_SEARCH_CSS)).click();
		
	}

}
