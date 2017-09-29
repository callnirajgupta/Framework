package com.exalogic.myvilla.pagefactory;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;

import com.exalogic.myvilla.util.SeleniumUtil;



public class LoginPO {
	
	private static final Logger LOGGER=LogManager.getLogger(LoginPO.class);
	public static final String LOGIN_PAGE_EMAIL_CSS="form#loginFrm>div>input#email";
	public static final String LOGIN_PAGE_PASSWORD_CSS="form#loginFrm>div>input#password";
	public static final String LOGIN_PAGE_SIGNIN_BUTTON_CSS="button#loginBtn";
	public static final String LOGIN_PAGE_REGISTRATION_LINK_CSS ="a.reg";
	public static final String LOGIN_PAGE_REGISTRATION_TEXT_XPATH="//h3[contains(text(),'Register with us for a Personalised Experience!')]";
	public static final String LOGIN_PAGE_REGISTRATION_USERNAME_INPUT_ID="first-name";
	public static final String LOGIN_PAGE_REGISTRATION_PHONE_INPUT_ID="phone";
	public static final String LOGIN_PAGE_REGISTRATION_EMAIL_INPUT_ID="pop-email";
	public static final String LOGIN_PAGE_REGISTRATION_PASSWORD_INPUT_CSS="form#registrationFrm>div>input#password";
	public static final String LOGIN_PAGE_REGISTRATION_CONFIRMPASSWORD_INPUT_ID="password1";
	public static final String LOGIN_PAGE_REGISTRATION_REGISTER_BUTTON_ID="registerBtn";
	public static final String LOGIN_PAGE_REGISTRATION_SUCCESS_MESSAGE_ID="register-message";
	public static final String LOGIN_PAGE_BACKTOLOGIN_BUTTON_XPATH="//div[@class='login']/div/a";
	
	public static void loginToApplication(String userName, String password){
		LOGGER.info("############# inside Login_PO method ###################");
		if (userName!=null){
			SeleniumUtil.setValue(By.cssSelector(LOGIN_PAGE_EMAIL_CSS), userName);
			
		}
		if (password!=null){
			SeleniumUtil.setValue(By.cssSelector(LOGIN_PAGE_PASSWORD_CSS), password);			
			
		}
		SeleniumUtil.clickWebElement(By.cssSelector(LOGIN_PAGE_SIGNIN_BUTTON_CSS));
		
		SeleniumUtil.wait(2000);
	}
	
	public static void clickRegistrationLink(){
		LOGGER.info("############# inside clickRegistrationLink method ###################");
		SeleniumUtil.clickWebElement(By.cssSelector(LOGIN_PAGE_REGISTRATION_LINK_CSS));
		
	}
	
	public static void registrationEndUser(String userName,String phoneNumber,String UserEmail, String password,String confirmPassword){
		LOGGER.info("############# inside Login_PO method ###################");
		if (userName!=null){
			SeleniumUtil.setValue(By.id(LOGIN_PAGE_REGISTRATION_USERNAME_INPUT_ID), userName);
		}
		if (phoneNumber!=null){
			SeleniumUtil.setValue(By.id(LOGIN_PAGE_REGISTRATION_PHONE_INPUT_ID), phoneNumber);				
		}
		
		if (UserEmail!=null){
			SeleniumUtil.setValue(By.id(LOGIN_PAGE_REGISTRATION_EMAIL_INPUT_ID), UserEmail);			
		}
		if (password!=null){
			SeleniumUtil.setValue(By.cssSelector(LOGIN_PAGE_REGISTRATION_PASSWORD_INPUT_CSS), password);			
		}
		if (confirmPassword!=null){
			SeleniumUtil.setValue(By.id(LOGIN_PAGE_REGISTRATION_CONFIRMPASSWORD_INPUT_ID), confirmPassword);			
		}
		SeleniumUtil.clickWebElement(By.id(LOGIN_PAGE_REGISTRATION_REGISTER_BUTTON_ID));
		
		SeleniumUtil.wait(2000);
	}
	
	public static void clickBackToLogin(){
		SeleniumUtil.clickWebElement(By.xpath(LOGIN_PAGE_BACKTOLOGIN_BUTTON_XPATH));	
	}
}
