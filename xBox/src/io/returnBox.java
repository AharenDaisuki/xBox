package io;

import java.util.*;
import cmd.*;
import javax.swing.*;

import java.awt.event.*;  
import ex.*;
import xBox.UserInterfaces;
import xBox.Xbox;

import java.awt.*;

/**
 * @author dongjiajie
 * @brief return
 * 
 * UI Interface for user function [return]
 */

public class returnBox implements IO{
	private JPanel panel1;
	private JLabel label1;
	private JLabel label2;
	private JLabel label4;

	private JTextField jt1;

	private JButton btn1;
	private JButton btn2;
	public returnBox(UserInterfaces u) {
	    label1 = new JLabel("Return");
        label2 = new JLabel("Enter Item ID:");
        jt1 = new JTextField();

        

        btn1=new JButton("Run");
        btn2=new JButton("Back");
        label1.setBounds(160, 50, 150, 40);
        label2.setBounds(110,100,100,40);
        jt1.setBounds(210,105,120,30);
        

        btn1.setBounds(275,140,50,35);
        btn2.setBounds(270,55,50,35);

        label4 = new JLabel("[console log]:");
        label4.setBounds(10,260,100,30);
        btn2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                UserPage.load(u);
            }
        }); 
        btn1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String[] cmdLine= jt1.getText().split(" ");
                String results;
                try {
                    results = u.unload(cmdLine);
                    System.out.println(results);
                    Xbox.output(results);
                } catch (Exception e1) {
                    Xbox.error(e1);
                }

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

		panel1.add(btn1);
		panel1.add(btn2);

		panel1.add(jt1);

		panel1.setVisible(true);
		return panel1;
	}

	public static void load(UserInterfaces u) {
		Xbox main = Xbox.getInstance();
		returnBox page =new returnBox(u);
		// System.out.println("Return page");
		main.show_page(page.show(),"returnBox");
		return;
	}
	public void clickbtn2() {
	    this.btn2.doClick();
	}
	public void clickbtn1(String a) {
	    this.jt1.setText(a);
	    this.btn1.doClick();
	}
}