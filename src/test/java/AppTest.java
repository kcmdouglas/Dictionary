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
}
