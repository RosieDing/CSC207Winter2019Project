package ATM.GUI;

import ATM.BankIdentities.*;
import ATM.InfoHandling.InfoManager;
import ATM.InfoHandling.InfoStorer;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JOptionPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;;


public class UserMenu extends JFrame {

    private JPanel contentPane;
    private JTextField txtPassword;
    private PasswordManager passwordManager;


    /**
     * Launch the application.
     */



    /**
     * Create the frame.
     */
    public UserMenu(String id,InfoStorer infoStorer, InfoManager infoManager) {
        User user = infoManager.getUser(id);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JButton btnNext = new JButton("Next");
        btnNext.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String password = txtPassword.getText();
                passwordManager.login(password, infoStorer.getPasswordMap());
                if (passwordManager.isLogin()){
                    UserMenu.this.dispose();
                    new UserMainMenu().setVisible(true);
                }else {
                    JOptionPane.showMessageDialog(null, "Password wrong! Please enter again.");
                }
            }
        });
        btnNext.setBounds(288, 75, 117, 29);
        contentPane.add(btnNext);

        txtPassword = new JTextField();
        txtPassword.setColumns(10);
        txtPassword.setBounds(146, 75, 130, 26);
        contentPane.add(txtPassword);

        JLabel lblPassword = new JLabel("Password:");
        lblPassword.setBounds(79, 80, 73, 16);
        contentPane.add(lblPassword);

        JLabel lblEnterPassword = new JLabel("Please enter your password");
        lblEnterPassword.setHorizontalAlignment(SwingConstants.CENTER);
        lblEnterPassword.setBounds(6, 27, 440, 16);
        contentPane.add(lblEnterPassword);

        JLabel lblWelcome = new JLabel("Welcome User!");
        lblWelcome.setHorizontalAlignment(SwingConstants.CENTER);
        lblWelcome.setBounds(6, 6, 440, 16);
        contentPane.add(lblWelcome);
    }
}
