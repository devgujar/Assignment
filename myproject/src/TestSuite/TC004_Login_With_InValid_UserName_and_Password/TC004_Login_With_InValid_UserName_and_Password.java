package TestSuite.TC004_Login_With_InValid_UserName_and_Password;

import java.io.IOException;
import java.util.Hashtable;
import LibraryPackage.rtmediaPageBrowser;
import junit.framework.Assert;
import jxl.read.biff.BiffException;
import org.junit.Test;
import LibraryPackage.*;

/*
 * Test Objective 	: 	Login with invalid user name and password
 * Expected Result 	:	User should get error message,should not get logged in
 * 
 */
public class TC004_Login_With_InValid_UserName_and_Password {

	@Test
	public void TC004_Login_With_InValid_UserName_and_Password() throws BiffException, IOException {

	    // Create page Object
	    rtmediaPageBrowser rtmediaPage = new rtmediaPageBrowser();

		//Get Test case Data
	    Hashtable<String, String> excellData = rtmediaPage.GetExcell_Data_As_Hashtable("TC004_Login_With_InValid_UserName_and_Password.xls");
				
		//Start Home Page
		rtmediaPage.Start_Home_Page();
		
		//Navigate to LogIn Window
		rtmediaPage.Navigate_to_Login_Window();
		
		//Login with Invalid User Name and Password
		rtmediaPage.LogIn_As_User(excellData.get("username"),excellData.get("password"));
	    	    
	    Assert.assertTrue(rtmediaPage.VerifyText("ERROR"));
	    
    	//Clean up code
    	rtmediaPage.Close();
		
	}

}
