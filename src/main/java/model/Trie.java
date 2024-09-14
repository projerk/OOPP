package model;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;

public class Trie {
    private static Trie instance;
    private TrieNode root;

    private Trie() {
        root = new TrieNode();
    }

    public static Trie getInstance() {
        if (instance == null) {
            synchronized (Trie.class) {
                if (instance == null) {
                    instance = new Trie();
                }
            }
        }
        return instance;
    }

    public void insert(String word) {
        TrieNode node = root;
        for (char c : word.toCharArray()) {
            if (!node.containsKey(c)) {
                node.put(c, new TrieNode());
            }
            node = node.get(c);
        }
        node.setEndOfWord(true);
    }

    public boolean search(String word) {
        TrieNode node = searchNode(word);
        return node != null && node.isEndOfWord();
    }

    public List<String> getWordsWithPrefix(String prefix) {
        List<String> words = new ArrayList<>();
        TrieNode node = searchNode(prefix);
        if (node != null) {
            collectWords(node, new StringBuilder(prefix), words);
        }
        return words;
    }

    private TrieNode searchNode(String word) {
        TrieNode node = root;
        for (char c : word.toCharArray()) {
            if (node.containsKey(c)) {
                node = node.get(c);
            } else {
                return null;
            }
        }
        return node;
    }

    private void collectWords(TrieNode node, StringBuilder prefix, List<String> words) {
        if (node.isEndOfWord()) {
            words.add(prefix.toString());
        }
        for (char c : node.getChildren().keySet()) {
            prefix.append(c);
            collectWords(node.get(c), prefix, words);
            prefix.deleteCharAt(prefix.length() - 1);
        }
    }
}
