package com.ebanking.Utilities;

import java.io.FileInputStream;
import java.util.Properties;

public class ReadConfig {
	
	Properties prop;
	
	public ReadConfig() {
		
		try {
		FileInputStream src = new FileInputStream("./Configurations/Config.properties");
				prop = new Properties();
		prop.load(src);
		} catch (Exception e) {
			System.out.println("Error message -"+ e.getMessage());
			
		}
		
		
	}
	
	public String baseUrl() {
		String url =prop.getProperty("baseurl");
		return url;
		
	}

	public String username () {
		String uname = prop.getProperty("username1");
		return uname;
	}
	
	public String pwd () {
		String passwd = prop.getProperty("password1");
		return passwd;
	}

public String userName1() {
	String uname1=prop.getProperty("username1");
	return uname1;
}

public String passWord2() {
	String pwd2 = prop.getProperty("password2");
	return pwd2;
}
}