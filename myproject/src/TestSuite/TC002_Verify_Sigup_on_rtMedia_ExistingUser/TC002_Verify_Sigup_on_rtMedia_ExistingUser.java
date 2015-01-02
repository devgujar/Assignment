package TestSuite.TC002_Verify_Sigup_on_rtMedia_ExistingUser;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Hashtable;

import junit.framework.Assert;
import jxl.common.AssertionFailed;

import org.apache.bcel.generic.Select;
import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.openqa.selenium.By;

import LibraryPackage.*;

/*
 * Test Objective  : Check the process of registration with already registered user name or email id 
 * Expected Result :
 * 
 */
public class TC002_Verify_Sigup_on_rtMedia_ExistingUser {
	
	@Test
	public void TC002_Verify_Sigup_on_rtMedia_ExistingUser() throws Exception {
		
		
		// Create Page Object
		rtmediaPageBrowser rtmediaPage = new rtmediaPageBrowser();		
			
		//Start Home Page
		rtmediaPage.Start_Home_Page();
		
		// Navigate to Sign Up page
		rtmediaPage.Navigate_to_SignUp_Page();
		
		// Fetch data from excelSheet and fill sign up form and click submit.		    
		rtmediaPage.Enter_SignUp_form_details_from_excell_sheet("TC002_Verify_Sigup_on_rtMedia_ExistingUser.xls");		    
	    	
		//Verify message on Sign Up completion		
		Assert.assertTrue(rtmediaPage.VerifyText("Sorry, that username already exists!") || rtmediaPage.VerifyText("Sorry, that email address is already used!"));
	  
	}
}
