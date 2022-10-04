/**
 * 
 */
package utils;

/**
 * @author lixiaoyang
 *
 */
public class StringUtils {
	public static final String splitLine = "********************************************************************************";
	
	public static String getExtensionName(String filePathName_) {
		if(filePathName_ == null || filePathName_.length() <= 0) {
			return "";
		}
		int pos = filePathName_.lastIndexOf('.');
		if(pos >= 0 && pos < filePathName_.length()) {
			return filePathName_.substring(pos + 1);
		}
		// return empty string by default
		return "";
	}
}
