package model;
import java.util.ArrayList;

public class Type {
    private String type;
    
    private ArrayList<Meaning> meanings = new ArrayList<>();

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return this.type;
    }

    public void addMeaning(Meaning meaning) {
        meanings.add(meaning);
    }

    public int getMeaningArraySize() {
        return this.meanings.size();
    }

    public Meaning getMeaning(int idx) {
        return meanings.get(idx);
    }
}