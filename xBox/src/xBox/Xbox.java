package xBox;

import java.util.*;
import javax.swing.*;
import java.awt.event.*;  
import ex.*;
import io.*;

import java.awt.*;
import data.Client;

public class Xbox {
	private static Xbox instance;

	public static Xbox getInstance() {
		if(instance ==null) {
			instance =new Xbox();
		}
		return instance;
	}
	private JFrame jframe = init();
	private JPanel content = null;
	private JScrollPane content_data = null;
	private String data =null;
	
	private JFrame init(){
		JFrame jFrame = new JFrame();
		double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		jFrame.setBounds(0, 0, 450, 550);
        jFrame.setLocation( (int) (width - jFrame.getWidth()) / 2,
        (int) (height - jFrame.getHeight()) / 2);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jFrame.setLayout(null);
		jFrame.setVisible(true);
		jFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                System.out.println("saving datas");
           }
        });
		return jFrame;
	}
	public static JTextArea out(String a) {
		JTextArea jt= new JTextArea();
		jt.setText(a);
		jt.setEditable(false);
		jt.setOpaque(false);
    	jt.setLineWrap(true);
    	System.out.println(a);
		return jt;
	}
	
	public void show_page(JPanel a) {
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
        JOptionPane.showMessageDialog(null, a.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);

	}
	public static void main(String[] args) {
		Xbox main = Xbox.getInstance();
//    	new Client("Admin","1");
//    	new Client("1","1");

//		UserPage.load("1");
		login_or_register.load();
	}


}
