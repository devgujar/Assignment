package TestSuite.TC002_Verify_Sigup_on_rtMedia_ExistingUser;

import java.util.Hashtable;
import junit.framework.Assert;
import org.junit.Test;
import LibraryPackage.*;

/*
 * Test Objective  : Check the process of registration with already registered user name or email id 
 * Expected Result : User should get an error message, any of email or username already registered
 * 
 */
public class TC002_Verify_Sigup_on_rtMedia_ExistingUser {
	
	@Test
	public void TC002_Verify_Sigup_on_rtMedia_ExistingUser() throws Exception {
		
		
		// Create Page Object
		rtmediaPageBrowser rtmediaPage = new rtmediaPageBrowser();		
		
		//Get Test data
		Hashtable<String, String> excellData =rtmediaPage.GetExcell_Data_As_Hashtable("TC002_Verify_Sigup_on_rtMedia_ExistingUser.xls");
			
		//Start Home Page
		rtmediaPage.Start_Home_Page();

		SignUpPageBuilder SignUpPage = new SignUpPageBuilder();
			
			SignUpPage.setUsername(excellData.get("signup_username"));
			SignUpPage.setEmail(excellData.get("signup_email"));
			SignUpPage.setPassword(excellData.get("signup_password"));
			SignUpPage.setConfirmPassword(excellData.get("signup_password"));		
			SignUpPage.setName(excellData.get("Name"));  
			SignUpPage.setCity(excellData.get("city"));
			SignUpPage.setGender(excellData.get("gender"));
			SignUpPage.setBirthdate_day(excellData.get("birthdate_field_4_day"));
			SignUpPage.setBirthdate_month(excellData.get("birthdate_field_4_month"));
			SignUpPage.setBirthdate_Year(excellData.get("birthdate_field_4_year"));
		    SignUpPage.setSecurityAnswer();
		    SignUpPage.Submit();
	    	
		//Verify message on Sign Up completion		
		Assert.assertTrue(rtmediaPage.VerifyText("Sorry, that username already exists!") || rtmediaPage.VerifyText("Sorry, that email address is already used!"));
	  
    	//Clean up code
    	rtmediaPage.Close();
	}
}
