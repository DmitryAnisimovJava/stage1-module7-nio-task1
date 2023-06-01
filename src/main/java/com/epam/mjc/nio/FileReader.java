package com.epam.mjc.nio;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;


public class FileReader {
		private String name;
	    private Integer age;
	    private String email;
	    private Long phone;

	public static String readDataFromFile(File file) {
		try (RandomAccessFile myFile = new RandomAccessFile(file, "r");
			FileChannel inChannel = myFile.getChannel();) {
			long fileSize = inChannel.size();
			ByteBuffer buffer = ByteBuffer.allocate((int) fileSize);
			inChannel.read(buffer);
			String resultingData = buffer.toString();
			return resultingData;
		} catch (IOException e) {
			 e.printStackTrace();
			 return null;
		}
	}
	
    public Profile getDataFromFile(File file) {
    	String[] allDataInString;
    	try {
    		allDataInString = readDataFromFile(file).split("\n");
		} catch (NullPointerException e) {
			e.getMessage();
			e.getStackTrace();
		} finally {
			allDataInString = new String[1];
		}
    	for(int i = 0; i < allDataInString.length; i = i++) {
    		if(allDataInString[i].contains("Name:")) {
    		name = allDataInString[i].replace("Name:", "").replaceAll("\\s+", "");
    		}
    		if(allDataInString[i].contains("Age:")) {
    		age = Integer.getInteger(allDataInString[i].replace("Age:", "").replaceAll("\\s+", ""));
    		}
    		if(allDataInString[i].contains("Age:")) {
    		email = allDataInString[i].replace("Email:", "").replaceAll("\\s+", "");
    		}
    		if(allDataInString[i].contains("Age:")) {
    		phone = Long.getLong(allDataInString[i].replace("Phone:", "").replaceAll("\\s+", ""));
    		}
    	}
        return new Profile(name, age, email, phone);
    }
}
