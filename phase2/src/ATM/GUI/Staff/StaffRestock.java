package ATM.GUI.Staff;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

public class StaffRestock extends JFrame {

    private JPanel contentPane;
    private JTextField txtFive;
    private JTextField txtTen;
    private JTextField txtTwenty;
    private JTextField txtFifty;

    /**
     * Create the frame.
     */
    public StaffRestock() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblRestock = new JLabel("Restock");
        lblRestock.setBounds(174, 6, 61, 16);
        contentPane.add(lblRestock);

        txtFive = new JTextField();
        txtFive.setColumns(10);
        txtFive.setBounds(201, 45, 130, 26);
        contentPane.add(txtFive);

        JLabel lblFive = new JLabel("Number of Five dollars:");
        lblFive.setBounds(42, 50, 171, 16);
        contentPane.add(lblFive);

        JLabel lblTen = new JLabel("Number of Ten dollars:");
        lblTen.setBounds(42, 92, 171, 16);
        contentPane.add(lblTen);

        txtTen = new JTextField();
        txtTen.setColumns(10);
        txtTen.setBounds(201, 87, 130, 26);
        contentPane.add(txtTen);

        txtTwenty = new JTextField();
        txtTwenty.setColumns(10);
        txtTwenty.setBounds(225, 133, 130, 26);
        contentPane.add(txtTwenty);

        txtFifty = new JTextField();
        txtFifty.setColumns(10);
        txtFifty.setBounds(201, 181, 130, 26);
        contentPane.add(txtFifty);

        JLabel lblFifty = new JLabel("Number of Fifty dollars:");
        lblFifty.setBounds(42, 186, 171, 16);
        contentPane.add(lblFifty);

        JLabel lblTwenty = new JLabel("Number of Twenty dollars:");
        lblTwenty.setBounds(42, 138, 171, 16);
        contentPane.add(lblTwenty);

        JButton btnBack = new JButton("Back");
        btnBack.setBounds(6, 219, 117, 29);
        contentPane.add(btnBack);

        JButton btnRestock = new JButton("Restock");
        btnRestock.setBounds(327, 219, 117, 29);
        contentPane.add(btnRestock);
    }

}
