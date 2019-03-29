package ATM.GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;

public class UserTransactionWithdrawal extends JFrame {

	private JPanel contentPane;
	private JTextField txtAmount;
	private JTextField txtID;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserTransactionWithdrawal frame = new UserTransactionWithdrawal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public UserTransactionWithdrawal() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnTransfer = new JButton("Transfer");
		btnTransfer.setBounds(131, 216, 117, 29);
		contentPane.add(btnTransfer);
		
		txtAmount = new JTextField();
		txtAmount.setColumns(10);
		txtAmount.setBounds(90, 134, 130, 26);
		contentPane.add(txtAmount);
		
		JLabel lblAmount = new JLabel("Amount:");
		lblAmount.setBounds(28, 139, 60, 16);
		contentPane.add(lblAmount);
		
		JLabel lblFromAccount = new JLabel("From Account:");
		lblFromAccount.setBounds(6, 80, 110, 16);
		contentPane.add(lblFromAccount);
		
		txtID = new JTextField();
		txtID.setColumns(10);
		txtID.setBounds(104, 75, 130, 26);
		contentPane.add(txtID);
		
		JLabel lblWithdrawal = new JLabel("Withdrawal");
		lblWithdrawal.setBounds(159, 22, 89, 16);
		contentPane.add(lblWithdrawal);
	}

}
