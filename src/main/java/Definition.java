import java.util.ArrayList;

public class Definition {
  private String mDefinition;
  private static ArrayList<Definition> definitions = new ArrayList<Definition>();
  private int mId;

  public Definition(String definition){
    mDefinition = definition;
    mId = definitions.size();
    definitions.add(this);
  }

  public static void clear() {
    definitions.clear();
  }
}
