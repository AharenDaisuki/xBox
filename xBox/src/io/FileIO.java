/**
 * 
 */
package io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;


/**
 * @author xyli45
 *
 */
public class FileIO implements XBoxIO{
	private String filePathName;
	private File file;
	private static FileIO instance = new FileIO();
	
	private FileIO() {}
	/*
	private FileIO(String filePathName_) {
		this.filePathName = filePathName_;
		this.file = new File(filePathName_);
	}
	
	private FileIO(File file_) {
		this.filePathName = file_.getAbsolutePath();
		this.file = file_; // TODO: modify
	}*/
	
	public static FileIO getInstance() {
		return instance;
	}
	
	public void setFile(String filePathName_) {
		this.filePathName = filePathName_;
		this.file = new File(filePathName_);
	}
	
	public void setFile(File file_) {
		this.filePathName = file_.getAbsolutePath();
		this.file = file_;
	}
	// TODO: io status
	public void input() {
		
	}
	
	public void output() {
		
	}
}
