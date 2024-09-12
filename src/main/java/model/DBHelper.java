package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.json.JSONObject;
import java.nio.file.Paths;


public class DBHelper {

    static {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("SQLite JDBC driver not found");
        }
    }

    public static Connection connect() throws SQLException {
        String dbPath = Paths.get("database.db").toAbsolutePath().toString();
        String url = "jdbc:sqlite:" + dbPath;
        return DriverManager.getConnection(url);
    }

    public static JSONObject searchWord(String word) {
        JSONObject json = new JSONObject();
        String sql = "SELECT word, pronunciation, meaning FROM engviet WHERE word = ?";
        
        try (Connection conn = connect(); 
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, word);
            
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
 
                    json.put("word", rs.getString("word"));
                    json.put("pronunciation", rs.getString("pronunciation"));
                    json.put("meaning", rs.getString("meaning"));
                } else {
                    json.put("error", "No results found.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            json.put("error", "Error occurred while searching for the word.");
        }
        
        return json;
    }


    public static void createWord(String word) {

    }

    public static void readWord() {

    }

    public static void updateWord(int id, String newWord) {

    }

    public static void deleteWord(int id) {

    }
}