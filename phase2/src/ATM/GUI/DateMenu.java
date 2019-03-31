package ATM.GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ATM.InfoHandling.InfoManager;
import ATM.BankSystem.Time;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.ActionEvent;

public class DateMenu extends JFrame {

	private JPanel contentPane;
	private JTextField txtYear;
	private JTextField txtMonth;
	private JTextField txtDay;

	/**
	 * Create the frame.
	 */
	public DateMenu() {
		InfoManager infoManager = new InfoManager();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtYear = new JTextField();
		txtYear.addKeyListener(new KeyAdapter() {
			public void keyReleased(java.awt.event.KeyEvent evt) {
	            try {
	                long number = Long.parseLong(txtYear.getText());
	            } catch (Exception e) {
	                JOptionPane.showMessageDialog(rootPane, "Only Numbers Allowed");
	                txtYear.setText("");
	            }
	        }
	
		});

		txtYear.setBounds(99, 58, 130, 26);
		contentPane.add(txtYear);
		txtYear.setColumns(10);
		
		JLabel lblLeaveEmptyTo = new JLabel("Leave empty to use system date");
		lblLeaveEmptyTo.setBounds(33, 6, 305, 16);
		contentPane.add(lblLeaveEmptyTo);
		
		txtMonth = new JTextField();
		txtMonth.addKeyListener(new KeyAdapter() {
			public void keyReleased(java.awt.event.KeyEvent evt) {
	            try {
	                long number = Long.parseLong(txtMonth.getText());
	            } catch (Exception e) {
	                JOptionPane.showMessageDialog(rootPane, "Only Numbers Allowed");
	                txtMonth.setText("");
	            }
	        }
	
		});

		txtMonth.setColumns(10);
		txtMonth.setBounds(99, 107, 130, 26);
		contentPane.add(txtMonth);
		
		txtDay = new JTextField();
		txtDay.addKeyListener(new KeyAdapter() {
			public void keyReleased(java.awt.event.KeyEvent evt) {
	            try {
	                long number = Long.parseLong(txtDay.getText());
	            } catch (Exception e) {
	                JOptionPane.showMessageDialog(rootPane, "Only Numbers Allowed");
	                txtDay.setText("");
	            }
	        }
	
		});
		txtDay.setColumns(10);
		txtDay.setBounds(99, 156, 130, 26);
		contentPane.add(txtDay);
		
		JLabel lblYear = new JLabel("Year:");
		lblYear.setBounds(26, 63, 61, 16);
		contentPane.add(lblYear);
		
		JLabel lblMonth = new JLabel("Month:");
		lblMonth.setBounds(26, 112, 61, 16);
		contentPane.add(lblMonth);
		
		JLabel lblDay = new JLabel("Day:");
		lblDay.setBounds(26, 161, 61, 16);
		contentPane.add(lblDay);
		
		JButton btnNext = new JButton("Next");
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String year = txtYear.getText();
				String month = txtMonth.getText();
				String day = txtDay.getText();
				int yearint = Integer.valueOf(year);
				int monthint = Integer.valueOf(month);
				int dayint = Integer.valueOf(day);


			}
		});
		btnNext.setBounds(295, 220, 117, 29);
		contentPane.add(btnNext);
	}
}
