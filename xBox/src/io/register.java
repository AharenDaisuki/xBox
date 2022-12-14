package io;

import java.util.*;
import javax.swing.*;
import java.awt.event.*;  
import ex.*;
import xBox.AdminInterfaces;
import xBox.UserInterfaces;
import xBox.Xbox;
import java.awt.*;
import cmd.*;

public class register implements IO{
	
	private JPanel panel1;
	private JLabel label1;
	private JLabel label2;
	private JLabel label3;
	private JLabel label4;
    private JLabel label5;

	
	private JTextField jt1;
	private JTextField jt2;
	private JTextField jt3;

	private JButton btn1;
	private JButton btn2;
	
	private JRadioButton jr1;
    private JRadioButton jr2;
    private ButtonGroup bg;

    public register(UserInterfaces u) {
        label1 = new JLabel("Email:");
        label2 = new JLabel("Password:");
        label3 = new JLabel("Register an account:");
        label5 = new JLabel("Phone number:");
        bg = new ButtonGroup();    

        jt1 = new JTextField();
        jt2 = new JTextField();
        jt3 = new JTextField();
        
        jr1 = new JRadioButton("student");
        jr2 = new JRadioButton("staff");

        btn1 = new JButton("Register");
        btn2 = new JButton("Back");
        
        label1.setBounds(100,50,100,50);
        label2.setBounds(100,110,100,50);
        label3.setBounds(150,10,150,50);
        label5.setBounds(100, 80, 100, 50);

        label4 = new JLabel("[console log]:");
        label4.setBounds(10,260,100,40);
        
        jt1.setBounds(200, 60, 150,30);
        jt2.setBounds(200, 120, 150,30);
        jt3.setBounds(200, 90, 150,30);

        btn1.setBounds(290, 190, 65, 40);
        btn2.setBounds(300,20, 50, 35);
        jr1.setBounds(120, 150, 130, 50);
        jr2.setBounds(250, 150, 140, 50);

        btn1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String type;
                // System.out.println(jt1.getText());
                // System.out.println(jt2.getText());
                if(jr1.isSelected()) {
                   type = "student";
                }else {
                    type = "staff";
                }
                // TODO: add new type
                String[] cmdLine = {jt1.getText(),jt3.getText(),jt2.getText(),type};
                try {
                    String results=u.register(cmdLine);
                    Xbox.output(results);
                    login_or_register.load();
                }catch(Exception e1) {
                    Xbox.error(e1);
                };
            }
        });
        btn2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                login_or_register.load();
            }
        });
        jr1.setSelected(true);
        bg.add(jr1);bg.add(jr2);
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
	    panel1.add(label5);

		panel1.add(jt1);
		panel1.add(jt2);
	    panel1.add(jt3);

		panel1.add(btn1);
		panel1.add(btn2);
		panel1.add(jr1);
	    panel1.add(jr2);

		panel1.setVisible(true);
		return panel1;
	}

	public static void load(UserInterfaces u) {
		Xbox main = Xbox.getInstance();
		register page =new register(u);
		// System.out.println("Register page");
		main.show_page(page.show(),"register");
		return;
	}
	public void clickbtn1(String a,String b,String c,String d) {
	    if(d.equals("student") ) {
            jr1.setSelected(true);
         }else {
            jr2.setSelected(true);
         }
	    jt1.setText(a);
	    jt2.setText(b);
	    jt3.setText(c);
	    this.btn1.doClick();
	}
	
	public void clickbtn2() {
        this.btn2.doClick();
    }
}