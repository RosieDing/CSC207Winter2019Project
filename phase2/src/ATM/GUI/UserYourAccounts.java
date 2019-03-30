package ATM.GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import javax.swing.JButton;

public class UserYourAccounts extends JFrame {

	private JPanel contentPane;



	/**
	 * Create the frame.
	 */
	public UserYourAccounts() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(62, 49, 166, 27);
		contentPane.add(comboBox);
		
		JButton btnNext = new JButton("Next");
		btnNext.setBounds(235, 48, 117, 29);
		contentPane.add(btnNext);
		
		JButton btnBack = new JButton("Back");
		btnBack.setBounds(151, 202, 117, 29);
		contentPane.add(btnBack);
	}

}
