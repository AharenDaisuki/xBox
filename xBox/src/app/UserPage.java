package app;

import java.util.*;
import javax.swing.*;
import java.awt.event.*;  
import ex.*;
import xBox.Xbox;

import java.awt.*;

public class UserPage implements IO{
	
	@Override
	public JPanel show(String userid) {
		JPanel panel1 = new JPanel();
		panel1.setBounds(0, 0, 450, 300);
		panel1.setLayout(null);

		
		
		panel1.setVisible(true);
		return panel1;
	}

	public static void load(String userid) {
		Xbox main = Xbox.getInstance();
		register page =new register();
		System.out.println("Turn to User page");
		main.show_page(page.show(null));
		return;
	}
}