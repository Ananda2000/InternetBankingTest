package com.ebankind.pageObjects;

import java.sql.Driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Loginpage {
	
	WebDriver ldriver;
	
	public Loginpage(WebDriver driver){
		ldriver = driver;
		PageFactory.initElements(ldriver, this);
		
	}

	
		@FindBy (xpath="//input[@name ='uid']")	
		WebElement username;
		
		@FindBy(xpath="//input[@name ='password']")
		WebElement password;
		
		@FindBy(xpath="//input[@name ='btnLogin']")
		WebElement loginbutton;
		
		
		
		public void setusername (String usn) {
			username.sendKeys(usn);
			
		}
		
		public void setpassword (String pwd) {
			password.sendKeys(pwd);
		}
		
		public void click() {
			loginbutton.click();
		}
}
