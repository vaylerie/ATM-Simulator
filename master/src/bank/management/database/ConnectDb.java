package bank.management.database;

import java.sql.*;

public class ConnectDb {
    public static void main(String[] args) {

        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/atm", "root", "");
            Statement statement =  connection.createStatement();

            ResultSet resultSet = statement.executeQuery("SELECT * FROM bank_account_registration");

            while(resultSet.next()) {
                System.out.println(resultSet.getString("name"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
