package pl.damiankaminski.model;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class User {

    private int id;
    private String username;
    private String email;
    private String password;

    private User() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;

    }

    public void setPassword(String password) {
        this.password = password;
        System.out.println("lib and hash");
        //TODO add lib and hash
    }


    public User( String username, String email, String password) {
        this.username = username;
        this.email = email;
        setPassword(password);
        //this.password=password;
    }

    public int getId() {
        return id;
    }

    public void save(Connection connection) throws SQLException {
        if (id == 0) {
            insert(connection);
        } else {
            update(connection);
        }
    }

    private void update(Connection connection) throws SQLException {
        String query="UPDATE users SET username=?,email=?,password=? WHERE id=?";
        PreparedStatement sql = connection.prepareStatement(query);

        sql.setString(1,username);
        sql.setString(2,email);
        sql.setString(3,password);
        sql.setInt(4,id);
        sql.executeUpdate();
    }

    private void insert(Connection connection) throws SQLException {

        String query="INSERT INTO users(username, email, password) VALUES (?,?,?)";
        PreparedStatement sql = connection.prepareStatement(query, new String[]{"id"});
        sql.setString(1,username);
        sql.setString(2,email);
        sql.setString(3,password);
        sql.executeUpdate();
        ResultSet rs=sql.getGeneratedKeys();
        if (rs.next()){
            id=rs.getInt(1);
        }

    }

    public static User findById(Connection connection, int id) throws SQLException {
        String query = "SELECT username,email,password FROM  users WHERE id=?";
        PreparedStatement sql = connection.prepareStatement(query);
        sql.setInt(1, id);
        ResultSet rs = sql.executeQuery();
        if (rs.next()) {
            User user = new User();
            user.id = id;
            user.username = rs.getString("username");
            user.email = rs.getString("email");
            user.password = rs.getString("password");

            return user;
        } else {
            return null;

        }
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}

