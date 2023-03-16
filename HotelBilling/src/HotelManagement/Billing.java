package HotelManagement;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Enumeration;
import java.util.Hashtable;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

public class Billing  {
	
	private JFrame frame;
	JComboBox<String> comboBox= new JComboBox<String>();
	Hashtable<String, Integer> foodlist =  new Hashtable<String, Integer>();
	int totalPrice = 0;
	String selectedTablenum= "";
	Hashtable<String, Integer> billlist= new Hashtable<String, Integer> ();
	Hashtable<String,ArrayList<String>> tableorderlist =null;
	
	public Billing(	Hashtable<String,ArrayList<String>> referncevariable) {
		super();
		tableorderlist =referncevariable;
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

//taking first array from GenerateBill document and adding it as dropdown
	comboBox.addItem("Select a number");
	 Enumeration <String> k = tableorderlist.keys();
	 while(k.hasMoreElements())
	 {
		 String key = (String)k.nextElement();
				comboBox.addItem(key);						//adding values to dropdown
	 }
		 
//Second Hashtable with items list as key and Price as Value

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
		 // System.out.println(key + " : " + value);
		}

		
//Hashtable to get table number and price of that particular table	
		//billlist= new Hashtable<Integer, Integer> ();					//hashtable
		try {
			Enumeration<String> price=tableorderlist.keys();
			while(price.hasMoreElements()) {
				String key=price.nextElement();
				ArrayList<String> value=tableorderlist.get(key);
				System.out.println(value);
			    for (String itemlist : value) {
			        totalPrice += foodlist.get(itemlist);
	   }
	   //System.out.println("Total price for " + key + ": " + totalPrice);
				billlist.put(key,totalPrice);
	}
			//System.out.println(billlist);
		}catch(Exception e) {
			
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
				selectedTablenum =(String)comboBox.getSelectedItem();
				Enumeration<String> e1 = billlist.keys();
				while (e1.hasMoreElements()) {
					String key = e1.nextElement();
					if (key.equalsIgnoreCase(selectedTablenum)){
			                totalPrice = billlist.get(key);
			                break;
			            }
			        }
				
			        JOptionPane.showMessageDialog(frame, "Bill Generated for " + selectedTablenum);
			   
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