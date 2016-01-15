import org.junit.*;
import static org.junit.Assert.*;
import java.util.ArrayList;

public class DefinitionTest {

  @Rule
  public ClearRule clearRule = new ClearRule();

  @Test
  public void definition_instantiatesCorrectly_true() {
      Definition newDefiniton = new Definition("To draw, paint, outline, or describe");
      assertEquals(true, newDefiniton instanceof Definition);
  }

  @Test
  public void Definition_instantiatesWithDefinition_true() {
      Definition newDefinition= new Definition("To draw, paint, outline, or describe");
      assertEquals("To draw, paint, outline, or describe", newDefinition.getDefinition());
  }


  @Test
  public void all_returnsAllInstancesOfDefinition_true() {
    Definition firstDefinition = new Definition("To draw, paint, outline, or describe");
    Definition secondDefinition = new Definition("A type of soil good for growing plants");
    assertTrue(Definition.all().contains(firstDefinition));
    assertTrue(Definition.all().contains(secondDefinition));
  }

  @Test
  public void newId_DefinitionsInstantiateWithAnID_true() {
    Definition newDefinition = new Definition("To draw, paint, outline, or describe");
    assertEquals(Definition.all().size(), newDefinition.getId());
  }

  @Test
  public void find_returnsDefinitionWithSameId_secondDefinition() {
    Definition firstDefinition = new Definition("To draw, paint, outline, or describe");
    Definition secondDefinition = new Definition("A type of soil good for growing plants");
    assertEquals(Definition.find(secondDefinition.getId()), secondDefinition);
  }

  @Test
  public void find_returnsNullWhenNoDefinitionFound_null() {
    assertTrue(Definition.find(999) == null);
  }

  @Test
  public void clear_emptiesAllDefinitionsFromArrayList() {
    Definition newDefinition = new Definition("To draw, paint, outline, or describe");
    Definition.clear();
    assertEquals(Definition.all().size(), 0);
  }
}
