package com.ebanking.TestCases;

import static org.testng.Assert.assertEquals;

import java.sql.Driver;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.ebankind.pageObjects.Loginpage;

import junit.framework.Assert;

//@Listeners(packagesListners.ListnersTesting.class)
public class TC_LoginPageTest extends TestBase {

	Loginpage lnp;
	
	@Test
	public void loginpages123() throws InterruptedException
	{
		driver.get(url);
		lnp = new Loginpage(driver);
		
		lnp.setusername(username1);
		lnp.setpassword(password1);

		lnp.click();
		
		System.out.println(driver.getTitle());
		assertEquals(driver.getTitle(), "GTPL Bank Manager HomePage", "the title is not matching");
	}

	
	  
	
	 @Test() 
	 public void loginWithInvalidCredentials() 
	 { 
		 //Loginpage lnp1 = new	  Loginpage(driver); 
	  lnp.setusername(userName2); 
	  lnp.setpassword(password2);
	  lnp.click();
	  driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	  String alerttext = driver.switchTo().alert().getText();
	  System.out.println("The alert text message will display -"+alerttext);
	  
	  }
	 

	@Test()
	public void test12() {
		System.out.println("Inside the test method test12");
		Assert.assertEquals(true,true);
		System.out.println("Inside the test method test12");
	}

}
