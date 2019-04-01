package ATM.GUI.Staff;

import ATM.GUI.IDMenu;
import ATM.GUI.Manager.*;
import ATM.GUI.PasswordMenu;
import ATM.GUI.StartMenu;
import ATM.GUI.User.UserMainMenu;
import ATM.InfoHandling.InfoManager;
import ATM.BankIdentities.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class StaffMainMenu extends JFrame {

	private JPanel contentPane;


	/**
	 * Create the frame.
	 */
	public StaffMainMenu(String id, InfoManager infoManager) {
		PasswordManager passwordManager =  new PasswordManager(id);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnCreateUser = new JButton("Create User");
		btnCreateUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StaffMainMenu.this.dispose();
				new ManagerCreaterUser(id, infoManager).setVisible(true);
			}
		});
		btnCreateUser.setBounds(28, 25, 117, 29);
		contentPane.add(btnCreateUser);
		
		JButton btnCreateAccount = new JButton("Create Account");
		btnCreateAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StaffMainMenu.this.dispose();
				new ManagerCreateAccount(id, infoManager).setVisible(true);
			}
		});
		btnCreateAccount.setBounds(28, 87, 140, 29);
		contentPane.add(btnCreateAccount);
		
		JButton btnRestock = new JButton("Restock");
		btnRestock.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StaffMainMenu.this.dispose();
				new ManagerRestock(id, infoManager).setVisible(true);
			}
		});
		btnRestock.setBounds(197, 146, 140, 29);
		contentPane.add(btnRestock);
		
		JButton btnAccessUserOption = new JButton("Access User Options");
		btnAccessUserOption.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StaffMainMenu.this.dispose();
				new UserMainMenu(id, infoManager).setVisible(true);
			}
		});
		btnAccessUserOption.setBounds(197, 25, 156, 29);
		contentPane.add(btnAccessUserOption);
		
		JButton btnLogout = new JButton("Logout");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				passwordManager.logout();
				StaffMainMenu.this.dispose();
				infoManager.saveToFile();
				new IDMenu(infoManager).setVisible(true);


			}
		});
		btnLogout.setBounds(28, 146, 156, 29);
		contentPane.add(btnLogout);
		
		JButton btnCheckMachineBalance = new JButton("Check Machine Balance");
		btnCheckMachineBalance.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StaffMainMenu.this.dispose();
				new ManagerCheckMachineBalance(id, infoManager).setVisible(true);
			}
		});
		btnCheckMachineBalance.setBounds(197, 87, 200, 29);
		contentPane.add(btnCheckMachineBalance);
	}
}
