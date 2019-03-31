package ATM.GUI.User;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JScrollBar;
import javax.swing.JButton;

public class UserSummary extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */


	/**
	 * Create the frame.
	 */
	public UserSummary() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollBar scrollBar = new JScrollBar();
		scrollBar.setBounds(341, 6, 15, 200);
		contentPane.add(scrollBar);
		
		JTextArea txtSummary = new JTextArea();
		txtSummary.setEditable(false);
		txtSummary.setBounds(79, 6, 277, 200);
		contentPane.add(txtSummary);
		
		JButton btnBack = new JButton("Back");
		btnBack.setBounds(148, 229, 117, 29);
		contentPane.add(btnBack);
	}
}
