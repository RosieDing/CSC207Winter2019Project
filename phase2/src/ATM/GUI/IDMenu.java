package ATM.GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import ATM.AccountTypeChecker.*;
import ATM.Accounts.*;
import ATM.BankIdentities.*;
import ATM.InfoHandling.InfoManager;
import ATM.Machine.Money;
import ATM.BankSystem.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class IDMenu extends JFrame {

	private JPanel contentPane;
	private JTextField txtID;
    private static InfoManager infoManager = InfoManager.getInfoManager();
    private static String ID;


	/**
	 * Launch the application.
	 */


	public void identityLog(String id) {

            if (infoManager.getInfoStorer().getBankManagerMap().containsKey(id)) {
            	IDMenu.this.dispose();
            	new ManagerMenu(id).setVisible(true);
            	
            } else if (infoManager.getInfoStorer().getUserMap().containsKey(id)) {
            	IDMenu.this.dispose();
            	new UserMenu(id).setVisible(true);
            } else {
            	JOptionPane.showMessageDialog(null, "ID not found. Please enter again");
            }
        }
    

	/**
	 * Create the frame.
	 */
	public IDMenu() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblEnterID = new JLabel("Please enter your ID");
		lblEnterID.setBounds(5, 5, 440, 16);
		lblEnterID.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblEnterID);
		
		JLabel lblID = new JLabel("ID:");
		lblID.setBounds(117, 79, 61, 16);
		contentPane.add(lblID);
		
		txtID = new JTextField();
		txtID.setBounds(145, 74, 130, 26);
		contentPane.add(txtID);
		txtID.setColumns(10);
		
		JButton btnNext = new JButton("Next");
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		        if (infoManager.getBankManagerNum() == 0) {
		            BankManager defaultManager = new BankManager("1234");
		            //infoManager.add(defaultManager);
		        }
		        ID = txtID.getText();
		        identityLog(ID);
		        
			}
		});
		btnNext.setBounds(287, 74, 117, 29);
		contentPane.add(btnNext);
	}



	public static InfoManager getInfoManager() {
		return infoManager;
	}
	public static String getID() {
		return ID;
	}

}