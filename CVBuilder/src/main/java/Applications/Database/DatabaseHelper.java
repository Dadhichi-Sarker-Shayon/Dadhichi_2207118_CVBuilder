package Applications.Database;

import java.sql.*;

public class DatabaseHelper {
    private static final String URL = "jdbc:sqlite:cvbuilder.db";

    public static Connection connect() {
        try {
            return DriverManager.getConnection(URL);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void createTable() {
        String sql = "CREATE TABLE IF NOT EXISTS cv (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "fullname TEXT, " +
                "email TEXT, " +
                "phone TEXT, " +
                "address TEXT, " +
                "education TEXT, " +
                "skills TEXT, " +
                "workExperience TEXT, " +
                "project TEXT, " +
                "profileImagePath TEXT)";
        try (Connection conn = connect(); Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static boolean insertCV(String fullname, String email, String phone, String address,
                                   String education, String skills, String workExperience,
                                   String project, String profileImagePath) {
        String sql = "INSERT INTO cv(fullname, email, phone, address, education, skills, workExperience, project, profileImagePath) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, fullname);
            pstmt.setString(2, email);
            pstmt.setString(3, phone);
            pstmt.setString(4, address);
            pstmt.setString(5, education);
            pstmt.setString(6, skills);
            pstmt.setString(7, workExperience);
            pstmt.setString(8, project);
            pstmt.setString(9, profileImagePath);
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean updateCV(int id, String fullname, String email, String phone, String address,
                                   String education, String skills, String workExperience,
                                   String project, String profileImagePath) {
        String sql = "UPDATE cv SET fullname=?, email=?, phone=?, address=?, education=?, skills=?, workExperience=?, project=?, profileImagePath=? WHERE id=?";
        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, fullname);
            pstmt.setString(2, email);
            pstmt.setString(3, phone);
            pstmt.setString(4, address);
            pstmt.setString(5, education);
            pstmt.setString(6, skills);
            pstmt.setString(7, workExperience);
            pstmt.setString(8, project);
            pstmt.setString(9, profileImagePath);
            pstmt.setInt(10, id);
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static int insertCVAndReturnId(String fullname, String email, String phone, String address,
                                          String education, String skills, String workExperience,
                                          String project, String profileImagePath) {
        String sql = "INSERT INTO cv(fullname, email, phone, address, education, skills, workExperience, project, profileImagePath) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setString(1, fullname);
            pstmt.setString(2, email);
            pstmt.setString(3, phone);
            pstmt.setString(4, address);
            pstmt.setString(5, education);
            pstmt.setString(6, skills);
            pstmt.setString(7, workExperience);
            pstmt.setString(8, project);
            pstmt.setString(9, profileImagePath);

            int affectedRows = pstmt.executeUpdate();
            if (affectedRows == 0) return -1;

            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) return generatedKeys.getInt(1);
            }

        } catch (SQLException e) { e.printStackTrace(); }
        return -1;
    }

}
