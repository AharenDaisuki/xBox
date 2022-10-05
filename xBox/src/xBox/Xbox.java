package xBox;

import java.util.*;
import javax.swing.*;
import java.awt.event.*;  
import ex.*;
import java.awt.*;
import app.*;

public class Xbox {
	public static Xbox instance;

	public static Xbox getInstance() {
		return instance;
	}
	public static void createInstance() {
		if(instance ==null) {
			instance =new Xbox();
		}
	}
	private JFrame jframe = init();
	private JPanel content = null;
	
	private JFrame init(){
		JFrame jFrame = new JFrame();
		double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		jFrame.setBounds(0, 0, 450, 300);
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
	
	
	public static void main(String[] args) {
		Xbox.createInstance();
		Xbox main = Xbox.getInstance();
		login_or_register.load();
	}


}
