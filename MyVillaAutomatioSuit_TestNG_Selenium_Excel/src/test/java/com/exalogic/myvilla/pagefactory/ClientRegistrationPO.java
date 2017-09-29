package com.exalogic.myvilla.pagefactory;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;

import com.exalogic.myvilla.util.SeleniumUtil;

public class ClientRegistrationPO {
	private static final Logger LOGGER=LogManager.getLogger(ClientRegistrationPO.class);
	
	public static final String CLIENT_REGISTRATION_PAGE_REGISTARTION_BUTTON_XPATH="//a[contains(text(),'REGISTER WITH US')]";
	public static final String CLIENT_REGISTRATION_PAGE_REGISTARTION_TEXT_XPATH="//div[@id='register']/h3";
	public static final String CLIENT_REGISTRATION_PAGE_COMPANYNAME_TEXTBOX_ID="company-name";
    public static final String CLIENT_REGISTRATION_PAGE_FIRSTNAME_TEXTBOX_ID="first-name";
    public static final String CLIENT_REGISTRATION_PAGE_LASTNAME_TEXTBOX_ID="last-name";
    public static final String CLIENT_REGISTRATION_PAGE_EMAIL_TEXTBOX_ID="email";
    public static final String CLIENT_REGISTRATION_PAGE_PASSWORD_TEXTBOX_ID="password";
    public static final String CLIENT_REGISTRATION_PAGE_CONFIRMPASSWORD_TEXTBOX_ID="con-password";
    public static final String CLIENT_REGISTRATION_PAGE_PHONE_TEXTBOX_ID ="phone";
    public static final String CLIENT_REGISTRATION_PAGE_COUNTRY_XPATH="//button[@title='Select Country']";
    public static final String CLIENT_REGISTRATION_PAGE_REGISTER_BUTTON_ID="registrationBtn";
    
    public static void fillRegistartionForm(String companyName,String firstName,String lastName, String email,String password,String confirmPassword,String phone,String country){
    	
    	if (companyName!=null){
			SeleniumUtil.setValue(By.id(CLIENT_REGISTRATION_PAGE_COMPANYNAME_TEXTBOX_ID), companyName);
		}
		if (firstName!=null){
			SeleniumUtil.setValue(By.id(CLIENT_REGISTRATION_PAGE_FIRSTNAME_TEXTBOX_ID), firstName);				
		}
		
		/*if (lastName!=null){
			SeleniumUtil.setValue(By.id(CLIENT_REGISTRATION_PAGE_LASTNAME_TEXTBOX_ID), lastName);			
		}*/
		if (email!=null){
			SeleniumUtil.setValue(By.id(CLIENT_REGISTRATION_PAGE_EMAIL_TEXTBOX_ID), email);			
		}
		/*if (password!=null){
			SeleniumUtil.setValue(By.id(CLIENT_REGISTRATION_PAGE_PASSWORD_TEXTBOX_ID), password);			
		}
		
		if (confirmPassword!=null){
			SeleniumUtil.setValue(By.id(CLIENT_REGISTRATION_PAGE_CONFIRMPASSWORD_TEXTBOX_ID), confirmPassword);			
		}*/
		
		if (phone!=null){
			SeleniumUtil.setValue(By.id(CLIENT_REGISTRATION_PAGE_PHONE_TEXTBOX_ID), phone);			
		}
		
		/*if (country!=null){
			
			SeleniumUtil.selectWebList(By.xpath(CLIENT_REGISTRATION_PAGE_COUNTRY_XPATH), country,"selectByVisibleText");			
		}*/
		SeleniumUtil.clickWebElement(By.id(CLIENT_REGISTRATION_PAGE_REGISTER_BUTTON_ID));
		
		SeleniumUtil.wait(2000);
  
    }
    
  public static void clickRegisterWithUs(){
	  LOGGER.info("############# inside clickRegisterWithUs method ");
	  SeleniumUtil.clickWebElement(By.xpath(CLIENT_REGISTRATION_PAGE_REGISTARTION_BUTTON_XPATH));
	  
	  
  }
}
