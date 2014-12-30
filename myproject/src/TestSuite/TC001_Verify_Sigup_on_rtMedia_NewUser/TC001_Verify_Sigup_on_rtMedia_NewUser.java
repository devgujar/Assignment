package TestSuite.TC001_Verify_Sigup_on_rtMedia_NewUser;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Hashtable;

import junit.framework.Assert;

import org.apache.bcel.generic.Select;
import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.openqa.selenium.By;

import LibraryPackage.Project_Interface_Definations;

public class TC001_Verify_Sigup_on_rtMedia_NewUser extends Project_Interface_Definations{
	
	@Test
	public void TC001_Verify_Sigup_on_rtMedia_NewUser() throws Exception {
		
			start_setup() ;
			
		    driver.findElement(By.xpath("//a[contains(.,'Login')]")).click();
		    driver.findElement(By.xpath("//a[contains(.,'Register')]")).click();
		    
			String test_data_sheet_path="src/TestSuite/TC001_Verify_Sigup_on_rtMedia_NewUser/TC001_Verify_Sigup_on_rtMedia_NewUser.xls";
			Hashtable<String, String> excellData =GetExcell_Data_As_Hashtable(test_data_sheet_path);
			
		    driver.findElement(By.id("signup_username")).sendKeys(excellData.get("signup_username"));
			driver.findElement(By.id("signup_email")).sendKeys(excellData.get("signup_email"));
		    driver.findElement(By.id("signup_password")).sendKeys(excellData.get("signup_password"));		    
		    driver.findElement(By.id("signup_password_confirm")).sendKeys(excellData.get("signup_password"));
		    
		    driver.findElement(By.id("field_1")).sendKeys(excellData.get("Name"));
		    driver.findElement(By.id("field_2")).sendKeys(excellData.get("city"));
		    driver.findElement(By.xpath("//option[@value='"+  excellData.get("gender") + "']")).click();		    
		    driver.findElement(By.xpath("//select[@id='field_4_day']//option[@value='"+excellData.get("birthdate_field_4_day")+"']")).click();  
		    driver.findElement(By.xpath("//select[@id='field_4_month']//option[@value='"+excellData.get("birthdate_field_4_month")+"']")).click();  
		    driver.findElement(By.xpath("//select[@id='field_4_year']//option[@value='"+excellData.get("birthdate_field_4_year")+"']")).click();  
	    	driver.findElement(By.name("bp-security-check")).sendKeys(String.valueOf(Get_security_Answer_for_SignUp_Page()));
	    	driver.findElement(By.xpath("//input[@id='signup_submit']")).click();	    		    
	    	
	    	//Verify success Message
	    	String expected="You have successfully created your account! To begin using this site you will need to activate your account via the email we have just sent to your address.";	    	
	    	String actual=driver.findElement(By.xpath("//form[@id='signup_form']/p")).getText();
	    	Assert.assertEquals(expected, actual);
	  }

}
