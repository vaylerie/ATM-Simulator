package bank;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class setor extends JFrame implements ActionListener {
    JButton deposit, back;
    JTextField amount;
    String accountNumber;

    public setor(String accountNumber) {
        this.accountNumber = accountNumber;
        setSize(800, 800);
        setLocation(300, 0);
        setVisible(true);
        setLayout(null);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/atm.jpg"));
        Image i2 = i1.getImage().getScaledInstance(900, 900, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0, 0, 800, 800);
        add(image);

        JLabel text = new JLabel("Masukkan jumlah uang yang ingin disetor:");
        text.setForeground(Color.WHITE);
        text.setFont(new Font("System", Font.BOLD, 16));
        text.setBounds(140, 250, 400, 20);
        image.add(text);

        amount = new JTextField();
        amount.setFont(new Font("Raleway", Font.BOLD, 22));
        amount.setBounds(140, 280, 320, 25);
        image.add(amount);

        deposit = new JButton("Setor");
        deposit.setBounds(280, 400, 150, 30);
        image.add(deposit);
        deposit.addActionListener(this);

        back = new JButton("Kembali");
        back.setBounds(280, 450, 150, 30);
        image.add(back);
        back.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == deposit) {
            String inputAmount = amount.getText();
            if (inputAmount.equals("")) {
                JOptionPane.showMessageDialog(null, "Masukkan jumlah uang untuk disetor:");
            } else {
                try {
                    int amountToDeposit = Integer.parseInt(inputAmount);
                    if (amountToDeposit <= 0) {
                        JOptionPane.showMessageDialog(null, "Jumlah uang yang dimasukkan tidak valid");
                        return;
                    }

                    conn c = new conn();
                    ResultSet rs = c.s.executeQuery("SELECT saldo FROM nasabah WHERE accountNumber='" + accountNumber + "'");

                    int currentBalance = 0;
                    if (rs.next()) {
                        currentBalance = Integer.parseInt(rs.getString("saldo"));
                    }

                    int updatedBalance = currentBalance + amountToDeposit;
                    String updateQuery = "UPDATE nasabah SET saldo='" + updatedBalance + "' WHERE accountNumber='" + accountNumber + "'";
                    c.s.executeUpdate(updateQuery);

                    JOptionPane.showMessageDialog(null, "Penyetoran sejumlah Rp. " + amountToDeposit + " berhasil!");
                    setVisible(false);
                    new transaksi(accountNumber).setVisible(true);
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        } else if (ae.getSource() == back) {
            setVisible(false);
            new transaksi(accountNumber).setVisible(true);
        }
    }

    public static void main(String args[]) {
        new setor("");
    }
}
