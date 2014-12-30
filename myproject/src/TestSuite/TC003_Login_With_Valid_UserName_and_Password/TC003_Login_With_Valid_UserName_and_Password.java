package TestSuite.TC003_Login_With_Valid_UserName_and_Password;
import java.io.IOException;

import jxl.read.biff.BiffException;

import org.openqa.selenium.By;
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
import LibraryPackage.Project_Interface_Definations;

public class TC003_Login_With_Valid_UserName_and_Password extends Project_Interface_Definations{

	@Test
	public void TC003_Login_With_Valid_UserName_and_Password() throws BiffException, IOException {
	 
		start_setup() ;
		
		//Navigate to LogIn Window
		driver.findElement(By.xpath("//a[contains(.,'Login')]")).click();
	    String test_data_sheet_path ="src/TestSuite/TC003_Login_With_Valid_UserName_and_Password/TC003_Login_With_Valid_UserName_and_Password.xls";
	    Hashtable<String, String> excellData = GetExcell_Data_As_Hashtable(test_data_sheet_path);
	    	    
	    LogIn_As_User(excellData.get("username"),excellData.get("password"));
	    	    
	    String expected = "Welcome To rtDating";
	    String actual= driver.findElement(By.xpath("//h2[contains(.,'Welcome To rtDating')]")).getText();
	    Assert.assertTrue("Test case validation failed.", expected.equals(actual));
	    
	}

}
