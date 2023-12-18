package bank;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class gantiPin extends JFrame implements ActionListener {
    JTextField pin,repin;
    JButton change,back;
    String accountNumber;
    public gantiPin(String accountNumber) {
        this.accountNumber=accountNumber;
        setSize(800,800);
        setLocation(300,0);
        setLayout(null);
        setVisible(true);

        ImageIcon i1=new ImageIcon(ClassLoader.getSystemResource("icons/atm.jpg"));
        Image i2=i1.getImage().getScaledInstance(800,800,Image.SCALE_DEFAULT);
        ImageIcon i3=new ImageIcon(i2);
        JLabel image=new JLabel(i3);
        image.setBounds(0,0,800,800);
        add(image);

        JLabel text=new JLabel("Ganti PIN Anda");
        text.setForeground(Color.WHITE);
        text.setFont(new Font("System",Font.BOLD,16));
        text.setBounds(210,280,500,35);
        image.add(text);

        JLabel pintext=new JLabel("PIN Baru: ");
        pintext.setForeground(Color.WHITE);
        pintext.setFont(new Font("System",Font.BOLD,16));
        pintext.setBounds(165,320,180,25);
        image.add(pintext);

        pin=new JTextField();
        pin.setFont(new Font("Raleway",Font.BOLD,25));
        pin.setBounds(320,320,100,25);
        image.add(pin);

        JLabel repintext=new JLabel("Masukkan Kembali: ");
        repintext.setForeground(Color.WHITE);
        repintext.setFont(new Font("System",Font.BOLD,16));
        repintext.setBounds(165,360,180,25);
        image.add(repintext);

        repin=new JTextField();
        repin.setFont(new Font("Raleway",Font.BOLD,25));
        repin.setBounds(320,360,100,25);
        image.add(repin);

        change=new JButton("Ganti");
        change.setBounds(300,400,150,30);
        image.add(change);
        change.addActionListener(this);

        back=new JButton("Kembali");
        back.setBounds(300,440,150,30);
        image.add(back);
        back.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == change) {
            try {
                String npin = pin.getText();
                String nrepin = repin.getText();
                if (!npin.equals(nrepin)) {
                    JOptionPane.showMessageDialog(null, "PIN Yang Dimasukkan Tidak Sesuai");
                    return;
                }
                if (npin.equals("")) {
                    JOptionPane.showMessageDialog(null, "Masukkan PIN");
                    return;
                }
                if (nrepin.equals("")) {
                    JOptionPane.showMessageDialog(null, "Masukkan Kembali PIN");
                    return;
                }

                conn c = new conn();
                String query1 = "update nasabah set pin= '" + nrepin + "' where accountNumber='" + accountNumber + "'";
                String query3 = "update nasabah set pin= '" + nrepin + "' where accountNumber='" + accountNumber + "'";
                String query2 = "update nasabah set pin= '" + nrepin + "' where accountNumber='" + accountNumber + "'";

                c.s.executeUpdate(query1);
                c.s.executeUpdate(query2);
                c.s.executeUpdate(query3);
                JOptionPane.showMessageDialog(null, " PIN Berhasil Diperbarui!");

                setVisible(false);
                new transaksi(accountNumber).setVisible(true);
            } catch (Exception e) {
                System.out.println(e);
            }
        } else {
            setVisible(false);
            new transaksi(accountNumber).setVisible(true);
        }

    }
    public static void main(String args[]){
        new gantiPin("").setVisible(true);
    }
}
