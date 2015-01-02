package LibraryPackage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.InvalidPropertiesFormatException;
import java.util.Properties;


import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;

import javax.sound.midi.MidiDevice.Info;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathFactory;

import org.apache.commons.logging.Log;
import org.junit.*;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.openqa.selenium.*;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.w3c.dom.NodeList;

import com.gargoylesoftware.htmlunit.javascript.host.Document;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class Project_Interface_Definations 
{
	public static WebDriver driver;
	public static String baseUrl ;
	public static boolean acceptNextAlert = true;
	public static String data_sheet_path="src/LibraryPackage/setup_file.xls";
	

	public static void start_setup() throws BiffException, IOException {				  
		//Get Browser from config file setup_file.xls
		
		Hashtable<String ,String> excellData= GetExcell_Data_As_Hashtable(data_sheet_path);		

		String Broswer_Name =excellData.get("Browser");
		if(Broswer_Name.equals("FF"))
		{	
			System.out.println("Starting Firefox Browser");
			driver = new FirefoxDriver();
		}
		else if (Broswer_Name.equals("IE")) {
			System.out.println("Starting Internet Explorer Browser");
			driver = new InternetExplorerDriver();
		}
		else 
			System.err.println("Browser not implemented or Key \"Browser\" is not found please use IE or FF in setup_file.xls");
		
		//Maximize Browser Window
		driver.manage().window().maximize() ;
		
		//Submit baseUrl to Browser 
		baseUrl =excellData.get("BaseUrl"); 
		System.out.println("Starting URL: "+baseUrl);
		
		driver.get(baseUrl);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
	}

	
	/*
	 * This fuction will return value for the associated key from file setup_file.xls
	 * @see LibraryPackage.Project_Interface#GetExcell_Varible_Value(java.lang.String, java.lang.String)
	 */

	public String GetExcell_Varible_Value(String FilePath, String VariableName) throws BiffException, IOException {
		
		FileInputStream fileInputStream = new FileInputStream(FilePath);		
		Workbook wb = Workbook.getWorkbook(fileInputStream);
		Sheet sh = wb.getSheet("Sheet1");
		int rows = sh.getRows();
		int cols = sh.getColumns();
		//System.out.print("\n total rows:"+rows+"\n Total Columns:"+ cols +"\n");
		
			for(int i=0;i<rows;i++){ 
				if(sh.getCell(0,i).getContents().equals(VariableName)){	
					return sh.getCell(1,i).getContents();
				}
				
			}return null;
	}
	
	public static Hashtable<String,String> GetExcell_Data_As_Hashtable(String FilePath) throws BiffException, IOException {		
		
	    Hashtable<String,String> xls_data_array = new Hashtable<String, String>();
	   
	   	FileInputStream fileInputStream = new FileInputStream(FilePath);		
		Workbook wb = Workbook.getWorkbook(fileInputStream);
		Sheet sh = wb.getSheet("Sheet1");
		int rows = sh.getRows();
		int cols = sh.getColumns();

			for(int i=0;i<rows;i++){ 			
				xls_data_array.put(sh.getCell(0,i).getContents(),sh.getCell(1,i).getContents());
			}
		return xls_data_array;
	}
	
	public void LogIn_As_User(String UserName, String Password) {
		
	    System.out.println("Entering UserName :"+UserName);
	    driver.findElement(By.xpath("//input[@placeholder='Username']")).sendKeys(UserName);
	    
	    System.out.println("Entering Password :"+Password);
	    driver.findElement(By.xpath("//input[@placeholder='Password']")).sendKeys(Password);
	    
	    driver.findElement(By.name("wp-submit")).submit();
	}

	public int Get_security_Answer_for_SignUp_Page() {
		int answer = 0;	
		
    	String str= driver.findElement(By.xpath("//label[@for='bp-security-check']")).getText(); 		    	

    	if(str.contains("+"))
    		answer = Integer.parseInt(String.valueOf(str.charAt(0))) + Integer.parseInt( String.valueOf(str.charAt(4)));
    	else if (str.contains("-")) {
    		answer =Integer.parseInt(String.valueOf(str.charAt(0))) - Integer.parseInt( String.valueOf(str.charAt(4)));
		}		    			    	
    	else if (str.contains("−")) {
    		answer =Integer.parseInt(String.valueOf(str.charAt(0))) - Integer.parseInt( String.valueOf(str.charAt(4)));
		}		    	

    	return answer;
	}
	

	
	
	public boolean isElementPresent(By by) {
		    try {
		      driver.findElement(by);
		      return true;
		    } catch (NoSuchElementException e) {
		      return false;
		    }
		  }

	public String closeAlertAndGetItsText() {
		    try {
		      Alert alert = driver.switchTo().alert();
		      String alertText = alert.getText();
		      if (acceptNextAlert) {
		        alert.accept();
		      } else {
		        alert.dismiss();
		      }
		      return alertText;
		    } finally {
		      acceptNextAlert = true;
		    }
		  }
/*
	public void waitforpageload (){
		wait = Selenium::WebDriver::Wait.new(:timeout => 10)
			  wait.until {
			    @driver.execute_script("return document.readyState;") == "complete"
	  }
	  	    		
			  }
*/



}
