package ATM.GUI.User;

import ATM.BankIdentities.UserAccManager;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JComboBox;

public class UserTransactionPayBill extends JFrame {

	private JPanel contentPane;
	private JTextField txtAmount;
	private JTextField txtTo;

	/**
	 * Launch the application.
	 */


	/**
	 * Create the frame.
	 */
	public UserTransactionPayBill(Map<String, Object> transMap, UserAccManager uam) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton button = new JButton("Transfer");
		button.setBounds(131, 218, 117, 29);
		contentPane.add(button);
		
		txtAmount = new JTextField();
		txtAmount.setColumns(10);
		txtAmount.setBounds(90, 136, 130, 26);
		contentPane.add(txtAmount);
		
		JLabel lblAmount = new JLabel("Amount:");
		lblAmount.setBounds(6, 141, 82, 16);
		contentPane.add(lblAmount);
		
		JLabel lblFromAccount = new JLabel("From Account:");
		lblFromAccount.setBounds(6, 82, 92, 16);
		contentPane.add(lblFromAccount);
		
		JLabel lblPayBill = new JLabel("Pay Bill");
		lblPayBill.setBounds(159, 24, 61, 16);
		contentPane.add(lblPayBill);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(110, 78, 110, 27);
		contentPane.add(comboBox);
		
		JLabel lblTo = new JLabel("To:");
		lblTo.setBounds(78, 110, 92, 16);
		contentPane.add(lblTo);
		
		txtTo = new JTextField();
		txtTo.setBounds(110, 105, 130, 26);
		contentPane.add(txtTo);
		txtTo.setColumns(10);
	}
}
