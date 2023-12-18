package bank;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class register extends JFrame {
    private JTextField nameField;
    private JPasswordField pinField;
    private JButton registerButton;
    private conn databaseConnection; // Instance of conn.java for database connection

    public register() {
        setTitle("Register New User");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 250);
        setLocationRelativeTo(null);

        // Initialize the database connection
        databaseConnection = new conn();

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 1, 10, 10));

        JLabel nameLabel = new JLabel("Nama:");
        nameField = new JTextField(20);

        JLabel pinLabel = new JLabel("PIN (6 angka):");
        pinField = new JPasswordField(6);

        registerButton = new JButton("Daftar");
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Retrieve data from fields
                String name = nameField.getText();
                String pin = String.valueOf(pinField.getPassword());

                // Validate and pass the data to the database using conn.java
                if (validateInputs(name, pin)) {
                    // Data is valid, insert into the database
                    insertIntoDatabase(name, pin);
                }
            }
        });

        panel.add(nameLabel);
        panel.add(nameField);
        panel.add(pinLabel);
        panel.add(pinField);
        panel.add(registerButton);

        add(panel);
        setVisible(true);
    }

    private boolean validateInputs(String name, String pin) {
        // Perform validation for name and pin (you can add more validations if needed)
        if (name.isEmpty() || pin.isEmpty() || pin.length() != 6 || !pin.matches("\\d+")) {
            JOptionPane.showMessageDialog(this, "Masukkan 6 angka PIN.");
            return false;
        }
        return true;
    }

    private void insertIntoDatabase(String name, String pin) {
        // Generate random 9-digit account number
        Random random = new Random();
        int accountNumber = 100000000 + random.nextInt(900000000);

        // Set saldo to 100000 as initial balance
        int saldo = 100000;

        // Insert data into the database
        boolean success = databaseConnection.insertUserData(name, pin, accountNumber, saldo);

        if (success) {
            JOptionPane.showMessageDialog(this,
                    "Berhasil menambahkan nasabah!\n\n" +
                            "Nama: " + name + "\n" +
                            "PIN: " + pin + "\n" +
                            "Nomor Rekening: " + accountNumber + "\n" +
                            "Saldo Awal: " + saldo);

            // Close the registration window after successful registration
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Gagal menambahkan nasabah. Silakan coba lagi.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new register();
            }
        });
    }
}
