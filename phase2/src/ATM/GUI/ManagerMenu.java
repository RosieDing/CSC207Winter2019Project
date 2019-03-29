package ATM.GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import ATM.BankIdentities.AccountCreator;
import ATM.BankIdentities.BankManager;
import ATM.BankIdentities.PasswordManager;
import ATM.Machine.Money;
import ATM.Transactions.NoTransactionException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class ManagerMenu extends JFrame {

	private JPanel contentPane;
	private JPasswordField passwordField;
    


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ManagerMenu frame = new ManagerMenu(IDMenu.getID());
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
	public ManagerMenu(String id) {
		BankManager bankManager = IDMenu.getInfoManager().getBankManager(id);
	    PasswordManager passwordManager = bankManager.getPassManager();
	    
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblWelcomeManager = new JLabel("Welcome Manager!");
		lblWelcomeManager.setHorizontalAlignment(SwingConstants.CENTER);
		lblWelcomeManager.setBounds(6, 6, 440, 16);
		contentPane.add(lblWelcomeManager);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(79, 80, 73, 16);
		contentPane.add(lblPassword);
		
		JButton btnNext = new JButton("Next");
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String password = new String(passwordField.getPassword());
				passwordManager.login(password);
				if (passwordManager.isLogin()){
					ManagerMenu.this.dispose();
					new ManagerMainMenu(id).setVisible(true);
				}else {
					JOptionPane.showMessageDialog(null, "Password wrong! Please enter again.");
				
					
					
				}
			}
		});
		btnNext.setBounds(288, 75, 117, 29);
		contentPane.add(btnNext);
		
		JLabel lblEnterPassword = new JLabel("Please enter your password");
		lblEnterPassword.setHorizontalAlignment(SwingConstants.CENTER);
		lblEnterPassword.setBounds(6, 27, 440, 16);
		contentPane.add(lblEnterPassword);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(146, 75, 130, 26);
		contentPane.add(passwordField);
	}
}
