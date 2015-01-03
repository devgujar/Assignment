package LibraryPackage;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Hashtable;
import java.util.concurrent.TimeUnit;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class rtmediaPageBrowser {

	public static WebDriver driver;
	public static String baseUrl ;
	public static boolean acceptNextAlert = true;
	public static String data_sheet_path="setup_file.xls";
	
	public rtmediaPageBrowser() {
		// TODO Auto-generated constructor stub
	}

	public  void Start_Home_Page() throws BiffException, IOException {				  
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
		
		//Clear Browser Cache
		driver.manage().deleteAllCookies();
		
		//Submit baseUrl to Browser 
		baseUrl =excellData.get("BaseUrl"); 
		System.out.println("Starting URL: "+baseUrl);	
		driver.get(baseUrl);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
	}
	
	public  void Navigate_to_Login_Window(){
	    driver.findElement(By.xpath("//a[contains(.,'Login')]")).click();	    
	}

	public  void Navigate_to_SignUp_Page(){
	    driver.findElement(By.xpath("//a[contains(.,'Login')]")).click();
	    driver.findElement(By.xpath("//a[contains(.,'Register')]")).click();	    
	}
	
	public void Navigate_to_Actvity(){
	    driver.findElement(By.xpath("//span[@class='rtp-user-name']//..")).click();
	    driver.findElement(By.linkText("Activity")).click();	    
	}
	
	public void Navigate_to_Profile(){
	    driver.findElement(By.xpath("//span[@class='rtp-user-name']//..")).click();
	    driver.findElement(By.linkText("Profile")).click();
	}

	public void Navigate_to_Media_Tab(){
	    driver.findElement(By.xpath("//a[@id='user-media'][contains(.,'Media')]")).click();	
	}
	
	public void logout(){
	    driver.findElement(By.xpath("//span[@class='rtp-user-name']//..")).click();	    
	    driver.findElement(By.linkText("Logout")).click();
	}
	
	public void deleteAllCookies(){
	    driver.manage().deleteAllCookies();
	}
	
	public Hashtable<String,String> GetExcell_Data_As_Hashtable(String FilePath) throws BiffException, IOException {		
		
	    Hashtable<String,String> xls_data_array = new Hashtable<String, String>();
	   
	   	FileInputStream fileInputStream = new FileInputStream("src/DataSet/"+FilePath);		
		Workbook wb = Workbook.getWorkbook(fileInputStream);
		Sheet sh = wb.getSheet("Sheet1");
		int rows = sh.getRows();
		int cols = sh.getColumns();

			for(int i=0;i<rows;i++){ 			
				xls_data_array.put(sh.getCell(0,i).getContents(),sh.getCell(1,i).getContents());
			}
		return xls_data_array;
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
	
	
	public void LogIn_As_User(String UserName, String Password) {
		
	    System.out.println("Entering UserName :"+UserName);
	    driver.findElement(By.xpath("//input[@placeholder='Username']")).sendKeys(UserName);
	    
	    System.out.println("Entering Password :"+Password);
	    driver.findElement(By.xpath("//input[@placeholder='Password']")).sendKeys(Password);
	    
	    driver.findElement(By.name("wp-submit")).submit();
	}

	public void Publish_privatePost(String Post_Text) {
	    driver.findElement(By.xpath("//div[@id='whats-new-textarea']//textarea")).clear();
	    System.out.println("Entering private post :"+Post_Text);
	    driver.findElement(By.xpath("//div[@id='whats-new-textarea']//textarea")).sendKeys(Post_Text);
	    driver.findElement(By.xpath("//select[@id='rtSelectPrivacy']//option[contains(.,'Private')]")).click();
	    driver.findElement(By.id("aw-whats-new-submit")).click();
	}

	public void Publish_publicPost(String Post_Text) {
	    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	    driver.findElement(By.xpath("//div[@id='whats-new-textarea']//textarea")).clear();
	    System.out.println("Entering public post :"+Post_Text);
	    driver.findElement(By.xpath("//div[@id='whats-new-textarea']//textarea")).sendKeys(Post_Text);
	    driver.findElement(By.id("aw-whats-new-submit")).click();
	}

	public void Publish_FriendsPost(String Post_Text) {
	    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	    driver.findElement(By.xpath("//div[@id='whats-new-textarea']//textarea")).clear();
	    System.out.println("Entering Friends post :"+Post_Text);
	    driver.findElement(By.xpath("//div[@id='whats-new-textarea']//textarea")).sendKeys(Post_Text);
	    driver.findElement(By.xpath("//select[@id='rtSelectPrivacy']//option[contains(.,'Friends')]")).click(); 
	    driver.findElement(By.id("aw-whats-new-submit")).click();
	}
	
	public void Publish_Logged_in_UsersPost(String Post_Text) {
	    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	    driver.findElement(By.xpath("//div[@id='whats-new-textarea']//textarea")).clear();
	    System.out.println("Entering Logged in Users post :"+Post_Text);
	    driver.findElement(By.xpath("//div[@id='whats-new-textarea']//textarea")).sendKeys(Post_Text);
	    driver.findElement(By.xpath("//select[@id='rtSelectPrivacy']//option[contains(.,'Logged in Users')]")).click();
	    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	    driver.findElement(By.id("aw-whats-new-submit")).click();
	}
	

	public boolean VerifyText(String textToBeVerified)
	{
		try
		{
			if (driver.findElement((By.xpath("//*[contains(.,'" + textToBeVerified + "')]"))) != null) { 			
				return true; 	
			}
			
		} catch (Exception e) { return false; }
			
		return false;
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

	public void Close() {
	      driver.quit();
	    }
	  

}
