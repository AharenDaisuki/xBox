/**
 * 
 */
package test;

// junit
//import static org.junit.Assert.*;
import org.junit.Test;

// dependencies
import io.*;

// exception
import java.io.IOException;

/**
 * @author lixiaoyang
 *
 */
public class TestXBoxIO {
	@Test
	public void testMainFlow() {
		String filePathName = "";
		XBoxIO ioHandler = FileIO.getInstance(filePathName);
		try {
			ioHandler.input();
		}catch(IOException ex) {
			System.out.println(ex.getMessage());
		}
	} 
}
