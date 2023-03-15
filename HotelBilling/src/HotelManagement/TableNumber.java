package HotelManagement;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;


public class TableNumber {

	private JFrame frame;
	private JTextField txtbxtablenum;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TableNumber window = new TableNumber();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TableNumber() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		txtbxtablenum = new JTextField();
		txtbxtablenum.setFont(new Font("Arial", Font.PLAIN, 14));
		txtbxtablenum.setBounds(253, 109, 127, 30);
		frame.getContentPane().add(txtbxtablenum);
		txtbxtablenum.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("TABLES AVAILABLE");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 15));
		lblNewLabel.setBounds(55, 109, 149, 30);
		frame.getContentPane().add(lblNewLabel);
		
		JButton btnNewButton = new JButton("SUBMIT");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String item=txtbxtablenum.getText().trim();
				
				try {
					FileWriter fw= new FileWriter("D:\\Java Class\\Project\\TablesList.txt");
					
					PrintWriter p= new PrintWriter(fw);
					
					int num=Integer.parseInt(item);
					
					for(int i=1;i<=num;i++) {
					p.println(i);
					}
					p.close();
					
					JOptionPane.showMessageDialog(frame,"Saved Successfully");
					txtbxtablenum.setText("");
					OrderPrice op= new OrderPrice();		//opens OrderPrice page
				}
				catch(Exception  e1)
				{
					
				}
				
				
				
			}
		});
		btnNewButton.setFont(new Font("Arial", Font.PLAIN, 14));
		btnNewButton.setBounds(279, 209, 101, 30);
		frame.getContentPane().add(btnNewButton);
		
		
	}
}
