package io;

import java.util.*;
import cmd.*;
import javax.swing.*;

import java.awt.event.*;  
import ex.*;
import xBox.*;

import java.awt.*;

public class login implements IO{
	private JPanel panel1;
	private JLabel label1;
	private JLabel label2;
	private JLabel label3;
	private JLabel label4;

	private JTextField jt1;
	private JTextField jt2;
	private JButton btn1;
	private JButton btn2;


	@Override
	public JPanel show() {
		panel1 = new JPanel();
		panel1.setBounds(0, 0, 450, 300);
		panel1.setLayout(null);
		label1 = new JLabel("Email:");
		label2 = new JLabel("Password:");
		label3 = new JLabel("login into the Xbox");

		label4 = new JLabel("Data output:");
		label4.setBounds(10,260,100,40);
		panel1.add(label4);
		
		jt1 = new JTextField();
		jt2 = new JTextField();
		btn1 = new JButton("login");
		btn2 = new JButton("Back");
		
		label1.setBounds(100,80,100,50);
		label2.setBounds(100,130,100,50);
		label3.setBounds(150,30,150,50);


		jt1.setBounds(200, 90, 150,30);
		jt2.setBounds(200, 140, 150,30);
		btn1.setBounds(300, 180, 50, 40);
		btn2.setBounds(300,40, 50, 35);
		
		btn1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	System.out.println(jt1.getText());
            	System.out.println(jt2.getText());
            	String[] cmdLine = {jt1.getText(),jt2.getText()};
            	try {
            	    String results = UserInterfaces.getInstance().login(cmdLine);
            	    Xbox.output(results);
            	    if(jt1.getText().equals("Admin")) {
            	        AdminPage.load();
            	    }else {
                        UserPage.load();
            	    }
            	}catch (Exception e1) {
            	    Xbox.error(e1);
            	}
            }
        });
		btn2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	login_or_register.load();
            }
        });
		
		panel1.add(label1);
		panel1.add(label2);
		panel1.add(label3);

		panel1.add(jt1);
		panel1.add(jt2);
		panel1.add(btn1);
		panel1.add(btn2);
		
		panel1.setVisible(true);
		return panel1;
	}

	public static void load() {
		Xbox main = Xbox.getInstance();
		login page =new login();
		System.out.println("Turn to login page");
		main.show_page(page.show());
		return;
	}
}