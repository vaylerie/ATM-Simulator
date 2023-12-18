package bank;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class tarik extends JFrame implements ActionListener {
    JButton withdraw, back;
    JTextField amount;
    String accountNumber;
    public tarik(String accountNumber) {
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

        JLabel text=new JLabel("Masukkan jumlah uang yang ingin ditarik:");
        text.setForeground(Color.WHITE);
        text.setFont(new Font("System",Font.BOLD,16));
        text.setBounds(140,250,400,20);
        image.add(text);

        amount=new JTextField();
        amount.setFont(new Font("Raleway",Font.BOLD,22));
        amount.setBounds(140,280,320,25);
        image.add(amount);

        withdraw=new JButton("Tarik");
        withdraw.setBounds(280,400,150,30);
        image.add(withdraw);
        withdraw.addActionListener(this);

        back=new JButton("Kembali");
        back.setBounds(280,450,150,30);
        image.add(back);
        back.addActionListener(this);
    }
    @Override
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == withdraw){
            String inputAmount=amount.getText();
            if(inputAmount.equals("")){
                JOptionPane.showMessageDialog(null,"Masukkan jumlah uang untuk ditarik:");
            }else{
                try{
                    int amountToWithdraw = Integer.parseInt(inputAmount);
                    int currentBalance = 0;

                    conn c=new conn();
                    ResultSet rs = c.s.executeQuery("select saldo from nasabah where accountNumber='" + accountNumber + "'");

                    if (rs.next()){
                        currentBalance = Integer.parseInt(rs.getString("saldo"));
                    }

                    if (amountToWithdraw <= currentBalance){
                        int updatedBalance = currentBalance - amountToWithdraw;
                        String updateQuery = "UPDATE nasabah SET saldo='" + updatedBalance + "' WHERE accountNumber='" + accountNumber + "'";
                        c.s.executeUpdate(updateQuery);

                        JOptionPane.showMessageDialog(null, "Penarikan sejumlah Rp. " + amountToWithdraw + " berhasil!");
                        setVisible(false);
                        new transaksi(accountNumber).setVisible(true);
                    } else {
                        JOptionPane.showMessageDialog(null, "Saldo tidak mencukupi untuk penarikan sejumlah tersebut");
                    }
                }catch(Exception e){
                    System.out.println(e);
                }

            }
        }else if(ae.getSource() == back){
            setVisible(false);
            new transaksi(accountNumber).setVisible(true);
        }
    }


    public static void main(String args[]){
        new setor("");
    }
}
