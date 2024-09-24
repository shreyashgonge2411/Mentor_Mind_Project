import java.sql.*;

public class UserDAO {
    private Connection connection;

    public UserDAO() {
        try {
            // Load the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Establish connection
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/anp_7699", "root", "Shreyash@2411");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    // Register user in the database
    public boolean registerUser(User user) {
        String query = "INSERT INTO userdetails (name, email, password, phone) VALUES (?, ?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, user.getName());
            pstmt.setString(2, user.getEmail());
            pstmt.setString(3, user.getPassword());
            pstmt.setString(4, user.getPhone());
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Log in user by matching email and password
    public User loginUser(String email, String password) {
        String query = "SELECT * FROM userdetails WHERE email = ? AND password = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, email);
            pstmt.setString(2, password);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                // Create and return the User object with all the retrieved information
                return new User(
                    rs.getString("name"),
                    rs.getString("email"),
                    rs.getString("password"),
                    rs.getString("phone"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Update user details in the database
    public boolean updateUser(User user) {
        String query = "UPDATE userdetails SET name = ?, email = ?, password = ?, phone = ? WHERE email = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, user.getName());
            pstmt.setString(2, user.getEmail());
            pstmt.setString(3, user.getPassword());
            pstmt.setString(4, user.getPhone());
            pstmt.setString(5, user.getEmail()); // Use email to find the correct user
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Closing the connection
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
