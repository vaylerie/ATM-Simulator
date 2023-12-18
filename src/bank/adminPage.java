package bank;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class adminPage extends JFrame {
    private JButton registerUserButton;
    private JButton userDetailsButton;

    public adminPage() {
        setTitle("Halaman Admin");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 400);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JLabel welcomeLabel = new JLabel("MRBank Admin");
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

        registerUserButton = new JButton("Daftar Nasabah");
        registerUserButton.setFont(new Font("Raleway", Font.BOLD, 24));
        registerUserButton.setPreferredSize(new Dimension(100, 50)); // Adjust button size
        registerUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Open the Admin page with pin verification
                openRegister();
            }
        });

        userDetailsButton = new JButton("Detail Nasabah");
        userDetailsButton.setFont(new Font("Raleway", Font.BOLD, 24));
        userDetailsButton.setPreferredSize(new Dimension(100, 50)); // Adjust button size
        userDetailsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Open the User login page
                openUserDetails();
            }
        });

        buttonPanel.add(registerUserButton);
        buttonPanel.add(userDetailsButton);

        panel.add(buttonPanel, BorderLayout.CENTER);

        add(panel);
        setVisible(true);
    }

    private void openRegister() {
        register registerPage = new register();
        registerPage.setVisible(true);
    }

    private void openUserDetails() {
        detailUser userDetailsPage = new detailUser();
        userDetailsPage.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new adminPage();
            }
        });
    }
}
