package bank;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class transfer extends JFrame implements ActionListener {
    JButton transferButton, back;
    JTextField transferToAccount, transferAmount;
    String accountNumber;
    public transfer(String accountNumber) {
        this.accountNumber=accountNumber;
        setSize(800,800);
        setLocation(300,0);
        setVisible(true);
        setLayout(null);

        ImageIcon i1=new ImageIcon(ClassLoader.getSystemResource("icons/atm.jpg"));
        Image i2=i1.getImage().getScaledInstance(900,900,Image.SCALE_DEFAULT);
        ImageIcon i3=new ImageIcon(i2);
        JLabel image=new JLabel(i3);
        image.setBounds(0,0,800,800);
        add(image);

        JLabel text=new JLabel("Transfer");
        text.setForeground(Color.WHITE);
        text.setFont(new Font("System",Font.BOLD,16));
        text.setBounds(210,280,500,35);
        image.add(text);

        JLabel accounttext=new JLabel("Rekening tujuan:");
        accounttext.setForeground(Color.WHITE);
        accounttext.setFont(new Font("System",Font.BOLD,16));
        accounttext.setBounds(165,320,180,25);
        image.add(accounttext);

        transferToAccount=new JTextField();
        transferToAccount.setFont(new Font("Raleway",Font.BOLD,25));
        transferToAccount.setBounds(320,320,100,25);
        image.add(transferToAccount);

        JLabel amounttext=new JLabel("Jumlah uang:");
        amounttext.setForeground(Color.WHITE);
        amounttext.setFont(new Font("System",Font.BOLD,16));
        amounttext.setBounds(165,360,180,25);
        image.add(amounttext);

        transferAmount=new JTextField();
        transferAmount.setFont(new Font("Raleway",Font.BOLD,25));
        transferAmount.setBounds(320,360,100,25);
        image.add(transferAmount);

        transferButton=new JButton("Transfer");
        transferButton.setBounds(300,400,150,30);
        image.add(transferButton);
        transferButton.addActionListener(this);

        back=new JButton("Kembali");
        back.setBounds(300,440,150,30);
        image.add(back);
        back.addActionListener(this);
    }


    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == transferButton){
            String toAccount = transferToAccount.getText();
            String amount = transferAmount.getText();

            if (toAccount.isEmpty() || amount.isEmpty()){
                JOptionPane.showMessageDialog(null, "Mohon lengkapi semua kolom.");
            }

            try {
                int transferAmount = Integer.parseInt(amount);

                conn c = new conn();
                ResultSet rs = c.s.executeQuery("select saldo from nasabah where accountNumber='" + accountNumber + "'");
                int senderBalance = 0;

                if (rs.next()){
                    senderBalance = Integer.parseInt(rs.getString("saldo"));
                }

                if (senderBalance < transferAmount){
                    JOptionPane.showMessageDialog(null, "Saldo tidak mencukupi.");
                }

                ResultSet rs2 = c.s.executeQuery("SELECT saldo FROM nasabah WHERE accountNumber='" + toAccount + "'");
                int receiverBalance = 0;

                if (rs2.next()) {
                    receiverBalance = Integer.parseInt(rs2.getString("saldo"));
                }

                int updatedSenderBalance = senderBalance - transferAmount;
                int updatedReceiverBalance = receiverBalance + transferAmount;

                String updateSenderQuery = "UPDATE nasabah SET saldo='" + updatedSenderBalance + "' WHERE accountNumber='" + accountNumber + "'";
                String updateReceiverQuery = "UPDATE nasabah SET saldo='" + updatedReceiverBalance + "' WHERE accountNumber='" + toAccount + "'";

                c.s.executeUpdate(updateSenderQuery);
                c.s.executeUpdate(updateReceiverQuery);

                JOptionPane.showMessageDialog(null, "Transfer sejumlah Rp. " + transferAmount + " berhasil!");
                setVisible(false);
                new transaksi(accountNumber).setVisible(true);
            } catch (Exception ex){
                System.out.println(ex);
            }
        } else if (ae.getSource() == back) {
            setVisible(false);
            new transaksi(accountNumber).setVisible(true);
        }
    }
    public static void main(String[] args){
        new transfer("");
    }
}
