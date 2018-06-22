package pl.damiankaminski;

import pl.damiankaminski.model.User;

import java.sql.*;
import java.util.Random;

public class Boot {
    public static void main(String[] args) {
        String jdbc = "jdbc:mysql://localhost:3306/programming_school?useSSL=false";
        String username = "root";
        String password = "coderslab";
        try (Connection connection = DriverManager.getConnection(jdbc, username,password)) {
            User jan =User.findById(connection,1);
    //        System.out.println(user);
            jan.setPassword("admin"+new Random().nextInt());
            jan.save(connection);
            System.out.println(jan);

            User basia = new User("barbaaa", "barna@gma"+ new Random().nextInt()+"gmail", "test2");
            basia.save(connection);
            System.out.println(basia);

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