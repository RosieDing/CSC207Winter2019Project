package ATM.GUI.User;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import javax.swing.JButton;

public class UserSetPrimaryChequing extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */


	/**
	 * Create the frame.
	 */
	public UserSetPrimaryChequing() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(78, 55, 166, 27);
		contentPane.add(comboBox);
		
		JButton btnSet = new JButton("Set");
		btnSet.setBounds(251, 54, 117, 29);
		contentPane.add(btnSet);
		
		JButton btnBack = new JButton("Back");
		btnBack.setBounds(167, 208, 117, 29);
		contentPane.add(btnBack);
	}

}
