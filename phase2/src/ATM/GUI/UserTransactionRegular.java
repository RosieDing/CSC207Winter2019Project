package ATM.GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

public class UserTransactionRegular extends JFrame {

	private JPanel contentPane;
	private JTextField txtFrom;

	/**
	 * Launch the application.
	 */


	/**
	 * Create the frame.
	 */
	public UserTransactionRegular() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblRegular = new JLabel("Regular Transaction:");
		lblRegular.setBounds(148, 18, 154, 16);
		contentPane.add(lblRegular);
		
		JLabel lblFromAccount = new JLabel("From Account:");
		lblFromAccount.setBounds(37, 68, 99, 16);
		contentPane.add(lblFromAccount);
		
		txtFrom = new JTextField();
		txtFrom.setBounds(144, 63, 130, 26);
		contentPane.add(txtFrom);
		txtFrom.setColumns(10);
		
		JButton btnNext = new JButton("Next");
		btnNext.setBounds(245, 168, 117, 29);
		contentPane.add(btnNext);
		
		JButton btnBack = new JButton("Back");
		btnBack.setBounds(26, 168, 117, 29);
		contentPane.add(btnBack);
	}

}
