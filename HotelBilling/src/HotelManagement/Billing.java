package HotelManagement;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JCheckBox;

import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Scanner;
import java.awt.event.ActionEvent;

public class Billing extends JFrame {
	
	private JFrame frame;
	Hashtable<String, Integer> pricelist ;
	JComboBox<String> comboBox= new JComboBox<String>();
	Hashtable<String, Integer> foodlist;
	BufferedReader reader;
	String line;
	int selectedTablenum=0;
	 int quantity ;
	Hashtable<Integer, Integer> billlist;
	Integer key;
	Integer value;
	
	public Billing() throws NumberFormatException, IOException {
		super("billing");
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

//taking first array from GenerateBill document and adding it as dropdown
	String eachline;
	comboBox.addItem("Select a number");
		try {
			BufferedReader reader = new BufferedReader(new FileReader("D:\\Java Class\\Project\\GenerateBill.txt"));
			
			while ((eachline = reader.readLine()) != null) {               
				String cols[] = eachline.split("-");					//splitting the text using delimiter
				comboBox.addItem(cols[0].trim());						//adding values to dropdown
				//System.out.println(cols[0]);
			}
				reader.close();
		}
				catch (IOException e1) {
					e1.printStackTrace();
		}
//Second Hashtable with items list as key and Price as Value
		foodlist = new Hashtable<String, Integer>();
		String line;

		try (BufferedReader br = new BufferedReader(new FileReader("D:\\Java Class\\Project\\ItemCost.txt"))) {
		    while ((line = br.readLine()) != null) {
		        String[] values = line.split(",");
		        String name = values[0];									//assigning first array to String name
		        int price = Integer.parseInt(values[1]);					//assigning second array to price
		        foodlist.put(name, price);
		    }
		} catch (IOException e) {
		    e.printStackTrace();
		}

// Print the hashtable to verify it
		Enumeration<String> keys = foodlist.keys();
		while (keys.hasMoreElements()) {
		    String key = keys.nextElement();
		    int value = foodlist.get(key);
		  //System.out.println(key + " : " + value);
		}

		
//Hashtable to get table number and price of that particular table	
		billlist= new Hashtable<Integer, Integer> ();					//hashtable
			try {
		BufferedReader reader = new BufferedReader(new FileReader("D:\\Java Class\\Project\\GenerateBill.txt"));
		
		while ((line = reader.readLine()) != null) {
		    String[] cols = line.split("-");								
		    quantity = Integer.parseInt(cols[0]);							
		    String[] dish = cols[1].replaceAll("[\\[\\]]", "").split(", ");
		    int totalPrice = 0;
		    for (String item : dish) {
		        totalPrice += foodlist.get(item);
		    }
		    String name=  dish + " : " + totalPrice;
		    //System.out.println("Total price for " + line + ": " + totalPrice);
				billlist.put(quantity,totalPrice);
		}
		}
			catch (IOException e) {
			    e.printStackTrace();
			}
			
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			    }
			});
		comboBox.setBounds(259, 53, 153, 33);
		frame.getContentPane().add(comboBox);
		
		JLabel lblNewLabel = new JLabel("SELECT TABLE NUMBER");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 15));
		lblNewLabel.setBounds(35, 53, 193, 33);
		frame.getContentPane().add(lblNewLabel);
		
		
//choose option from dropdown and clcik on Generate bill 		
		JButton btnNewButton = new JButton("GENERATE BILL");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectedTablenum =Integer.parseInt( (String)comboBox.getSelectedItem());
				 int totalPrice = 0;
				 Enumeration<Integer> e1 = billlist.keys();
			        while (e1.hasMoreElements()) {
			            int key = e1.nextElement();
			            if (key == selectedTablenum) {
			                totalPrice = billlist.get(key);
			                break;
			            }
			        }
				
			        JOptionPane.showMessageDialog(frame, "Bill Generated for" + selectedTablenum);
			   
//using printwriter to print the bill in the textdocument
			        
			        PrintWriter pwriter;

			        try {
			        pwriter=new PrintWriter(new FileWriter("D:\\Java Class\\Project\\" + selectedTablenum + ".txt"));
			        Calendar cal = Calendar.getInstance();

			        int year = cal.get(Calendar.YEAR);
			        int month = cal.get(Calendar.MONTH) + 1;
			        int day = cal.get(Calendar.DAY_OF_MONTH);
			        int hour = cal.get(Calendar.HOUR_OF_DAY);
			        int minute = cal.get(Calendar.MINUTE);
			        int second = cal.get(Calendar.SECOND);

			        // Print the date and time
			        pwriter.println("                  Date: " + year + "-" + month + "-" + day);
			        pwriter.println("                  Time: " + hour + ":" + minute + ":" + second);
			        
			        pwriter.println("           ------------------------------------");
			        
			        pwriter.println("       Total bill amount for table " + selectedTablenum + " is " + totalPrice);
			        
			        pwriter.println("           ------------------------------------");
			        
			        pwriter.println("               THANK YOU, VISIT AGAIN");
			        pwriter.close();
			 }catch(Exception e2) {
			        
			 }      
			}});
		btnNewButton.setFont(new Font("Arial", Font.PLAIN, 14));
		btnNewButton.setBounds(219, 196, 158, 38);
		frame.getContentPane().add(btnNewButton);
		frame.setVisible(true);
	}
	
}