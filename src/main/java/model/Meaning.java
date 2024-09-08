package model;

public class Meaning {
    private String meaning;
    
    private ArrayList<Example> examples = new ArrayList<>();

    public void setMeaning(String meaning) {
        this.meaning = meaning;
    }

    public String getMeaning() {
        return this.meaning;
    }

    public void addExample(Example example) {
        examples.add(example);
    }

    public int getExampleArraySize() {
        return examples.size();
    }

    public getExample(int idx) {
        return examples.get(idx);
    }
}