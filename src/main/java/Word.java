import java.util.ArrayList;

public class Word {
  private String mWord;
  private static ArrayList<Word> words = new ArrayList<Word>();
  private int mId;
  private ArrayList<Definition> mDefinitions;

  public Word(String word){
    mWord = word;
    words.add(this);
    mId = words.size();
    mDefinitions = new ArrayList<Definition>();
  }

  public String getWord(){
    return mWord;
  }

  public int getId(){
    return mId;
  }

  public static ArrayList<Word> all(){
    return words;
  }

  public ArrayList<Definition> getDefinitions() {
    return mDefinitions;
  }

  public void addDefinition(Definition definition) {
    mDefinitions.add(definition);
  }

  public static Word find(int id) {
    try {
      return words.get(id-1);
    } catch (IndexOutOfBoundsException ioobe) {
      return null;
    }
  }

  public static void clear() {
    words.clear();
  }
}
