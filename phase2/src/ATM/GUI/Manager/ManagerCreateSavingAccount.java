package ATM.GUI.Manager;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import ATM.Accounts.Plans.GICPlans.AnnualInterest;
import ATM.Accounts.Plans.GICPlans.FiveYearInterest;
import ATM.Accounts.Plans.GICPlans.GICPlan;
import ATM.Accounts.Plans.GICPlans.TwoYearInterest;
import ATM.Accounts.Plans.MonthlySavingPlans.*;
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

public class ManagerCreateSavingAccount extends JFrame {

	private JPanel contentPane;
	private JTextField txtID;
	private JTextField txtJoint;

	/**
	 * Create the frame.
	 */
	public ManagerCreateSavingAccount(String id, InfoManager infoManager) {

		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblID = new JLabel("Please enter user ID:");
		lblID.setBounds(37, 11, 144, 16);
		contentPane.add(lblID);
		
		txtID = new JTextField();
		txtID.setColumns(10);
		txtID.setBounds(174, 6, 130, 26);
		contentPane.add(txtID);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ManagerCreateSavingAccount.this.dispose();
				new ManagerCreateAccount(id, infoManager).setVisible(true);
			}
		});
		btnBack.setBounds(37, 217, 117, 29);
		contentPane.add(btnBack);
		
		
		
		txtJoint = new JTextField();
		txtJoint.setColumns(10);
		txtJoint.setBounds(312, 6, 130, 26);
		contentPane.add(txtJoint);
		
		JRadioButton rdbtnCAD = new JRadioButton("CAD");
		rdbtnCAD.setSelected(true);
		rdbtnCAD.setBounds(0, 72, 70, 23);
		contentPane.add(rdbtnCAD);
		
		JLabel label = new JLabel("Choose Currency Type:");
		label.setBounds(6, 44, 188, 16);
		contentPane.add(label);
		
		JRadioButton rdbtnUSD = new JRadioButton("USD");
		rdbtnUSD.setBounds(82, 72, 70, 23);
		contentPane.add(rdbtnUSD);
		
		JRadioButton rdbtnCNY = new JRadioButton("CNY");
		rdbtnCNY.setBounds(157, 71, 70, 23);
		contentPane.add(rdbtnCNY);
		
		JRadioButton rdbtnEUR = new JRadioButton("EUR");
		rdbtnEUR.setBounds(221, 72, 70, 23);
		contentPane.add(rdbtnEUR);
		
		JRadioButton rdbtnGBP = new JRadioButton("GBP");
		rdbtnGBP.setBounds(303, 72, 70, 23);
		contentPane.add(rdbtnGBP);
		
		JRadioButton rdbtnINR = new JRadioButton("INR");
		rdbtnINR.setBounds(380, 72, 70, 23);
		contentPane.add(rdbtnINR);
		
		ButtonGroup group = new ButtonGroup();
		group.add(rdbtnCAD);
		group.add(rdbtnUSD);
		group.add(rdbtnCNY);
		group.add(rdbtnEUR);
		group.add(rdbtnGBP);
		group.add(rdbtnINR);
		
		JRadioButton rdbtnMonthlyPremiumPlan = new JRadioButton("Monthly Premium Plan");
		rdbtnMonthlyPremiumPlan.setBounds(221, 135, 172, 23);
		contentPane.add(rdbtnMonthlyPremiumPlan);
		
		JRadioButton rdbtnMonthlyPlan = new JRadioButton("Monthly Plan");
		rdbtnMonthlyPlan.setSelected(true);
		rdbtnMonthlyPlan.setBounds(0, 135, 130, 23);
		contentPane.add(rdbtnMonthlyPlan);
		
		JLabel lblChoosePlan = new JLabel("Choose Plan:");
		lblChoosePlan.setBounds(6, 107, 188, 16);
		contentPane.add(lblChoosePlan);
		
		ButtonGroup group2 = new ButtonGroup();
		group2.add(rdbtnMonthlyPremiumPlan);
		group2.add(rdbtnMonthlyPlan);
		
		JButton btnCreateSavingAccount = new JButton("Create Saving Account");
		btnCreateSavingAccount.addActionListener(new ActionListener() {
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
				
				SavingPlan plan = new MonthlyInterest();	
				if (rdbtnMonthlyPlan.isSelected()) {
					plan = new MonthlyInterest();
				}else if (rdbtnMonthlyPremiumPlan.isSelected()) {
					plan = new MonthlyPremiumInterest();
				}
				String ID = txtID.getText();
				InfoStorer infoStorer = infoManager.getInfoStorer();
				User user = infoStorer.getUserMap().get(ID);
				AccountCreator creater = new AccountCreator(user, infoStorer, type);
				creater.createNewSavingAccount(plan);
			}
		});
		btnCreateSavingAccount.setBounds(232, 217, 191, 29);
		contentPane.add(btnCreateSavingAccount);
	}

}