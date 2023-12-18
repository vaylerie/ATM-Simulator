package bank;
import java.sql.*;

public class conn {
    Connection c;
    Statement s;
    ResultSet rs;

    public conn(){
        try{
            c = DriverManager.getConnection("jdbc:mysql://localhost/atm","root","");
            s = c.createStatement();

            // Note: Removed the initial SELECT query from the constructor
        } catch(Exception e){
            System.out.println(e);
        }
    }

    public boolean insertUserData(String name, String pin, int accountNumber, int saldo) {
        try {
            // Inserting data into the 'nasabah' table
            String query = "INSERT INTO nasabah (name, pin, accountNumber, saldo) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = c.prepareStatement(query);
            ps.setString(1, name);
            ps.setString(2, pin);
            ps.setInt(3, accountNumber);
            ps.setInt(4, saldo);

            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.out.println("Error in insertUserData: " + e.getMessage());
            return false;
        }
    }
}
