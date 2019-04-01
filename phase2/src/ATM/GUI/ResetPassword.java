package ATM.GUI;

import ATM.GUI.User.UserMainMenu;
import ATM.InfoHandling.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ResetPassword extends JFrame {

	private JPanel contentPane;
	private JTextField txtReset;

	/**
	 * Launch the application.
	 */


	/**
	 * Create the frame.
	 */
	public ResetPassword(String id, InfoManager infoManager) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblResetPassword = new JLabel("Reset Password:");
		lblResetPassword.setBounds(48, 91, 133, 16);
		contentPane.add(lblResetPassword);
		
		txtReset = new JTextField();
		txtReset.setBounds(159, 86, 130, 26);
		contentPane.add(txtReset);
		txtReset.setColumns(10);
		
		JButton btnReset = new JButton("Reset");
		btnReset.setBounds(298, 86, 117, 29);
		contentPane.add(btnReset);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ResetPassword.this.dispose();
				new UserMainMenu(id, infoManager).setVisible(true);
			}
		});
		btnBack.setBounds(159, 197, 117, 29);
		contentPane.add(btnBack);
	}

}
