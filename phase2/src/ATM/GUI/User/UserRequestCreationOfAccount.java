package ATM.GUI.User;

import ATM.InfoHandling.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class UserRequestCreationOfAccount extends JFrame {

	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public UserRequestCreationOfAccount(String id, InfoManager infoManager) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnChequingAccount = new JButton("Chequing Account");
		btnChequingAccount.setBounds(246, 31, 144, 29);
		contentPane.add(btnChequingAccount);
		
		JButton btnSavingAccount = new JButton("Saving Account");
		btnSavingAccount.setBounds(246, 95, 144, 29);
		contentPane.add(btnSavingAccount);
		
		JButton btnCreditAccount = new JButton("Credit Account");
		btnCreditAccount.setBounds(246, 164, 144, 29);
		contentPane.add(btnCreditAccount);
		
		JButton btnLine = new JButton(" Line of Credit Account");
		btnLine.setBounds(16, 95, 170, 29);
		contentPane.add(btnLine);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserRequestCreationOfAccount.this.dispose();
				new UserMainMenu(id, infoManager).setVisible(true);

			}
		});
		btnBack.setBounds(151, 232, 117, 29);
		contentPane.add(btnBack);
		
		JButton btnInvestmentAccount = new JButton("GIC Account");
		btnInvestmentAccount.setBounds(16, 31, 170, 29);
		contentPane.add(btnInvestmentAccount);
	}

}
