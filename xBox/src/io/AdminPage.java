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
	private JButton btn7;
	private JButton btn8;

	public AdminPage(AdminInterfaces a) {
	    label1 = new JLabel("Select one command");
        label2 = new JLabel("[console log]:");

        btn0=new JButton("Quit");
        btn1=new JButton("Confirm Payment");
        btn2=new JButton("Confirm Return");
        btn3=new JButton("Search Items");
        btn4=new JButton("Search Clients");
        btn5=new JButton("Summary Requests");
        btn6=new JButton("Summary Records");
        btn7=new JButton("Undo");
        btn8=new JButton("Redo");

        label1.setBounds(155, 15, 145, 40);
        label2.setBounds(10,260,100,40);
        
        btn0.setBounds(300,20,55,35);
        btn1.setBounds(80,60,135,40);
        btn2.setBounds(230,60,135,40);
        btn3.setBounds(80,110,135,40);
        btn4.setBounds(230,110,135,40);
        btn5.setBounds(80,160,135,40);
        btn6.setBounds(230,160,135,40);
        btn7.setBounds(80,210,135,40);
        btn8.setBounds(230,210,135,40);

        
        btn0.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Undoable.clearList(); // clear list
                login_or_register.load();
            }
        }); 
        btn1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                confirmPayment.load(a);
            }
        });         
        btn2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                confirmReturn.load(a);
            }
        }); 
        btn3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SearchItem.load(a);
            }                
        }); 
        
        btn4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SearchClient.load(a);
            } 
        }); 
        
        btn5.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               String results = a.summaryAllRequests();
               Xbox.output(results);
 
            }
        }); 
        
        btn6.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String results = a.summaryAllRecords();
                Xbox.output(results);
                 
            }
        }); 
        btn7.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    String results = a.undo();
                    Xbox.output(results);
                 }catch (Exception e1){
                     Xbox.error(e1);
                 }            
            }
        }); 
        btn8.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    String results = a.redo();
                    Xbox.output(results);
                 }catch (Exception e1){
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
		
		
		panel1.add(label1);
		panel1.add(label2);

		panel1.add(btn0);
		panel1.add(btn1);
		panel1.add(btn2);
		panel1.add(btn3);
		panel1.add(btn4);
		panel1.add(btn5);
		panel1.add(btn6);
        panel1.add(btn7);
        panel1.add(btn8);


		panel1.setVisible(true);
		return panel1;
	}

	public static void load(AdminInterfaces a) {
		Xbox main = Xbox.getInstance();
		AdminPage page =new AdminPage(a);
		// System.out.println("Turn to Admin page");
		main.show_page(page.show(),"AdminPage");
		return;
	}
	public void clickbtn0() {
	    this.btn0.doClick();
	}
	public void clickbtn1() {
	    this.btn1.doClick();
	}
	public void clickbtn2() {
	    this.btn2.doClick();
	}
	public void clickbtn3() {
	    this.btn3.doClick();
	}
	public void clickbtn4() {
	    this.btn4.doClick();
	}
	public void clickbtn5() {
	    this.btn5.doClick();
	}
	public void clickbtn6() {
	    this.btn6.doClick();
	}
	public void clickbtn7() {
        this.btn7.doClick();
	}
    public void clickbtn8() {
        this.btn8.doClick();
	}
}