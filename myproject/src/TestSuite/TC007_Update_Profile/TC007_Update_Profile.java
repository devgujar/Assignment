package TestSuite.TC007_Update_Profile;
import java.io.IOException;
import java.util.Hashtable;

import junit.framework.Assert;
import jxl.read.biff.BiffException;

import org.junit.Test;
import org.openqa.selenium.By;

import LibraryPackage.Project_Interface_Definations;

public class TC007_Update_Profile extends Project_Interface_Definations{

	@Test
	public void TC007_Update_Profile() throws BiffException, IOException {
		start_setup() ;
		
		//Navigate to LogIn Window
		driver.findElement(By.xpath("//a[contains(.,'Login')]")).click();
	    String test_data_sheet_path ="src/TestSuite/TC007_Update_Profile/TC007_Update_Profile.xls";
	    Hashtable<String, String> excellData = GetExcell_Data_As_Hashtable(test_data_sheet_path);
	    	    
	    LogIn_As_User(excellData.get("username"),excellData.get("password"));
	    
	    driver.findElement(By.xpath("//span[@class='rtp-user-name']//..")).click();
	    driver.findElement(By.linkText("Profile")).click();
	    driver.findElement(By.xpath("//li[@id='edit-personal-li']//a")).click();
	    
	    driver.findElement(By.id("field_1")).clear();
	    driver.findElement(By.id("field_1")).sendKeys(excellData.get("name"));
	    driver.findElement(By.id("field_2")).clear();
	    driver.findElement(By.id("field_2")).sendKeys(excellData.get("city"));
	    driver.findElement(By.xpath("//select[@id='field_3']//option[contains(.,'"+excellData.get("gender")+"')]")).click();	    
	    driver.findElement(By.xpath("//select[@id='field_4_day']//option[@value='"+excellData.get("birthdate_field_4_day")+"']")).click();  
	    driver.findElement(By.xpath("//select[@id='field_4_month']//option[@value='"+excellData.get("birthdate_field_4_month")+"']")).click();  
	    driver.findElement(By.xpath("//select[@id='field_4_year']//option[@value='"+excellData.get("birthdate_field_4_year")+"']")).click();  
	    driver.findElement(By.id("profile-group-edit-submit")).click();
	    
	    boolean flag= driver.findElement(By.xpath("//div[@id='message'][@class='bp-template-notice updated']//p[contains(.,'Changes saved.')]")).isDisplayed();	    
	    System.out.println(flag);
	    Assert.assertTrue("Profile not updated correctly.", flag);
	    
	    
	}

}
