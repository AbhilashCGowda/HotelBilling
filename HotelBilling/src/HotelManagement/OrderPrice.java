package HotelManagement;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class OrderPrice extends JFrame {

	private JFrame frame;
	private JTextField txtbxitem;
	private JTextField txtbxcost;

	public OrderPrice() {
		super("Orders");
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel txtitem = new JLabel("ITEM");
		txtitem.setFont(new Font("Arial", Font.PLAIN, 15));
		txtitem.setHorizontalAlignment(SwingConstants.CENTER);
		txtitem.setBackground(new Color(0, 128, 255));
		txtitem.setBounds(62, 50, 74, 30);
		frame.getContentPane().add(txtitem);
		
		JLabel txtcost = new JLabel("COST");
		txtcost.setFont(new Font("Arial", Font.PLAIN, 15));
		txtcost.setHorizontalAlignment(SwingConstants.CENTER);
		txtcost.setBounds(62, 114, 74, 30);
		frame.getContentPane().add(txtcost);
		
		txtbxitem = new JTextField();
		txtbxitem.setFont(new Font("Arial", Font.PLAIN, 15));
		txtbxitem.setBounds(223, 50, 96, 30);
		frame.getContentPane().add(txtbxitem);
		txtbxitem.setColumns(10);
		
		txtbxcost = new JTextField();
		txtbxcost.setFont(new Font("Arial", Font.PLAIN, 15));
		txtbxcost.setBounds(223, 114, 96, 30);
		frame.getContentPane().add(txtbxcost);
		txtbxcost.setColumns(10);
		
		JButton btnNewButton = new JButton("SUBMIT");
		btnNewButton.setFont(new Font("Arial", Font.PLAIN, 15));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String item=txtbxitem.getText();
				String cost=txtbxcost.getText();
				
				FileWriter fw;
				try {
					fw= new FileWriter("D:\\Java Class\\Project\\ItemCost.txt",true);
					
					PrintWriter p= new PrintWriter(fw);
					p.println(item.trim()+ "," + cost.trim());
					p.close();
					
					JOptionPane.showMessageDialog(frame,"Item Saved Successfully");
					txtbxitem.setText("");
					txtbxcost.setText("");
					
				}
				catch(Exception  e1) {
					e1.printStackTrace();
			    }
			}
		});
		btnNewButton.setBounds(154, 202, 107, 30);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("NEXT");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					TablesOrder to=new TablesOrder();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_1.setFont(new Font("Arial", Font.PLAIN, 14));
		btnNewButton_1.setBounds(298, 202, 96, 27);
		frame.getContentPane().add(btnNewButton_1);
		frame.setVisible(true);
	}
}
