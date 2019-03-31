package ATM.GUI.User;

import ATM.BankIdentities.*;
import ATM.InfoHandling.InfoManager;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class UserMainMenu extends JFrame {

	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public UserMainMenu(String id, InfoManager infoManager) {
		PasswordManager passwordManager = new PasswordManager(id);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblWelcomeUser = new JLabel("Welcome User!");
		lblWelcomeUser.setHorizontalAlignment(SwingConstants.CENTER);
		lblWelcomeUser.setBounds(6, 6, 440, 16);
		contentPane.add(lblWelcomeUser);
		
		JButton btnNettotalbalance = new JButton("Net Total Balance");
		btnNettotalbalance.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserMainMenu.this.dispose();
				new UserNetTotal(id, infoManager).setVisible(true);
			}
		});
		btnNettotalbalance.setBounds(176, 34, 187, 29);
		contentPane.add(btnNettotalbalance);
		
		JButton btnSummary = new JButton("Summary");
		btnSummary.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserMainMenu.this.dispose();
				new UserSummary(id, infoManager).setVisible(true);
			}
		});
		btnSummary.setBounds(16, 34, 117, 29);
		contentPane.add(btnSummary);
		
		JButton btnYourAccounts = new JButton("Your Accounts");
		btnYourAccounts.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserMainMenu.this.dispose();
				new UserYourAccounts(id, infoManager).setVisible(true);
			}
		});
		btnYourAccounts.setBounds(16, 94, 125, 29);
		contentPane.add(btnYourAccounts);
		
		JButton btnMakeTransaction = new JButton("Make Transaction");
		btnMakeTransaction.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserMainMenu.this.dispose();
				new UserMakeTransaction(id, infoManager).setVisible(true);
			}
		});
		btnMakeTransaction.setBounds(16, 155, 148, 29);
		contentPane.add(btnMakeTransaction);
		
		JButton btnRequestForAccount = new JButton("Request for account creation");
		btnRequestForAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserMainMenu.this.dispose();
				new UserRequestCreationOfAccount(id, infoManager).setVisible(true);
			}
		});
		btnRequestForAccount.setBounds(176, 155, 225, 29);
		contentPane.add(btnRequestForAccount);
		
		JButton btnSetPrimaryAccount = new JButton("Set Primary Account");
		btnSetPrimaryAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserMainMenu.this.dispose();
				new UserSetPrimaryChequing(id, infoManager).setVisible(true);
			}
		});
		btnSetPrimaryAccount.setBounds(176, 94, 198, 29);
		contentPane.add(btnSetPrimaryAccount);
		
		JButton btnChangePassword = new JButton("Change Password");
		btnChangePassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserMainMenu.this.dispose();
				new UserResetPassword(id, infoManager).setVisible(true);
			}
		});
		btnChangePassword.setBounds(16, 214, 148, 29);
		contentPane.add(btnChangePassword);
		
		JButton btnLogout = new JButton("Logout");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				passwordManager.logout();
				UserMainMenu.this.dispose();
				infoManager.saveToFile();
			}
		});
		btnLogout.setBounds(176, 214, 148, 29);
		contentPane.add(btnLogout);
	}

}
