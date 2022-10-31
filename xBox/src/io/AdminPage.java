package io;

import java.util.*;
import cmd.*;
import javax.swing.*;


import java.awt.event.*;  
import ex.*;
import xBox.*;

import java.awt.*;

public class AdminPage implements IO{
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
		label2 = new JLabel("[console log]:");

		btn0=new JButton("Quit");
		btn1=new JButton("Confirm Payment");
		btn2=new JButton("Confirm Return");
		btn3=new JButton("Search Items");
		btn4=new JButton("Search Clients");
		btn5=new JButton("Summary Requests");
		btn6=new JButton("Summary Records");


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
                Undoable.clearList(); // clear list
                login_or_register.load();
            }
        });	
		btn1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                confirmPayment.load();
            }
        });			
		btn2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                confirmReturn.load();
            }
        });	
		btn3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SearchItem.load();
            }                
        });	
		
		btn4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SearchClient.load();
            } 
        });	
		
		btn5.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    String results = AdminInterfaces.getInstance().summaryAllRequests();
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
                    String results = AdminInterfaces.getInstance().summaryAllRecords();
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
		AdminPage page =new AdminPage();
		// System.out.println("Turn to Admin page");
		main.show_page(page.show());
		return;
	}
}