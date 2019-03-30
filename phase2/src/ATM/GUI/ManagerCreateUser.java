package ATM.GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;

public class ManagerCreateUser extends JFrame {

	private JPanel contentPane;
	private JTextField txtUserNumber;

	/**
	 * Launch the application.
	 */


	/**
	 * Create the frame.
	 */
	public ManagerCreateUser() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtUserNumber = new JTextField();
		txtUserNumber.setBounds(76, 97, 130, 26);
		contentPane.add(txtUserNumber);
		txtUserNumber.setColumns(10);
		
		JButton btnCreate = new JButton("Create");
		btnCreate.setBounds(207, 97, 117, 29);
		contentPane.add(btnCreate);
		
		JLabel lblID = new JLabel("Please enter user id:");
		lblID.setBounds(129, 51, 154, 16);
		contentPane.add(lblID);
	}

}
