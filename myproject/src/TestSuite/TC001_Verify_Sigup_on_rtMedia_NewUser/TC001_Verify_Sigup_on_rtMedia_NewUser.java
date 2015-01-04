package TestSuite.TC001_Verify_Sigup_on_rtMedia_NewUser;

import java.util.Hashtable;
import junit.framework.Assert;
import org.junit.Test;
import LibraryPackage.*;


/*	
 * Get register on rtMedia	|  User should get successfully registered
 * 
 */

public class TC001_Verify_Sigup_on_rtMedia_NewUser{
	
	@Test
	public void TC001_Verify_Sigup_on_rtMedia_NewUser() throws Exception {
		
		// Create Page Object
		rtmediaPageBrowser rtmediaPage = new rtmediaPageBrowser();		
		
		//Get Test data
		Hashtable<String, String> excellData =rtmediaPage.GetExcell_Data_As_Hashtable("TC001_Verify_Sigup_on_rtMedia_NewUser.xls");
			
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

		//Verify success message on Sign Up completion
    	Assert.assertTrue(rtmediaPage.VerifyText("You have successfully created your account! To begin using this site you will need to activate your account via the email we have just sent to your address."));   	

    	//Clean up code
    	rtmediaPage.Close();
	}

}
