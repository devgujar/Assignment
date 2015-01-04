package TestSuite.test_demo;
import java.io.FileInputStream;
import java.sql.Driver;
import java.util.Hashtable;

import junit.framework.Assert;

import org.apache.bcel.generic.Select;
import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.openqa.selenium.By;

import LibraryPackage.rtmediaPageBrowser;

public class test_demo {
	
	@Test
	public void Get_register_on_rtMedia() throws Exception {		
		System.out.println("TEST");
		
		rtmediaPageBrowser rtmediaPage = new rtmediaPageBrowser();
		rtmediaPage.Start_Home_Page();
		
    	//Clean up code
    	rtmediaPage.Close();
	  }

}
