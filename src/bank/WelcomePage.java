package bank;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WelcomePage extends JFrame {
    private JButton adminButton;
    private JButton userButton;

    public WelcomePage() {
        setTitle("ATM Simulator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 400);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JLabel welcomeLabel = new JLabel("Selamat Datang di MRBank");
        welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Raleway", Font.BOLD,38));
        panel.add(welcomeLabel, BorderLayout.NORTH);

        JPanel paddingPanelLeft = new JPanel();
        paddingPanelLeft.setPreferredSize(new Dimension(100, 1)); // Adjust left padding size
        panel.add(paddingPanelLeft, BorderLayout.WEST);

        JPanel paddingPanelRight = new JPanel();
        paddingPanelRight.setPreferredSize(new Dimension(100, 1)); // Adjust right padding size
        panel.add(paddingPanelRight, BorderLayout.EAST);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 2, 50, 100)); // Adding some spacing between buttons

        adminButton = new JButton("Admin");
        adminButton.setFont(new Font("Raleway", Font.BOLD, 24));
        adminButton.setPreferredSize(new Dimension(100, 50)); // Adjust button size
        adminButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Open the Admin page with pin verification
                openAdminPage();
            }
        });

        userButton = new JButton("Nasabah");
        userButton.setFont(new Font("Raleway", Font.BOLD, 24));
        userButton.setPreferredSize(new Dimension(100, 50)); // Adjust button size
        userButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Open the User login page
                openUserLoginPage();
            }
        });

        buttonPanel.add(adminButton);
        buttonPanel.add(userButton);

        panel.add(buttonPanel, BorderLayout.CENTER);

        add(panel);
        setVisible(true);
    }

    private void openAdminPage() {
        String pin = JOptionPane.showInputDialog(null, "Masukkan PIN untuk mengakses halaman admin:");
        if (pin != null && pin.equals("123456")) {
            adminPage adminPage = new adminPage();
            adminPage.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null, "PIN salah. Akses ditolak!");
        }
    }

    private void openUserLoginPage() {
        login loginPage = new login();
        loginPage.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new WelcomePage();
            }
        });
    }
}