package HelperClasses;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;

public class FTPOPerations {
	
	private String userName = "";
	private String userPassword = "";
	
	public static Boolean UploadFile(String FileName, String FileStoreName) {
		
		String desktopPath = System.getProperty("user.home") + "/Desktop";
		desktopPath.replace("\\", "/");
		
		FTPClient ftpClient = new FTPClient();
		try {
			ftpClient.connect("ftp.drivehq.com", 21);
		
		ftpClient.enterLocalPassiveMode();
		ftpClient.login("USERNAME", "PASSWORD");
		
		File file = new File(desktopPath + "\\"+FileName);
		
		InputStream inputStream = new FileInputStream(file);
		ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
		
		Boolean test = ftpClient.storeFile(FileStoreName, inputStream);
		
		inputStream.close();
		
		if(test)
			return true;
		
		}catch (Exception e) {
			return false;
		}
		return false;
		
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	
	
}
