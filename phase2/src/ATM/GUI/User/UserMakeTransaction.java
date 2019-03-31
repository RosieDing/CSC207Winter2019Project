package ATM.GUI.User;

import ATM.InfoHandling.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class UserMakeTransaction extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */


	/**
	 * Create the frame.
	 */
	public UserMakeTransaction(String id, InfoManager infoManager) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTrans = new JLabel("Transaction Menu:");
		lblTrans.setBounds(145, 6, 170, 16);
		contentPane.add(lblTrans);
		
		JButton btnRegularTransaction = new JButton("Regular Transaction");
		btnRegularTransaction.setBounds(28, 55, 157, 29);
		contentPane.add(btnRegularTransaction);
		
		JButton btnDeposit = new JButton("Deposit");
		btnDeposit.setBounds(246, 55, 144, 29);
		contentPane.add(btnDeposit);
		
		JButton btnWithdraw = new JButton("Withdraw");
		btnWithdraw.setBounds(28, 141, 144, 29);
		contentPane.add(btnWithdraw);
		
		JButton btnPayBill = new JButton("Pay Bill");
		btnPayBill.setBounds(246, 141, 144, 29);
		contentPane.add(btnPayBill);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserMakeTransaction.this.dispose();
				new UserMainMenu(id, infoManager).setVisible(true);

			}
		});
		btnBack.setBounds(144, 222, 144, 29);
		contentPane.add(btnBack);
	}

}
