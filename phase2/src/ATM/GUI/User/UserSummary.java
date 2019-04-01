package ATM.GUI.User;

import ATM.InfoHandling.*;
import ATM.BankIdentities.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import javax.swing.JScrollBar;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class UserSummary extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */


	/**
	 * Create the frame.
	 */
	public UserSummary(String id, InfoManager infoManager) {
		InfoStorer infoStorer = infoManager.getInfoStorer();
		UserAccManager manager = new UserAccManager(id, infoStorer.getUserMap(), infoStorer.getStaffMap());
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollBar scrollBar = new JScrollBar();
		scrollBar.setBounds(479, 6, 15, 200);
		contentPane.add(scrollBar);
		
		JTextArea txtSummary = new JTextArea();
		txtSummary.setWrapStyleWord(true);
		txtSummary.setEditable(false);
		txtSummary.setBounds(79, 6, 800, 200);
		contentPane.add(txtSummary);
		txtSummary.setText(manager.getSummary());
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserSummary.this.dispose();
				new UserMainMenu(id, infoManager).setVisible(true);
			}
		});
		btnBack.setBounds(148, 229, 117, 29);
		contentPane.add(btnBack);
		txtSummary.setText(manager.getSummary());

	}
}
