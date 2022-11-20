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
	
	private final String adminEmail = "admin@xbox.com.hk";
	
	public login(UserInterfaces u, AdminInterfaces a) {
        label1 = new JLabel("Email:");
        label2 = new JLabel("Password:");
        label3 = new JLabel("login into the Xbox");

        label4 = new JLabel("[console log]:");
        label4.setBounds(10,260,100,40);
        jt1 = new JTextField();
        jt2 = new JTextField();
        btn1 = new JButton("Login");
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

                String[] cmdLine = {jt1.getText(),jt2.getText()};
                try {
                    String results = u.login(cmdLine); // invoke
                    Xbox.output(results);
                    if(jt1.getText().equals(adminEmail)) {
                        AdminPage.load(a);
                    }else {
                        UserPage.load(u);
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
                
	}
	@Override
	public JPanel show() {
		panel1 = new JPanel();
		panel1.setBounds(0, 0, 450, 300);
		panel1.setLayout(null);

		panel1.add(label4);
		

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

	public static void load(UserInterfaces u, AdminInterfaces a) {
		Xbox main = Xbox.getInstance();
		login page =new login(u,a);
		// System.out.println("Login page");
		main.show_page(page.show(),"login");
		return;
	}
	public void clickbtn1(String a,String b) {
	    this.jt1.setText(a);
	    this.jt2.setText(b);
	    this.btn1.doClick();
    }
	public void clickbtn2() {
	    this.btn2.doClick();
	}
}