package io;

import java.util.*;
import javax.swing.*;
import java.awt.event.*;  
import ex.*;
import java.awt.*;
import xBox.*;

public class login_or_register implements IO{
	
	private JPanel panel1;
	private JLabel label1;
	private JLabel label2;

	protected JButton btn1;
	protected JButton btn2;
	
	public login_or_register() {
        label1 = new JLabel("Welcome to use XBOX");
        btn1 = new JButton("login");
        btn2 = new JButton("register");
        
        label1.setBounds(150, 40, 300, 50);
        btn1.setBounds(100, 90, 100, 40);
        btn2.setBounds(230, 90, 100, 40);
        label2 = new JLabel("[console log]:");
        label2.setBounds(10,260,100,40);


        btn1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                login.load(new UserInterfaces(), new AdminInterfaces());
            }
        });
        
        btn2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                register.load(new UserInterfaces());
            }
        });
	}
	@Override
	public JPanel show() {
		panel1 = new JPanel();
		panel1.setBounds(0, 0, 450, 300);
		panel1.setLayout(null);
				
		panel1.add(label1);
        panel1.add(label2);


		panel1.add(btn1);
		panel1.add(btn2);
		
		panel1.setVisible(true);
		
		return panel1;
	}
	
	public static void load() {
		Xbox main = Xbox.getInstance();
		login_or_register page =new login_or_register();
		// System.out.println("Welcome to use xBox service!");
		main.show_page(page.show(),"login_or_register");
//		page.btn1.doClick();
	}
	
	public void clickbtn1() {
	    this.btn1.doClick();
	}
	public void clickbtn2() {
	    this.btn2.doClick();
    }
	
}