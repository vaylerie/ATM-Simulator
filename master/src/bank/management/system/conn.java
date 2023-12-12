package bank.management.system;
import java.sql.*;


public class conn{
    Connection c;
    Statement s;
    ResultSet rs;
    public conn(){
    try{
        c = DriverManager.getConnection("jdbc:mysql://localhost/atm_simulaator","root","");
        s=c.createStatement();
        rs = s.executeQuery("SELECT * FROM bank_account_registration");


    }catch(Exception e){
        System.out.println(e);
    }
    }

}
