package bank;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class detailUser extends JFrame {
    private JTable table;
    private JScrollPane scrollPane;
    private JButton backButton;

    public detailUser() {
        setTitle("Detail Nasabah");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 400);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        // Create a table model
        DefaultTableModel model = new DefaultTableModel();
        table = new JTable(model);
        scrollPane = new JScrollPane(table);
        panel.add(scrollPane, BorderLayout.CENTER);

        backButton = new JButton("Kembali");
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new adminPage().setVisible(true);
            }
        });

        panel.add(backButton, BorderLayout.SOUTH);

        try {
            conn c = new conn();
            Connection dbConnection = c.s.getConnection();

            // Fetch data from database
            Statement s = dbConnection.createStatement();
            ResultSet rs = s.executeQuery("SELECT * FROM nasabah");

            model.addColumn("ID");
            model.addColumn("Nama");
            model.addColumn("Nomor Rekening");
            model.addColumn("Saldo");
            model.addColumn("PIN");

            while (rs.next()) {
                String id = rs.getString("id");
                String nama = rs.getString("name");
                String nomorRekening = rs.getString("accountNumber");
                String saldo = rs.getString("saldo");
                String pin = rs.getString("pin");

                model.addRow(new String[]{id, nama, nomorRekening, saldo, pin});
            }

            rs.close();
            s.close();
            dbConnection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        add(panel);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new detailUser();
            }
        });
    }
}