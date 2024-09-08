import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.json.JSONObject;

public class DBHelper {
    private static final String URL = "jdbc:sqlite:mydatabase.db"; // Đường dẫn tới cơ sở dữ liệu SQLite

    public static Connection connect() {
        try {
            return DriverManager.getConnection(URL);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String searchWord(String word) {
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
        
        return json.toString(); 
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