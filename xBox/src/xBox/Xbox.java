package xBox;

import java.util.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;

import ex.*;
import io.*;

import java.awt.*;
import data.Client;
import data.Database;
import java.net.URL;

/**
 * 
 * @brief xBox UI Interface
 * 
 * provide UI interface for users and handle data base initialization and json file operation
 */

public class Xbox {
	private static Xbox instance;
	private String now_page = null;
	private static String[] saveFilePaths;
	private static boolean iftest = false;
	private JFrame jframe = init(!iftest);
	private JPanel content = null;
	private JScrollPane content_data = null;
	private String data =null;
    private static String error_message =null;

	public static Xbox getInstance() {
		if(instance ==null) {
			instance =new Xbox();
		}
		return instance;
	}
	
	public static void setSaveFilePaths(String[] saveFilePaths_) { saveFilePaths = saveFilePaths_; }
	

	private JFrame init(boolean test){
	    Database db = Database.getInstance();

		JFrame jFrame = new JFrame();
		double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		jFrame.setBounds(0, 0, 450, 550);
        jFrame.setLocation( (int) (width - jFrame.getWidth()) / 2,
        (int) (height - jFrame.getHeight()) / 2);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jFrame.setLayout(null);
		jFrame.setVisible(test);
		jFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                // store up
                try {
                    db.storeUp(saveFilePaths);
                } catch (IOException ex) {
                    Xbox.error(ex);
                    // System.out.println("Fail to save json file!");
                }
           }
        });
		return jFrame;
	}
	public static JTextArea out(String a) {
		JTextArea jt= new JTextArea();
		Font x = new Font(Font.MONOSPACED, 0, 10);
		jt.setFont(x);
		jt.setText(a);
		jt.setEditable(false);
		jt.setOpaque(false);
    	jt.setLineWrap(true);
    	System.out.println(a);
		return jt;
	}
	
	public void show_page(JPanel a,String page) {
	    now_page = page;
		if(this.content != a) {
			if(this.content!=null) {
				this.jframe.remove(this.content);
			}
			this.content = a;
			this.jframe.add(this.content);
			SwingUtilities.updateComponentTreeUI(this.jframe);	
		}
	}
	
	
	public void show_data(String a) {
		if(data !=a) {
			if(content_data !=null) {
				this.jframe.remove(content_data);
			}
			data=a;
			content_data = new JScrollPane(out(a),ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
	                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			content_data.setBounds(0, 300, 450, 190);
			jframe.add(content_data);
			SwingUtilities.updateComponentTreeUI(this.jframe);	

		}
	}
	public static void output(String a) {
		Xbox main = Xbox.getInstance();
		main.show_data(a);
		return;
	}
	public static void error(Exception a) {
	    error_message = a.getMessage();
	    if(iftest==false) {
	        JOptionPane.showMessageDialog(null, a.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
	    }

	}
	public String get_page() {
	    return now_page;
	}
	public static void totest() {
	    iftest = true;
	}
	public String get_data() {
	    return data;
	}
	public static String get_error_message() {
	    return error_message;
	}


}
