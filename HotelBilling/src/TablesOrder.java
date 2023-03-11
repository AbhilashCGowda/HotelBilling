import java.awt.EventQueue;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import java.awt.Font;
import javax.swing.SwingConstants;

public class TablesOrder {

	private JFrame frame;

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
		
		JComboBox comboBox = new JComboBox();
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
  
		 String eachline;
		 JPanel panel = new JPanel();
		 int y=85;
				try {
					BufferedReader reader = new BufferedReader(new FileReader("D:\\Java Class\\Project\\ItemCost.txt"));
					while ((eachline = reader.readLine()) != null) 
					{
					    String cols[] = eachline.split(",");
					    	//System.out.println(cols[0]);
				    	JCheckBox c = new JCheckBox(cols[0]);
								c.setBounds(6, y, 97, 23);
							frame.getContentPane().add(c);	    
							y+=20;
					   
				}
					reader.close();
				
				}

					catch (FileNotFoundException e) {
					e.printStackTrace();
					}
				frame.add(panel);
		        frame.setVisible(true);
		        
		        
		 
				
	}	
}
				
				
				
				