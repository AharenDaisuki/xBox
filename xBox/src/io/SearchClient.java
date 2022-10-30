package io;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import xBox.AdminInterfaces;
import xBox.Xbox;

public class SearchClient implements IO{
    private JPanel panel1;
    private JLabel label1;
    private JLabel label2;
    private JLabel label4;

    private JTextField jt1;

    private JButton btn1;
    private JButton btn2;
    
    @Override
    public JPanel show() {
        panel1 = new JPanel();
        label1 = new JLabel("Search Client");
        label2 = new JLabel("Client Email:");
        jt1 = new JTextField();

        

        btn1=new JButton("Run");
        btn2=new JButton("Back");

        panel1.setBounds(0, 0, 450, 300);
        panel1.setLayout(null);
        label1.setBounds(160, 50, 150, 40);
        label2.setBounds(110,100,100,40);

        jt1.setBounds(210,105,120,30);
        

        btn1.setBounds(275,140,50,35);
        btn2.setBounds(270,55,50,35);

        label4 = new JLabel("[console log]:");
        label4.setBounds(10,260,100,30);
        panel1.add(label4);
        
        String ret = AdminInterfaces.getInstance().summaryAllClients();
        Xbox.output(ret);
        
        btn2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                AdminPage.load();
            }
        }); 
        
        btn1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
//                login_or_register.load();
                String[] cmdLine= {jt1.getText()};
                String results;
                try {
                    results = AdminInterfaces.getInstance().summaryClient(cmdLine);
                    System.out.println(results);
                    Xbox.output(results);
                } catch (Exception e1) {
                    Xbox.error(e1);
                }
            }
        }); 

        panel1.add(label1);
        panel1.add(label2);

        panel1.add(btn1);
        panel1.add(btn2);

        panel1.add(jt1);

        panel1.setVisible(true);
        return panel1;
    }

    public static void load() {
        Xbox main = Xbox.getInstance();
        SearchClient page = new SearchClient();
        // System.out.println("Store page");
        main.show_page(page.show());
        return;
    }

}
