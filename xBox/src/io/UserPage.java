package io;

import java.util.*;
import cmd.*;
import javax.swing.*;


import java.awt.event.*;  
import ex.*;
import xBox.UserInterfaces;
import xBox.Xbox;

import java.awt.*;

public class UserPage implements IO{
	private JPanel panel1;
	private JLabel label1;
	private JLabel label2;
	
	private JButton btn0;
	private JButton btn1;
	private JButton btn2;
	private JButton btn3;
	private JButton btn4;
	private JButton btn5;	
	private JButton btn6;


	@Override
	public JPanel show() {
		panel1 = new JPanel();
		label1 = new JLabel("Select one command");
		label2 = new JLabel("Data output:");

		btn0=new JButton("Back");
		btn1=new JButton("Request new Box");
		btn2=new JButton("Store Box");
		btn3=new JButton("Return Box");
		btn4=new JButton("Summary");
		btn5=new JButton("undo");
		btn6=new JButton("redo");


		panel1.setBounds(0, 0, 450, 300);
		panel1.setLayout(null);
		
		label1.setBounds(155, 15, 145, 40);
		label2.setBounds(10,260,100,40);
		btn0.setBounds(300,20,55,35);
		btn1.setBounds(80,60,135,40);
		btn2.setBounds(230,60,135,40);
		btn3.setBounds(80,110,135,40);
		btn4.setBounds(230,110,135,40);
		btn5.setBounds(80,160,135,40);
		btn6.setBounds(230,160,135,40);

		
		btn0.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                login_or_register.load();
            }
        });	
		btn1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                requestBox.load();
            }
        });			
		btn2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                storeBox.load();
            }
        });	
		btn3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                returnBox.load();
            }
        });	
		btn4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    String results = UserInterfaces.getInstance().summary(null);
                    Xbox.output(results);
                 }catch (Exception e1){
                     Xbox.error(e1);
                 }              
               }
        });	
		btn5.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    String results = UserInterfaces.getInstance().undo();
                    Xbox.output(results);
                 }catch (Exception e1){
                     Xbox.error(e1);
                 }            
            }
        });	
		btn6.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
//                getBox.load(userid);
                try {
                   String results = UserInterfaces.getInstance().redo();
                   Xbox.output(results);
                }catch (Exception e1){
                    Xbox.error(e1);
                }
            }
        });	
		panel1.add(label1);
		panel1.add(label2);

		panel1.add(btn0);
		panel1.add(btn1);
		panel1.add(btn2);
		panel1.add(btn3);
		panel1.add(btn4);
		panel1.add(btn5);
		panel1.add(btn6);


		panel1.setVisible(true);
		return panel1;
	}

	public static void load() {
		Xbox main = Xbox.getInstance();
		UserPage page =new UserPage();
		System.out.println("Turn to User page");
		main.show_page(page.show());
		return;
	}
}