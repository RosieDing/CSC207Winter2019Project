package ATM.GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import ATM.AccountTypeChecker.*;
import ATM.Accounts.Account;
import ATM.Accounts.ChequingAccount;
import ATM.Accounts.CreditAccount;
import ATM.Accounts.Withdrawable;
import ATM.BankIdentities.*;
import ATM.InfoHandling.InfoManager;
import ATM.Machine.CashNotWithdrawableException;
import ATM.Machine.NotEnoughMoneyException;
import ATM.Transactions.NoTransactionException;
import ATM.Transactions.Transaction;
import ATM.Transactions.TransactionManager;
import ATM.Transactions.Withdrawal;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JOptionPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;;


public class UserMenu extends JFrame {

	private JPanel contentPane;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserMenu frame = new UserMenu(IDMenu.getID());
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
	public UserMenu(String id) {
		User user = IDMenu.getInfoManager().getUser(id);
        PasswordManager passwordManager = user.getPassManager();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNext = new JButton("Next");
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String password = new String(passwordField.getPassword());
				passwordManager.login(password);
				if (passwordManager.isLogin()){
					UserMenu.this.dispose();
					new UserMainMenu().setVisible(true);
				}else {
					JOptionPane.showMessageDialog(null, "Password wrong! Please enter again.");
			}
			}
		});
		btnNext.setBounds(288, 75, 117, 29);
		contentPane.add(btnNext);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(79, 80, 73, 16);
		contentPane.add(lblPassword);
		
		JLabel lblEnterPassword = new JLabel("Please enter your password");
		lblEnterPassword.setHorizontalAlignment(SwingConstants.CENTER);
		lblEnterPassword.setBounds(6, 27, 440, 16);
		contentPane.add(lblEnterPassword);
		
		JLabel lblWelcome = new JLabel("Welcome User!");
		lblWelcome.setHorizontalAlignment(SwingConstants.CENTER);
		lblWelcome.setBounds(6, 6, 440, 16);
		contentPane.add(lblWelcome);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(156, 75, 120, 26);
		contentPane.add(passwordField);
	}

}
