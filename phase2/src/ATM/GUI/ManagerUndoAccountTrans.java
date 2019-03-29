package ATM.GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

public class ManagerUndoAccountTrans extends JFrame {

	private JPanel contentPane;
	private JTextField txtID;
	private JTextField txtTrans;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ManagerUndoAccountTrans frame = new ManagerUndoAccountTrans();
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
	public ManagerUndoAccountTrans() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblUndo = new JLabel("Undo Account Transaction");
		lblUndo.setBounds(131, 17, 260, 16);
		contentPane.add(lblUndo);
		
		JLabel lblEnterID = new JLabel("Please enter an account number:");
		lblEnterID.setBounds(89, 63, 238, 16);
		contentPane.add(lblEnterID);
		
		txtID = new JTextField();
		txtID.setBounds(131, 88, 130, 26);
		contentPane.add(txtID);
		txtID.setColumns(10);
		
		JButton btnUndo = new JButton("Undo");
		btnUndo.setBounds(274, 214, 117, 29);
		contentPane.add(btnUndo);
		
		JLabel lblNumTrans = new JLabel("Please enter number of transactions needed to undo:");
		lblNumTrans.setBounds(89, 126, 352, 16);
		contentPane.add(lblNumTrans);
		
		txtTrans = new JTextField();
		txtTrans.setColumns(10);
		txtTrans.setBounds(131, 164, 130, 26);
		contentPane.add(txtTrans);
		
		JButton btnBack = new JButton("Back");
		btnBack.setBounds(6, 214, 117, 29);
		contentPane.add(btnBack);
	}

}
