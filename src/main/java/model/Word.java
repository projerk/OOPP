package model;
import java.util.ArrayList;

public class Word {
    private String word;
    
    private String phonetic;

    private ArrayList<Type> types = new ArrayList<>();

    public void setWord(String word) {
        this.word = word;
    }

    public void setPhonetic(String phonetic) {
        this.phonetic = phonetic;
    }

    public String getWord() {
        return this.word;
    }

    public String getPhonetic() {
        return this.phonetic;
    }

    public void addType(Type type) {
        types.add(type);
    }

    public int getTypeArraySize() {
        return this.types.size();
    }

    public Type getType(int idx) {
        return types.get(idx);
    }

    public ArrayList<Type> getTypeArray() {
        return types;
    }
}