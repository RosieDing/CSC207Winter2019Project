package ATM.GUI.User;

import ATM.BankIdentities.User;
import ATM.InfoHandling.InfoManager;
import ATM.Machine.CashMachine;
import ATM.Transactions.Transaction;
import ATM.Transactions.TransactionManager;
import ATM.Accounts.Currency;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.util.Map;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

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
	public UserTransactionDeposit(Map<String, Object> transMap, String id,
								  TransactionManager tm, CashMachine machine, InfoManager infoManager) {
		transMap.put("toAccount", infoManager.getUser(id).getPrimaryChq());

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
		txtID.setEditable(false);
		txtID.setText(infoManager.getUser(id).getPrimaryChq().toString());
		txtID.setBounds(125, 75, 300, 26);
		contentPane.add(txtID);
		txtID.setColumns(10);
		
		JLabel lblAmount = new JLabel("Amount:");
		lblAmount.setBounds(41, 139, 82, 16);
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

		JButton btnTransfer = new JButton("Deposit");
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
		btnTransfer.setBounds(166, 216, 117, 29);
		contentPane.add(btnTransfer);

		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserTransactionDeposit.this.dispose();
				new UserMakeTransaction(id, infoManager).setVisible(true);
			}
		});
		btnBack.setBounds(19, 219, 117, 29);
		contentPane.add(btnBack);
	}

}
