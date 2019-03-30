package ATM.GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class UserYourAccountsNext extends JFrame {

	private JPanel contentPane;
	private JTextField txtBalance;
	private JTextField txtTrans;
	private JTextField txtDate;



	/**
	 * Create the frame.
	 */
	public UserYourAccountsNext() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnBack.setBounds(257, 65, 117, 29);
		contentPane.add(btnBack);
		
		JLabel lblBalance = new JLabel("Balance:");
		lblBalance.setBounds(63, 42, 78, 16);
		contentPane.add(lblBalance);
		
		JLabel lblLastTransaction = new JLabel("Last Transaction:");
		lblLastTransaction.setBounds(6, 70, 117, 16);
		contentPane.add(lblLastTransaction);
		
		JLabel lblDateOfCreation = new JLabel("Date of Creation:");
		lblDateOfCreation.setBounds(6, 98, 117, 16);
		contentPane.add(lblDateOfCreation);
		
		txtBalance = new JTextField();
		txtBalance.setEditable(false);
		txtBalance.setBounds(115, 37, 130, 26);
		contentPane.add(txtBalance);
		txtBalance.setColumns(10);
		
		txtTrans = new JTextField();
		txtTrans.setEditable(false);
		txtTrans.setColumns(10);
		txtTrans.setBounds(115, 65, 130, 26);
		contentPane.add(txtTrans);
		
		txtDate = new JTextField();
		txtDate.setEditable(false);
		txtDate.setColumns(10);
		txtDate.setBounds(115, 93, 130, 26);
		contentPane.add(txtDate);
	}

}
