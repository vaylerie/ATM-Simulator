package bank.management.system;
import java.sql.*;


public class conn{
    Connection c;
    Statement s;
    public conn(){
    try{
        c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/atm_simulator","root","");
        s=c.createStatement();
    }catch(Exception e){
        System.out.println(e);
    }
    }
    
}
