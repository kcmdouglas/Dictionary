import java.util.HashMap;
import java.util.ArrayList;

import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;

public class App {
  public static void main(String[] args) {
    staticFileLocation("/public");
    String layout = "templates/layout.vtl";

    get("/", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/home.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/words", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      model.put("words", Word.all());
      model.put("template", "templates/words.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/words/new", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/word-form.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/words", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      String word = request.queryParams("word");
      Word newWord = new Word(word);
      model.put("word", word);
      model.put("template", "templates/success.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("words/:id/definitions/new", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      Word word = Word.find(Integer.parseInt(request.params(":id")));
      model.put("word", word);
      model.put("template", "templates/word-def-form.vtl");
      return new ModelAndView(model, layout);
      }, new VelocityTemplateEngine());

    post("/definitions", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      Word word = Word.find(Integer.parseInt(request.queryParams("wordId")));
      ArrayList<Definition> definitions = word.getDefinitions();

      if (definitions == null) {
        definitions = new ArrayList<Definition>();
        request.session().attribute("definitions", definitions);
      }

      String definition = request.queryParams("definition");
      Definition newDefinition = new Definition(definition);

      word.addDefinition(newDefinition);
      model.put("definition", definition);
      model.put("template", "templates/success.vtl");
      return new ModelAndView(model, layout);
      }, new VelocityTemplateEngine());

    get("words/:id", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      Word word = Word.find(Integer.parseInt(request.params(":id")));
      model.put("word", word);
      model.put("template", "templates/singleword.vtl");
      return new ModelAndView(model, layout);
      }, new VelocityTemplateEngine());

    get("/words-and-defs", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      model.put("words", Word.all());
      model.put("definitions", Definition.all());
      model.put("template", "templates/words-and-defs.vtl");
      return new ModelAndView(model, layout);
      }, new VelocityTemplateEngine());
  }
}
