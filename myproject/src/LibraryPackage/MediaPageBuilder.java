package LibraryPackage;

import java.io.IOException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MediaPageBuilder {	
	
	  WebDriver driver= rtmediaPageBrowser.driver;
		
	  public void Navigate_to_UploadUI(){
		    // Navigate to Activity Page on UI
			driver.findElement(By.xpath("//span[@class='rtp-user-name']//..")).click();
		    driver.findElement(By.linkText("Activity")).click();
		    
		    // Navigaet to Media tab on UI
		    driver.findElement(By.xpath("//a[@id='user-media'][contains(.,'Media')]")).click();
		    
		    //Show Upload UI
			driver.findElement(By.xpath("//span[@id='rtm_show_upload_ui']/i")).click();
			}
			
		public void setAlbum(String Album) {
			driver.findElement(By.xpath("//select[@class='rtmedia-user-album-list']//option[contains(.,'"+Album+"')]")).click();			 
		}

		public void setPrivacy(String privacy) {
			driver.findElement(By.xpath("//select[@id='rtSelectPrivacy']//option[contains(.,'Public')]")).click();			
		}

		public void upload_File(String FileName) throws IOException, InterruptedException{

			// Select File for upload
			driver.findElement(By.xpath("//input[@id='rtMedia-upload-button']")).click();
			
			// Find path url of files to upload
		    final String pathFile = new java.io.File("").getAbsolutePath()+"\\src\\MediaFiles\\" + FileName ;
		    System.out.println("File Upload path : "+ pathFile);
			Runtime.getRuntime().exec("src/LibraryPackage/AutoItUpload.exe "+pathFile);
			Thread.sleep(2000);
			
			// Select Terms and conditions
			driver.findElement(By.xpath("//input[@id='rtmedia_upload_terms_conditions']")).click();
			
			//Click Start upload
			driver.findElement(By.xpath("//input[@class='start-media-upload']")).click();
			
			//Thread.sleep(10000);
			
			// wait for upload complete
			(new WebDriverWait(driver, 10)).until(ExpectedConditions.textToBePresentInElementLocated(
					By.xpath("//td[@class='plupload_file_status']"), "Uploaded"));
			
			System.out.println("Upload Status :"+driver.findElement(By.xpath("//td[@class='plupload_file_status']")).getText());
						
		}	
}
