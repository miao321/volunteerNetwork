package com.volunteer.utils;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

public class FileUpload {
	 public static final String FILE_PATH = "/upload/";  
	  
	    //文件上传  
	    public static String uploadFile(MultipartFile file, HttpServletRequest request) throws IOException {  
	        String fileName = file.getOriginalFilename();  
	        File tempFile = new File(FILE_PATH, new Date().getTime() + String.valueOf(fileName));  
	        if (!tempFile.getParentFile().exists()) {  
	            tempFile.getParentFile().mkdir();  
	        }  
	        if (!tempFile.exists()) {  
	            tempFile.createNewFile();  
	        }  
	        file.transferTo(tempFile);  
	        return tempFile.getName();  
	    }  
	  
	    public static File getFile(String fileName) {  
	        return new File(FILE_PATH, fileName);  
	    }  
}
