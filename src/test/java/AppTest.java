import org.fluentlenium.adapter.FluentTest;
import org.junit.ClassRule;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import static org.assertj.core.api.Assertions.assertThat;
import static org.fluentlenium.assertj.FluentLeniumAssertions.assertThat;
import static org.fluentlenium.core.filter.FilterConstructor.*;

import org.junit.*;
import static org.junit.Assert.*;

public class AppTest extends FluentTest {
  public WebDriver webDriver = new HtmlUnitDriver();

  @Override
  public WebDriver getDefaultDriver() {
   return webDriver;
 }

  @ClassRule
  public static ServerRule server = new ServerRule();

  @Test
    public void rootTest() {
      goTo("http://localhost:4567/");
      assertThat(pageSource()).contains("Your Very Own Customizable Dictionary");
    }
  @Test
    public void wordIsCreatedTest() {
      goTo("http://localhost:4567/");
      click("a", withText("Add a New Word"));
      fill("#word").with("Limn");
      submit(".btn");
      assertThat(pageSource()).contains("Your new word has been carefully inscribed into your dictionary.");
    }

  @Test
    public void wordIsDisplayedTest() {
      goTo("http://localhost:4567/words/new");
      fill("#word").with("Limn");
      submit(".btn");
      click("a", withText("View Your Words"));
      assertThat(pageSource()).contains("Limn");
    }

  @Test
    public void wordDefinitionFormIsDisplayed() {
      goTo("http://localhost:4567/words/new");
      fill("#word").with("Loam");
      submit(".btn");
      click("a", withText("View Your Words"));
      click("a", withText("Loam"));
      click("a", withText("Add a New Definition"));
      assertThat(pageSource()).contains("Add a New Definition for Loam");
    }

  @Test
    public void definitionSuccessFormIsDisplayed() {
      goTo("http://localhost:4567/words/new");
      fill("#word").with("Anathema");
      submit(".btn");
      click("a", withText("View Your Words"));
      click("a", withText("Anathema"));
      click("a", withText("Add a New Definition"));
      fill("#definition").with("Someone or something that is very strongly disliked");
      submit(".btn");
      assertThat(pageSource()).contains("Your definition has been gingerly written beneath your word. Future generations will sing of your brilliance.");
    }

    @Test
      public void definitionsAppearOnIndividualWordPages() {
        goTo("http://localhost:4567/words/new");
        fill("#word").with("Windfall");
        submit(".btn");
        click("a", withText("View Your Words"));
        click("a", withText("Windfall"));
        click("a", withText("Add a New Definition"));
        fill("#definition").with("An unexpected, unearned, or sudden gain or advantage");
        submit(".btn");
        click("a", withText("View Your Words"));
        click("a", withText("Windfall"));
        assertThat(pageSource()).contains("An unexpected, unearned, or sudden gain or advantage");
      }

    @Test
      public void entireDictionaryPageDisplays() {
        goTo("http://localhost:4567/words/new");
        fill("#word").with("Haphazard");
        submit(".btn");
        click("a", withText("View Your Words"));
        click("a", withText("Haphazard"));
        click("a", withText("Add a New Definition"));
        fill("#definition").with("Having no plan, order, or direction");
        submit(".btn");
        click("a", withText("View Entire Dictionary"));
        assertThat(pageSource()).contains("Having no plan, order, or direction");
      }

}
