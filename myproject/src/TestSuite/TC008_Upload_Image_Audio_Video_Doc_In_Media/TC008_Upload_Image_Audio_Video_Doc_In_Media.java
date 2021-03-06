package TestSuite.TC008_Upload_Image_Audio_Video_Doc_In_Media;

import java.io.IOException;
import java.util.Hashtable;
import org.junit.Test;
import jxl.read.biff.BiffException;
import LibraryPackage.*;

/*
 * Test Objective 	: 	Upload image/audio/video/doc in media. Ref. http://demo.rtcamp.com/rtmedia/members/admin/media/Note: Replace admin with Your User Name.
 * Expected Result 	:	Image/audio/video/doc should be uploaded in the selected album and with the selected privacy only successfully.
 * 
 */

public class TC008_Upload_Image_Audio_Video_Doc_In_Media {
	
	@Test
	public void TC008_Upload_Image_Audio_Video_Doc_In_Media() throws BiffException, IOException, InterruptedException {

		// Create Page Object
		rtmediaPageBrowser rtmediaPage = new rtmediaPageBrowser();
		
		//Start Home Page
		rtmediaPage.Start_Home_Page();
		
		//Navigate to LogIn Window
		rtmediaPage.Navigate_to_Login_Window();
		
		//Get Test case Data
	    Hashtable<String, String> excellData = rtmediaPage.GetExcell_Data_As_Hashtable("TC008_Upload_Image_Audio_Video_Doc_In_Media.xls");
	    	    
		//Login with valid User Name and Password
		rtmediaPage.LogIn_As_User(excellData.get("username"),excellData.get("password"));

		// Create Media Page Object
	    MediaPageBuilder MediaPage =new MediaPageBuilder();

	    // Navigate to Upload UI on Media Page
	    MediaPage.Navigate_to_UploadUI();
	    // Upload File with default settings for Album and Privacy	    
	    MediaPage.upload_File("TC005_Audio_Song.mp3");

	    
	    // Navigate to Upload UI on Media Page
	    MediaPage.Navigate_to_UploadUI();
	    // Select Album	    
	    MediaPage.setAlbum("Profile Pictures");
	    // Select Privacy	    
	    MediaPage.setPrivacy("Friends");
	    // Upload File	    
	    MediaPage.upload_File("TC005_Image.jpg");

	    
	    // Navigate to Upload UI on Media Page	    
	    MediaPage.Navigate_to_UploadUI();
	    // Select Album
	    MediaPage.setAlbum("Wall Posts");
	    // Select Privacy
	    MediaPage.setPrivacy("Private");
	    // Upload File
	    MediaPage.upload_File("TC005_VDO_FacebookClip.mp4");
	    
	    // Navigate to Upload UI on Media Page	    
	    MediaPage.Navigate_to_UploadUI();
	    // Select Album
	    MediaPage.setAlbum("My very first");
	    // Select Privacy
	    MediaPage.setPrivacy("Logged in Users");
	    // Upload File	    
	    MediaPage.upload_File("TC005_WordDoc.docx");
	    
    	//Clean up code
    	rtmediaPage.Close();
	}

}
