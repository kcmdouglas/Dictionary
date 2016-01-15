import java.util.ArrayList;

public class Word {
  private String mWord;
  private static ArrayList<Word> words = new ArrayList<Word>();
  private int mId;

  public Word(String word){
    mWord = word;
    words.add(this);
    mId = words.size();
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

  public static void clear() {
    words.clear();
  }
}
