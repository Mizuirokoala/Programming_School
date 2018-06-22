package pl.damiankaminski;

import pl.damiankaminski.model.User;

import java.sql.*;

public class Boot {
    public static void main(String[] args) {
        String jdbc = "jdbc:mysql://localhost:3306/programming_school?useSSL=false";
        String username = "root";
        String password = "coderslab";
        try (Connection connection = DriverManager.getConnection(jdbc, username,password)) {
            User user =User.findById(connection,1);
            System.out.println(user);



            //            String query = "SELECT 'Hello, Database'";
//            PreparedStatement sql = connection.prepareStatement(query);
//            ResultSet rs = sql.executeQuery();
//            while (rs.next()) {
//                String hello = rs.getString(1);
//                System.out.println(hello);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}