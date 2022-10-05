package app;

import java.util.*;
import javax.swing.*;
import java.awt.event.*;  
import ex.*;
import xBox.Xbox;

import java.awt.*;

public class register implements IO{
	
	@Override
	public JPanel show(String userid) {
		JPanel panel1 = new JPanel();
		panel1.setBounds(0, 0, 450, 300);
		panel1.setLayout(null);
		JLabel label1 = new JLabel("Email:");
		JLabel label2 = new JLabel("Password:");
		JLabel label3 = new JLabel("Register a account");

		JTextField jt1 = new JTextField();
		JTextField jt2 = new JTextField();
		JButton btn1 = new JButton("register");
		JButton btn2 = new JButton("Back");
		
		label1.setBounds(100,100,100,50);
		label2.setBounds(100,150,100,50);
		label3.setBounds(150,50,150,50);


		jt1.setBounds(200, 110, 150,30);
		jt2.setBounds(200, 160, 150,30);
		btn1.setBounds(290, 200, 60, 40);
		btn2.setBounds(300,60, 50, 35);
		
		btn1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	System.out.println(jt1.getText());
            	System.out.println(jt2.getText());
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
		register page =new register();
		System.out.println("Turn to register page");
		main.show_page(page.show(null));
		return;
	}
}