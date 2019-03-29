package ATM.GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JButton;

public class UserNetTotal extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserNetTotal frame = new UserNetTotal();
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
	public UserNetTotal() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNetTotal = new JLabel("");
		lblNetTotal.setHorizontalAlignment(SwingConstants.CENTER);
		lblNetTotal.setBounds(25, 63, 382, 16);
		contentPane.add(lblNetTotal);
		
		JLabel lblTitle = new JLabel("Net Total:");
		lblTitle.setBounds(182, 6, 83, 16);
		contentPane.add(lblTitle);
		
		JButton btnBack = new JButton("Back");
		btnBack.setBounds(163, 212, 117, 29);
		contentPane.add(btnBack);
	}

}
