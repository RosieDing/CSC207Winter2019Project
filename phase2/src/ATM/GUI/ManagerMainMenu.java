package ATM.GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
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
	public ManagerMainMenu() {
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
		btnRestock.setBounds(16, 155, 125, 29);
		contentPane.add(btnRestock);
		
		JButton btnLogout = new JButton("Logout");
		btnLogout.setBounds(182, 155, 125, 29);
		contentPane.add(btnLogout);
	}

}
