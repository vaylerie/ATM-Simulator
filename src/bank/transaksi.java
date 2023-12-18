package bank;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class transaksi extends JFrame implements ActionListener {

    JButton setor, tarik, transfer, gantiPin, cekSaldo, exit;
    String accountNumber;

    public transaksi(String accountNumber) {
        this.accountNumber = accountNumber;
        setUndecorated(true);

        setSize(900, 900);
        setLocation(300, 0);
        setLayout(null);
        setVisible(true);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/atm.jpg"));
        Image i2 = i1.getImage().getScaledInstance(900, 900, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0, 0, 900, 900);
        add(image);

        JLabel text = new JLabel("Silakan Pilih Transaksi");
        text.setBounds(210, 300, 700, 35);
        text.setForeground(Color.WHITE);
        text.setFont(new Font("System", Font.BOLD, 16));
        image.add(text);

        setor = new JButton("Setor Tunai");
        setor.setBounds(170, 415, 150, 30);
        image.add(setor);
        setor.addActionListener(this);

        tarik = new JButton("Tarik Tunai");
        tarik.setBounds(355, 415, 150, 30);
        image.add(tarik);
        tarik.addActionListener(this);

        transfer = new JButton("Transfer");
        transfer.setBounds(170, 450, 150, 30);
        image.add(transfer);
        transfer.addActionListener(this);

        gantiPin = new JButton("Ganti PIN");
        gantiPin.setBounds(355, 450, 150, 30);
        image.add(gantiPin);
        gantiPin.addActionListener(this);

        cekSaldo = new JButton("Informasi Saldo");
        cekSaldo.setBounds(170, 485, 150, 30);
        image.add(cekSaldo);
        cekSaldo.addActionListener(this);

        exit = new JButton("Keluar");
        exit.setBounds(355, 485, 150, 30);
        image.add(exit);
        exit.addActionListener(this);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == exit) {
            System.exit(0);
        } else if (ae.getSource() == setor) {
            setVisible(false);
            new setor(accountNumber).setVisible(true);
        } else if (ae.getSource() == tarik) {
            setVisible(false);
            new tarik(accountNumber).setVisible(true);
        } else if (ae.getSource() == transfer) {
            setVisible(false);
            new transfer(accountNumber).setVisible(true);
        } else if (ae.getSource() == gantiPin) {
            setVisible(false);
            new gantiPin(accountNumber).setVisible(true);
        } else if (ae.getSource() == cekSaldo) {
            setVisible(false);
            new cekSaldo(accountNumber).setVisible(true);
        }
    }

    public static void main(String args[]) {
        new transaksi("");
    }
}
