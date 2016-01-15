import java.util.ArrayList;

import org.junit.*;
import static org.junit.Assert.*;

public class WordTest {

  @Rule
  public ClearRule clearRule = new ClearRule();

  @Test
  public void getWord_returnsWord_true() {
    Word testWord = new Word("Limn");
    assertEquals("Limn", testWord.getWord());
  }

  @Test
  public void getId_returnsWordId() {
    Word testWord = new Word("Limn");
    assertTrue(Word.all().size() == testWord.getId());
  }

  @Test
  public void getWords_initiallyReturnsEmptyArrayList() {
    Word testWord = new Word("Limn");
    assertTrue(testWord.getDefinitions() instanceof ArrayList);
  }

  @Test
  public void all_returnsAllInstancesOfWord_true() {
    Word firstWord = new Word("Limn");
    Word secondWord = new Word("Loam");
    assertTrue(Word.all().contains(firstWord));
    assertTrue(Word.all().contains(secondWord));
  }

  @Test
  public void clear_removesAllWordInstancesFromMemory() {
    Word testWord = new Word("Limn");
    Word.clear();
    assertEquals(Word.all().size(), 0);
  }

  @Test
  public void find_returnsWordWithSameId() {
    Word testWord = new Word("Limn");
    assertEquals(Word.find(testWord.getId()), testWord);
  }

  @Test
  public void addDefinition_addsDefinitionToList() {
    Word testWord = new Word("Limn");
    Definition testDefinition = new Definition("To draw, paint, outline, or describe");
    testWord.addDefinition(testDefinition);
    assertTrue(testWord.getDefinitions().contains(testDefinition));
  }
}
