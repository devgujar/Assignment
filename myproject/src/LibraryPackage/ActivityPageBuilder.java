package LibraryPackage;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ActivityPageBuilder {

	public ActivityPageBuilder() {

	}

	public void Open_File_Upload_Dialog() {
		rtmediaPageBrowser.driver.findElement(By.xpath("//div[@id='rtmedia-whts-new-upload-container']//input[@type='file']")).click();
	}
	
	public void Upload_File(String FileName) throws IOException, InterruptedException {
		
		rtmediaPageBrowser.driver.findElement(By.xpath("//div[@id='rtmedia-whts-new-upload-container']//input[@type='file']")).click();
	    // Find path url of files to upload
	    final String pathFile = new java.io.File("").getAbsolutePath()+"\\src\\MediaFiles\\" + FileName ;				
		Runtime.getRuntime().exec("src/LibraryPackage/AutoItUpload.exe "+pathFile);
		Thread.sleep(7000);

	}

}
