package ATM.GUI.User;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

public class UserTransactionDeposit extends JFrame {

	private JPanel contentPane;
	private JTextField txtID;
	private JTextField txtAmount;

	/**
	 * Launch the application.
	 */


	/**
	 * Create the frame.
	 */
	public UserTransactionDeposit() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblDeposit = new JLabel("Deposit");
		lblDeposit.setBounds(194, 22, 61, 16);
		contentPane.add(lblDeposit);
		
		JLabel lblToAccount = new JLabel("To Account:");
		lblToAccount.setBounds(41, 80, 82, 16);
		contentPane.add(lblToAccount);
		
		txtID = new JTextField();
		txtID.setBounds(125, 75, 130, 26);
		contentPane.add(txtID);
		txtID.setColumns(10);
		
		JLabel lblAmount = new JLabel("Amount:");
		lblAmount.setBounds(41, 139, 82, 16);
		contentPane.add(lblAmount);
		
		txtAmount = new JTextField();
		txtAmount.setColumns(10);
		txtAmount.setBounds(125, 134, 130, 26);
		contentPane.add(txtAmount);
		
		JButton btnTransfer = new JButton("Transfer");
		btnTransfer.setBounds(166, 216, 117, 29);
		contentPane.add(btnTransfer);
	}

}
