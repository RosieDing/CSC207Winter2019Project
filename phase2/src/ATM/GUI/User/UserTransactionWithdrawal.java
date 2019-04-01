package ATM.GUI.User;

import ATM.Accounts.Currency;
import ATM.BankIdentities.UserAccManager;
import ATM.InfoHandling.InfoManager;
import ATM.Machine.CashMachine;
import ATM.Transactions.Transaction;
import ATM.Transactions.TransactionManager;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.util.Map;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class UserTransactionWithdrawal extends JFrame {

	private JPanel contentPane;
	private JTextField txtAmount;
	private JTextField txtID;



	/**
	 * Create the frame.
	 */
	public UserTransactionWithdrawal(Map<String, Object> transMap, String id,
									 TransactionManager tm, CashMachine machine, InfoManager infoManager) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnTransfer = new JButton("Withdraw");
		btnTransfer.addKeyListener(new KeyAdapter() {
			public void keyReleased(java.awt.event.KeyEvent evt) {
				try {
					Transaction trans = tm.makeTransaction(transMap, machine);
					if (trans.isHappened()) {
						JOptionPane.showMessageDialog(null, "Deposit successful!");
					}
				} catch (NullPointerException e) {
					JOptionPane.showMessageDialog(rootPane, "Transaction is not possible.");
				} catch (Exception e) {
					JOptionPane.showMessageDialog(rootPane, e.getMessage());
				}
			}
		});
		btnTransfer.setBounds(131, 216, 117, 29);
		contentPane.add(btnTransfer);

		JLabel lblFromAccount = new JLabel("From Account:");
		lblFromAccount.setBounds(6, 80, 110, 16);
		contentPane.add(lblFromAccount);
		//need to add accounts


		txtID = new JTextField();
		txtID.setColumns(10);
		txtID.setBounds(104, 75, 130, 26);
		contentPane.add(txtID);
		
		txtAmount = new JTextField();
		txtAmount.setColumns(10);
		txtAmount.setBounds(90, 134, 130, 26);
		contentPane.add(txtAmount);
		
		JLabel lblAmount = new JLabel("Amount:");
		lblAmount.setBounds(28, 139, 60, 16);
		contentPane.add(lblAmount);

		txtAmount = new JTextField();
		txtAmount.addKeyListener(new KeyAdapter() {
			public void keyReleased(java.awt.event.KeyEvent evt) {
				try {
					long number = Long.parseLong(txtAmount.getText());
				} catch (Exception e) {
					JOptionPane.showMessageDialog(rootPane, "Only Numbers Allowed");
					txtAmount.setText("");
				}
			}

		});

		txtAmount.setColumns(10);
		txtAmount.setBounds(125, 134, 130, 26);
		contentPane.add(txtAmount);
		Currency amount = new Currency(Double.valueOf(txtAmount.getText()));
		transMap.put("amount", amount);

		
//		txtID = new JTextField();
//		txtID.setColumns(10);
//		txtID.setBounds(104, 75, 130, 26);
//		contentPane.add(txtID);

		JLabel lblWithdrawal = new JLabel("Withdrawal");
		lblWithdrawal.setBounds(159, 22, 89, 16);
		contentPane.add(lblWithdrawal);

		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserTransactionWithdrawal.this.dispose();
				new UserMakeTransaction(id, infoManager).setVisible(true);
			}
		});
		btnBack.setBounds(19, 219, 117, 29);
		contentPane.add(btnBack);
	}

}
