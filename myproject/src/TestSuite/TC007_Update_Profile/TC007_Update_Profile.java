package TestSuite.TC007_Update_Profile;
import java.io.IOException;

import java.util.Hashtable;
import junit.framework.Assert;
import jxl.read.biff.BiffException;
import org.junit.Test;
import org.openqa.selenium.By;
import LibraryPackage.*;


public class TC007_Update_Profile {

	@Test
	public void TC007_Update_Profile() throws BiffException, IOException {
	
		// Create Page Object
		rtmediaPageBrowser rtmediaPage = new rtmediaPageBrowser();
		
		//Start Home Page
		rtmediaPage.Start_Home_Page();
		
		//Navigate to LogIn Window
		rtmediaPage.Navigate_to_Login_Window();

		//Get Test case Data
	    Hashtable<String, String> excellData = rtmediaPage.GetExcell_Data_As_Hashtable("TC007_Update_Profile.xls");
		
		//Login with valid User Name and Password
		rtmediaPage.LogIn_As_User(excellData.get("username"),excellData.get("password"));
		
		// Create Profile Page Object
		rtmediaPage.Navigate_to_Profile();
		
		// Update Profile 
		ProfileBuilder profile = new ProfileBuilder();
		profile.setName(excellData.get("name"));
		profile.setCity(excellData.get("city"));
		profile.setGender(excellData.get("gender"));
		profile.setBirthdate(excellData.get("birthdate_field_4_day"), excellData.get("birthdate_field_4_month"), excellData.get("birthdate_field_4_year"));
		profile.click_Submit();	    

		// Verify Profile update Success message.
		Assert.assertTrue("Profile not updated correctly.", rtmediaPage.VerifyText("Changes saved."));
	    
    	//Clean up code
    	rtmediaPage.Close();
	}

}
