package ATM.GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;

public class UserTransactionRegularNext extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */


	/**
	 * Create the frame.
	 */
	public UserTransactionRegularNext() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnTransferToMy = new JButton("Transfer to my account");
		btnTransferToMy.setBounds(83, 39, 231, 29);
		contentPane.add(btnTransferToMy);
		
		JButton btnTransferToOther = new JButton("Transfer to other user account");
		btnTransferToOther.setBounds(83, 112, 231, 29);
		contentPane.add(btnTransferToOther);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBounds(83, 176, 231, 29);
		contentPane.add(btnCancel);
	}

}
