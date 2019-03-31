package ATM.GUI.Manager;

import ATM.BankIdentities.*;
import ATM.InfoHandling.InfoManager;
import ATM.InfoHandling.InfoStorer;
import ATM.BankSystem.BankSystem;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ManagerMainMenu extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */


	/**
	 * Create the frame.
	 */
	public ManagerMainMenu(String id, InfoManager infoManager) {
        PasswordManager passwordManager = new PasswordManager(id);
        InfoStorer infoStorer = infoManager.getInfoStorer();
        BankManager bankManager = infoManager.getBankManager(id);


		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("Welcome Manager!");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(6, 6, 440, 16);
		contentPane.add(label);
		
		JButton btnCreateUser = new JButton("Create User");
		btnCreateUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String ID = bankManager.createUser(infoManager.getUserNum(), infoStorer.getUserMap(), infoStorer.getPasswordMap(), infoStorer.getAccountMap());
            	JOptionPane.showMessageDialog(null, "New user created! user ID: " + ID);

			}
		});
		btnCreateUser.setBounds(16, 34, 117, 29);
		contentPane.add(btnCreateUser);
		
		JButton btnUndoTransaction = new JButton("Undo User Transaction");
		btnUndoTransaction.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnUndoTransaction.setBounds(145, 34, 187, 29);
		contentPane.add(btnUndoTransaction);
		
		JButton btnUndoAccountTransaction = new JButton("Undo Account Transaction");
		btnUndoAccountTransaction.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnUndoAccountTransaction.setBounds(145, 94, 198, 29);
		contentPane.add(btnUndoAccountTransaction);
		
		JButton btnCreateAccount = new JButton("Create Account");
		btnCreateAccount.setBounds(16, 94, 125, 29);
		contentPane.add(btnCreateAccount);
		
		JButton btnRestock = new JButton("Restock");
		btnRestock.setBounds(16, 200, 125, 29);
		contentPane.add(btnRestock);
		
		JButton btnLogout = new JButton("Logout");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                passwordManager.logout();
                ManagerMainMenu.this.dispose();
			}
		});
		btnLogout.setBounds(183, 165, 125, 29);
		contentPane.add(btnLogout);
		
		JButton btnCreatestaff = new JButton("Create Staff");
		btnCreatestaff.setBounds(16, 142, 117, 29);
		contentPane.add(btnCreatestaff);
	}
}
