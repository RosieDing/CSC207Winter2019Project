package ATM.GUI.Staff;

import ATM.GUI.User.UserMainMenu;
import ATM.InfoHandling.InfoManager;

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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnCreateUser = new JButton("Create User");
		btnCreateUser.setBounds(28, 25, 117, 29);
		contentPane.add(btnCreateUser);
		
		JButton btnCreateAccount = new JButton("Create Account");
		btnCreateAccount.setBounds(268, 25, 140, 29);
		contentPane.add(btnCreateAccount);
		
		JButton btnRestock = new JButton("Restock");
		btnRestock.setBounds(28, 99, 140, 29);
		contentPane.add(btnRestock);
		
		JButton btnAccessUserOption = new JButton("Access User Option");
		btnAccessUserOption.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StaffMainMenu.this.dispose();
				new UserMainMenu(id, infoManager).setVisible(true);
			}
		});
		btnAccessUserOption.setBounds(268, 99, 156, 29);
		contentPane.add(btnAccessUserOption);
		
		JButton btnLogout = new JButton("Logout");
		btnLogout.setBounds(140, 175, 156, 29);
		contentPane.add(btnLogout);
	}
}
