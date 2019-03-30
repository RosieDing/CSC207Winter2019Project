package ATM.GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

public class UserResetPassword extends JFrame {

	private JPanel contentPane;
	private JTextField txtReset;

	/**
	 * Launch the application.
	 */


	/**
	 * Create the frame.
	 */
	public UserResetPassword() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblResetPassword = new JLabel("Reset Password:");
		lblResetPassword.setBounds(48, 91, 133, 16);
		contentPane.add(lblResetPassword);
		
		txtReset = new JTextField();
		txtReset.setBounds(159, 86, 130, 26);
		contentPane.add(txtReset);
		txtReset.setColumns(10);
		
		JButton btnReset = new JButton("Reset");
		btnReset.setBounds(298, 86, 117, 29);
		contentPane.add(btnReset);
		
		JButton btnBack = new JButton("Back");
		btnBack.setBounds(159, 197, 117, 29);
		contentPane.add(btnBack);
	}

}
