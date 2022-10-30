package io;

import java.util.*;
import cmd.*;
import javax.swing.*;

import java.awt.event.*;  
import ex.*;
import xBox.UserInterfaces;
import xBox.Xbox;

import java.awt.*;

public class requestBox implements IO{
	private JPanel panel1;
	private JLabel label1;
	private JLabel label2;
	private JLabel label3;
	private JLabel label4;

	private JTextField jt1;
    private JTextField jt2;

	private JButton btn1;
	private JButton btn2;
	
	private JRadioButton jr1;
	private JRadioButton jr2;
	private ButtonGroup bg;

	@Override
	public JPanel show() {
		panel1 = new JPanel();
		label1 = new JLabel("Request");
		label2 = new JLabel("The number of items:");
		label3 = new JLabel("Rent duration (month):");
		jt1 = new JTextField();
	    jt2 = new JTextField();
	    jr1 = new JRadioButton("Box");
	    jr2 = new JRadioButton("Bag");
	    jr1.setSelected(true);
	    
	    bg = new ButtonGroup();

		btn1=new JButton("Run");
		btn2=new JButton("Back");

		panel1.setBounds(0, 0, 450, 300);
		panel1.setLayout(null);
		label1.setBounds(160, 50, 150, 40);
		label2.setBounds(40,100,190,40);
	    label3.setBounds(40,140,190,40);

		jt1.setBounds(230,105,120,30);
	    jt2.setBounds(230,145,120,30);
	    
	    jr1.setBounds(150, 180, 100, 30);
        jr2.setBounds(260, 180, 100, 30);

		btn1.setBounds(295,215,50,35);
		btn2.setBounds(290,55,50,35);

		label4 = new JLabel("[console log]:");
		label4.setBounds(10,260,100,30);
		panel1.add(label4);
		
		btn2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                UserPage.load();
            }
        });	
		btn1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
//                login_or_register.load();
                String type;
                if(jr1.isSelected()) {
                    type="BOX"; // TODO: all uppercase
                }else {
                    type="BAG"; // TODO: all uppercase
                }
            	String[] cmdLine= {type,jt1.getText(),jt2.getText()};
            	String results;
                try {
                    results = UserInterfaces.getInstance().request(cmdLine);
                    System.out.println(results);
                    Xbox.output(results);
                } catch (Exception e1) {
                    Xbox.error(e1);
                }

            }
        });	
		bg.add(jr1);
		bg.add(jr2);
		
		panel1.add(label1);
		panel1.add(label2);
	    panel1.add(label3);

		panel1.add(btn1);
		panel1.add(btn2);

		panel1.add(jt1);
        panel1.add(jt2);

        panel1.add(jr1);
        panel1.add(jr2);
		panel1.setVisible(true);
		return panel1;
	}

	public static void load() {
		Xbox main = Xbox.getInstance();
		requestBox page =new requestBox();
		// System.out.println("Request page");
		main.show_page(page.show());
		return;
	}
}