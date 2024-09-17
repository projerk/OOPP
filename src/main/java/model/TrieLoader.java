package model;

import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;

public class TrieLoader {
    public static void loadTrie() {
        Trie trie = Trie.getInstance();
        String dbPath = Paths.get("database.db").toAbsolutePath().toString();
        String url = "jdbc:sqlite:" + dbPath;
        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT id, word FROM engviet")) {

            while (rs.next()) {
                int wordId = rs.getInt("id");
                String word = rs.getString("word");
                trie.insert(word, wordId);  // Chèn từ và id vào trie
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}