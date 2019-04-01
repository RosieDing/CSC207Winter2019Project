package ATM.GUI.User;

import ATM.BankIdentities.AlreadyPrimaryException;
import ATM.BankIdentities.UserAccManager;
import ATM.InfoHandling.*;
import ATM.AccountTypeChecker.*;

import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class UserSetPrimaryChequing extends JFrame {

	private JPanel contentPane;


	/**
	 * Create the frame.
	 */
	public UserSetPrimaryChequing(String id, InfoManager infoManager) {
		InfoStorer infoStorer = infoManager.getInfoStorer();
		UserAccManager manager = new UserAccManager(id, infoStorer.getUserMap(), infoStorer.getStaffMap());
		ChequingChecker checker = new ChequingChecker();
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(78, 55, 166, 27);
		contentPane.add(comboBox);
		ArrayList list = manager.getTypeAccounts(checker);
		for (Object o : list) {
			comboBox.addItem(o);
		}

		JButton btnSet = new JButton("Set");
		btnSet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnSet.setBounds(251, 54, 117, 29);
		contentPane.add(btnSet);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserSetPrimaryChequing.this.dispose();
				new UserMainMenu(id, infoManager).setVisible(true);
			}
		});
		btnBack.setBounds(167, 208, 117, 29);
		contentPane.add(btnBack);
	}

}
