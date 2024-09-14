package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;

public class TrieLoader {
    public static void loadTrie() {
        Trie trie = Trie.getInstance();
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:database.db");
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT word FROM engviet")) {

            while (rs.next()) {
                String word = rs.getString("word");
                trie.insert(word);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}