package bank;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class cekSaldo extends JFrame implements ActionListener {
    JButton back;
    String accountNumber;
    public cekSaldo(String accountNumber) {
        this.accountNumber=accountNumber;
        ImageIcon i1=new ImageIcon(ClassLoader.getSystemResource("icons/atm.jpg"));
        Image i2=i1.getImage().getScaledInstance(800,800,Image.SCALE_DEFAULT);
        ImageIcon i3=new ImageIcon(i2);
        JLabel image=new JLabel(i3);
        image.setBounds(0,0,800,800);
        add(image);

        back=new JButton("Kembali");
        back.setBounds(300,420,100,30);
        image.add(back);
        back.addActionListener(this);
        int saldo=0;

        conn c=new conn();
        try{
            ResultSet rs=c.s.executeQuery("select * from nasabah where accountNumber='"+accountNumber+"'");

            if (rs.next()) {
                saldo = Integer.parseInt(rs.getString("saldo"));
            }
        }catch(Exception e){
            System.out.println(e);
        }

        JLabel text=new JLabel("Jumlah Saldo Anda Rp. "+saldo);
        text.setBounds(170,300,400,30);
        text.setForeground(Color.WHITE);
        image.add(text);

        setSize(800,800);
        setLocation(300,0);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        setVisible(false);
        new transaksi(accountNumber).setVisible(true);
    }
}
