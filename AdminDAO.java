import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdminDAO {
    private Connection connection;

    public AdminDAO() {
        try {
            // Load the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Establish connection
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/anp_7699", "root", "Shreyash@2411");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    // Admin login method
    public boolean adminLogin(String email, String password) {
        String query = "SELECT * FROM admins WHERE email = ? AND password = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, email);
            pstmt.setString(2, password);
            ResultSet rs = pstmt.executeQuery();
            return rs.next(); // Returns true if an admin with the provided credentials exists
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Method to add a new admin (if needed)
    public boolean addAdmin(Admin admin) {
        String query = "INSERT INTO admins (name, email, password) VALUES (?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, admin.getName());
            pstmt.setString(2, admin.getEmail());
            pstmt.setString(3, admin.getPassword());
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Method to update admin details
    public boolean updateAdmin(Admin admin) {
        String query = "UPDATE admins SET name = ?, email = ?, password = ? WHERE email = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, admin.getName());
            pstmt.setString(2, admin.getEmail());
            pstmt.setString(3, admin.getPassword());
            pstmt.setString(4, admin.getEmail()); // Assuming we're using email to identify the admin
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<User> getUserDetails() {
        List<User> users = new ArrayList<>();
        try {
            //Connection connection = Database.getConnection(); // Assuming you have a Database class to manage connections
            String query = "SELECT * FROM userdetails"; // Replace with your user table name
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                // Assuming your User class has a constructor that accepts these parameters
                User user = new User(
                    resultSet.getString("name"),
                    resultSet.getString("email"),
                    resultSet.getString("password"),
                    resultSet.getString("phone")
                );
                users.add(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return users;
    }

    // Method to close the connection
    public void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
