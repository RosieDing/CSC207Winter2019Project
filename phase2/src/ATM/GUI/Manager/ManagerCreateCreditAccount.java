package ATM.GUI.Manager;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import ATM.BankIdentities.AccountCreator;
import ATM.BankIdentities.User;
import ATM.InfoHandling.InfoManager;
import ATM.InfoHandling.InfoStorer;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;

public class ManagerCreateCreditAccount extends JFrame {

	private JPanel contentPane;
	private JTextField txtID;
	private JTextField txtJoint;
	private JTextField txtLimit;


	/**
	 * Create the frame.
	 */
	public ManagerCreateCreditAccount(String id, InfoManager infoManager) {

		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblID = new JLabel("Please enter user ID:");
		lblID.setBounds(23, 11, 144, 16);
		contentPane.add(lblID);
		
		txtID = new JTextField();
		txtID.setColumns(10);
		txtID.setBounds(160, 6, 130, 26);
		contentPane.add(txtID);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ManagerCreateCreditAccount.this.dispose();
				new ManagerCreateAccount(id, infoManager).setVisible(true);
			}
		});
		btnBack.setBounds(50, 190, 117, 29);
		contentPane.add(btnBack);
		
		
		
		txtJoint = new JTextField();
		txtJoint.setColumns(10);
		txtJoint.setBounds(302, 6, 130, 26);
		contentPane.add(txtJoint);
		
		JLabel lblLimit = new JLabel("Limit:");
		lblLimit.setBounds(6, 131, 61, 16);
		contentPane.add(lblLimit);
		
		txtLimit = new JTextField();
		txtLimit.setColumns(10);
		txtLimit.setBounds(50, 126, 130, 26);
		contentPane.add(txtLimit);
		
		JLabel lblCurrency = new JLabel("Choose Currency Type:");
		lblCurrency.setBounds(6, 37, 188, 16);
		contentPane.add(lblCurrency);
		
		JRadioButton rdbtnCAD = new JRadioButton("CAD");
		rdbtnCAD.setSelected(true);
		rdbtnCAD.setBounds(0, 65, 70, 23);
		contentPane.add(rdbtnCAD);
		
		JRadioButton rdbtnUSD = new JRadioButton("USD");
		rdbtnUSD.setBounds(82, 65, 70, 23);
		contentPane.add(rdbtnUSD);
		
		JRadioButton rdbtnCNY = new JRadioButton("CNY");
		rdbtnCNY.setBounds(157, 64, 70, 23);
		contentPane.add(rdbtnCNY);
		
		JRadioButton rdbtnEUR = new JRadioButton("EUR");
		rdbtnEUR.setBounds(221, 65, 70, 23);
		contentPane.add(rdbtnEUR);
		
		JRadioButton rdbtnGBP = new JRadioButton("GBP");
		rdbtnGBP.setBounds(303, 65, 70, 23);
		contentPane.add(rdbtnGBP);
		
		JRadioButton rdbtnINR = new JRadioButton("INR");
		rdbtnINR.setBounds(380, 65, 70, 23);
		contentPane.add(rdbtnINR);
		
		ButtonGroup group = new ButtonGroup();
		group.add(rdbtnCAD);
		group.add(rdbtnUSD);
		group.add(rdbtnCNY);
		group.add(rdbtnEUR);
		group.add(rdbtnGBP);
		group.add(rdbtnINR);
		
		JButton btnCreateCreditAccount = new JButton("Create Credit Account");
		btnCreateCreditAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					String type = rdbtnCAD.getText();
					if (rdbtnCAD.isSelected()) {
						type = rdbtnCAD.getText();
					}else if (rdbtnUSD.isSelected()) {
						type = rdbtnUSD.getText();
					}else if (rdbtnCNY.isSelected()) {
						type = rdbtnCNY.getText();
					}else if (rdbtnEUR.isSelected()) {
						type = rdbtnEUR.getText();
					}else if (rdbtnGBP.isSelected()) {
						type = rdbtnGBP.getText();
					}else if (rdbtnINR.isSelected()) {
						type = rdbtnINR.getText();
					}
				String ID = txtID.getText();
				String limit = txtLimit.getText();
				InfoStorer infoStorer = infoManager.getInfoStorer();
				int limitint = Integer.valueOf(limit);
				User user = infoStorer.getUserMap().get(ID);
				AccountCreator creater = new AccountCreator(user, infoStorer, type);
				creater.createNewCreditAccount(limitint);
			}
		});
		btnCreateCreditAccount.setBounds(221, 190, 191, 29);
		contentPane.add(btnCreateCreditAccount);
	}

}