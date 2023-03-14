package HotelManagement;
import java.awt.EventQueue;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TablesOrder {

	private JFrame frame;
	JComboBox comboBox;
	JCheckBox[] checkbox ;
	Hashtable<String,ArrayList<String>> List ;
	ArrayList<String> selectedchkbx= new ArrayList<String>();
	JPanel panel;
	String selectedTablenum;
  	int r=0;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TablesOrder window = new TablesOrder();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	public TablesOrder() throws FileNotFoundException, IOException {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() throws FileNotFoundException, IOException {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 15));
		lblNewLabel.setBounds(66, 41, 98, 30);
		frame.getContentPane().add(lblNewLabel);
		
		comboBox = new JComboBox();
		comboBox.setFont(new Font("Arial", Font.PLAIN, 12));
    	comboBox.setBounds(198, 41, 143, 34);
		frame.getContentPane().add(comboBox);
		
				comboBox.addItem("Select a number");
				
		        // Read numbers from the text document
		        try {
		        	BufferedReader reader = new BufferedReader(new FileReader("D:\\Java Class\\Project\\TablesList.txt"));
		            String line;
		            while ((line = reader.readLine()) != null) {
		            	comboBox.addItem(line.trim());
		            }
		            reader.close();
		        } catch (IOException e1) {
		            e1.printStackTrace();
		        }
		        
      comboBox.addActionListener(new ActionListener(){
		        	 public void actionPerformed(ActionEvent e) {
		        		 selectedTablenum = comboBox.getSelectedItem().toString();
		        		// System.out.print(selectedTablenum);
		        	 }
		        });
      
			
		 String eachline;
		 JPanel panel = new JPanel();
		 int y=85;
				try {
					BufferedReader reader = new BufferedReader(new FileReader("D:\\Java Class\\Project\\ItemCost.txt"));
			      	checkbox= new JCheckBox [FileCounter.getCountRows()];

					while ((eachline = reader.readLine()) != null) 
					{
					    String cols[] = eachline.split(",");
				    	 checkbox[r] = new JCheckBox(cols[0]);
								checkbox[r].setBounds(6, y, 97, 23);
							frame.getContentPane().add(checkbox[r]);	    

				checkbox[r].addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent e) {
									JCheckBox checkbox = (JCheckBox) e.getSource();
								
							    if (checkbox.isSelected()) {
							        selectedchkbx.add(checkbox.getText());
							       
							        r++;
							    } 
							    else {
							        selectedchkbx.remove(checkbox.getText());
							    }
							    //System.out.print(checkbox.getText());
								}
							});
							y+=20;
							 
				}
					reader.close();
				
				}
					catch (FileNotFoundException e1) {
					e1.printStackTrace();
					}
				
			
				JButton submitbutton = new JButton("SUBMIT");
				submitbutton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						List = new Hashtable<String, ArrayList<String>>();
						{
							List.put(selectedTablenum, selectedchkbx);

						}
						
						Enumeration<String> e1 = List.keys();			
						while (e1.hasMoreElements()) {
							String t = e1.nextElement();				//key 
							ArrayList<String> value= List.get(t);		//value
							System.out.println(t + value);
						}
						
					 }});
				
				
				submitbutton.setFont(new Font("Arial", Font.PLAIN, 14));
				submitbutton.setBounds(279, 198, 110, 34);
				frame.getContentPane().add(submitbutton);
		        frame.setVisible(true);
		        
		        
	}
}
   			
				
				
				