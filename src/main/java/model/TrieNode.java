package model;

import java.util.HashMap;
import java.util.Map;

public class TrieNode {
    private final Map<Character, TrieNode> children;
    private boolean endOfWord;
    private int wordId; 

    public TrieNode() {
        children = new HashMap<>();
        endOfWord = false;
        wordId = -1; 
    }

    public boolean containsKey(char ch) {
        return children.containsKey(ch);
    }

    public TrieNode get(char ch) {
        return children.get(ch);
    }

    public void put(char ch, TrieNode node) {
        children.put(ch, node);
    }

    public Map<Character, TrieNode> getChildren() {
        return children;
    }

    public boolean isEndOfWord() {
        return endOfWord;
    }

    public void setEndOfWord(boolean endOfWord) {
        this.endOfWord = endOfWord;
    }

    public int getWordId() {
        return wordId;
    }

    public void setWordId(int wordId) {
        this.wordId = wordId;
    }
}
