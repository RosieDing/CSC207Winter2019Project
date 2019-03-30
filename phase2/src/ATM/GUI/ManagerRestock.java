package ATM.GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

public class ManagerRestock extends JFrame {

	private JPanel contentPane;
	private JTextField txtFive;
	private JTextField txtTen;
	private JTextField txtTwenty;
	private JTextField txtFifty;

	/**
	 * Launch the application.
	 */


	/**
	 * Create the frame.
	 */
	public ManagerRestock() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblRestock = new JLabel("Restock");
		lblRestock.setBounds(174, 26, 61, 16);
		contentPane.add(lblRestock);
		
		JLabel lblFive = new JLabel("Number of Five dollars:");
		lblFive.setBounds(42, 70, 171, 16);
		contentPane.add(lblFive);
		
		JLabel lblTen = new JLabel("Number of Ten dollars:");
		lblTen.setBounds(42, 112, 171, 16);
		contentPane.add(lblTen);
		
		JLabel lblTwenty = new JLabel("Number of Twenty dollars:");
		lblTwenty.setBounds(42, 158, 171, 16);
		contentPane.add(lblTwenty);
		
		JLabel lblFifty = new JLabel("Number of Fifty dollars:");
		lblFifty.setBounds(42, 206, 171, 16);
		contentPane.add(lblFifty);
		
		txtFive = new JTextField();
		txtFive.setBounds(201, 65, 130, 26);
		contentPane.add(txtFive);
		txtFive.setColumns(10);
		
		txtTen = new JTextField();
		txtTen.setColumns(10);
		txtTen.setBounds(201, 107, 130, 26);
		contentPane.add(txtTen);
		
		txtTwenty = new JTextField();
		txtTwenty.setColumns(10);
		txtTwenty.setBounds(225, 153, 130, 26);
		contentPane.add(txtTwenty);
		
		txtFifty = new JTextField();
		txtFifty.setColumns(10);
		txtFifty.setBounds(201, 201, 130, 26);
		contentPane.add(txtFifty);
		
		JButton btnRestock = new JButton("Restock");
		btnRestock.setBounds(327, 239, 117, 29);
		contentPane.add(btnRestock);
		
		JButton btnBack = new JButton("Back");
		btnBack.setBounds(6, 239, 117, 29);
		contentPane.add(btnBack);
	}

}
