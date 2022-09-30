/**
 * 
 */
package io;

// io
import java.io.BufferedReader;
//import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;

// exception
import ex.ExInvalidFileFormat;

// configurations
import debug.DebugConfig;

// utilities
import java.util.HashMap;
import java.util.Arrays;
import utils.StringUtils;

/**
 * @author xyli45
 *
 */
public class FileIO implements XBoxIO{
	private BufferedReader reader;
	//private BufferedWriter writer;
	private String filePathName;
	private String extensionName;
	//private File file;
	private static FileIO instance = new FileIO();
	private static final String[] validExts = {"csv", "txt"}; // TODO: modify
	private static final HashMap<String, String> delimeters = new HashMap<String, String>(){{
		put("csv", ",");
		put("txt", " ");
	}};
	
	private FileIO() {}
	
	public static FileIO getInstance(String filePathName_) {
		FileIO ret = instance;
		ret.setFile(filePathName_);
		return ret;
	}
	
	private static boolean isValidExt(String extensionName_) {
		if(extensionName_ == null || extensionName_.length() <= 0) {
			return false;
		}
		for(String validExtensionName : validExts) {
			if(validExtensionName.equals(extensionName_)) {
				return true;
			}
		}
		return false;
	}
	
	// set file before input
	public void setFile(String filePathName_) {
		this.filePathName = filePathName_;
		this.extensionName = StringUtils.getExtensionName(filePathName_);
	}
	
	@Override
	public void input() throws IOException{
		try {
			// check whether system resources are released 
			if(this.reader != null) {
				// TODO: handled by IOException
				this.reader.close(); 
			}
			// check whether file format is valid
			if(FileIO.isValidExt(this.extensionName) == false) {
				throw new ExInvalidFileFormat();
			}
			// TODO: handled by FileNotFoundException
			this.reader = new BufferedReader(new FileReader(this.filePathName));
			String strLine;
			while((strLine = this.reader.readLine()) != null) {
				String delim = delimeters.get(this.extensionName);
				String[] cmdLine = strLine.split(delim);
				
				// #if DEBUG
				if(DebugConfig.IO_FILEIO_DEBUG_FLAG) {
					for(String str : cmdLine) {
						System.out.println(str);
					}
				}
				// #endif
				
				if(cmdLine[0].equals(IOStrConst.REGISTER_CMD)) {
					/*TODO: register(cmdLine);*/
				}else if(cmdLine[0].equals(IOStrConst.LOGIN_CMD)) {
					/*TODO: login(cmdLine);*/
				}else if(cmdLine[0].equals(IOStrConst.QUIT_CMD)) {
					/*TODO: quit();*/
				}
			}
		}catch(FileNotFoundException ex) {
			System.out.println(ex.getMessage());
		}catch(ExInvalidFileFormat ex) {
			System.out.println(ex.getMessage());
			System.out.println("The file format is not in " + Arrays.toString(FileIO.validExts));
		}catch(IOException ex) {
			if(DebugConfig.IO_FILEIO_DEBUG_FLAG) {
				System.out.println(ex.getMessage());
			}
		} 
	}

	@Override
	public void output() {
		// TODO Auto-generated method stub
		
	}
}
