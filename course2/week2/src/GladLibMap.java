import edu.duke.*;
import java.util.*;

public class GladLibMap {
  // HashMap<String category, ArrayList words>
  private HashMap<String, ArrayList<String>> map;
  private int numOfReplacedWords;
  private ArrayList<String> usedCategories;
  private Random myRandom;

  // private static String dataSourceURL =
  // "http://dukelearntoprogram.com/course3/data/";
  private static String dataSourceDirectory = "data/GladLibData/data/";

  public GladLibMap() {
    map = new HashMap<>();
    numOfReplacedWords = 0;
    usedCategories = new ArrayList<String>();
    myRandom = new Random();
    initializeFromSource(dataSourceDirectory);
  }

  private void initializeFromSource(String source) {
    String[] categories = { "adjective", "noun", "color", "country", "name", "animal", "timeframe", "verb", "fruit" };
    for (String category : categories) {
      ArrayList<String> words = readIt(dataSourceDirectory + category + ".txt");
      map.put(category, words);
    }
  }

  private String randomFrom(ArrayList<String> source) {
    int index = myRandom.nextInt(source.size());
    return source.get(index);
  }

  private String getSubstitute(String category) {
    if (category.equals("number")) {
      return "" + myRandom.nextInt(50) + 5;
    }
    if (map.containsKey(category)) {
      String randomWord = randomFrom(map.get(category));
      return randomWord;
    }
    return "**UNKNOWN**";
  }

  private String processWord(String w) {
    int first = w.indexOf("<");
    int last = w.indexOf(">", first);
    if (first == -1 || last == -1) {
      return w;
    }
    String word = w.substring(first + 1, last);
    if (!usedCategories.contains(word)) {
      usedCategories.add(word);
    }
    String prefix = w.substring(0, first);
    String suffix = w.substring(last + 1);
    String sub = getSubstitute(word);
    // track how many words are replaced
    numOfReplacedWords++;
    return prefix + sub + suffix;
  }

  private void printOut(String s, int lineWidth) {
    int charsWritten = 0;
    // \s : white space
    for (String w : s.split("\\s+")) {
      if (charsWritten + w.length() > lineWidth) {
        System.out.println();
        charsWritten = 0;
      }
      System.out.print(w + " ");
      charsWritten += w.length() + 1;
    }
  }

  private String fromTemplate(String source) {
    String story = "";
    if (source.startsWith("http")) {
      URLResource resource = new URLResource(source);
      for (String word : resource.words()) {
        story = story + processWord(word) + " ";
      }
    } else {
      FileResource resource = new FileResource(source);
      for (String word : resource.words()) {
        story = story + processWord(word) + " ";
      }
    }
    return story;
  }

  private ArrayList<String> readIt(String source) {
    ArrayList<String> list = new ArrayList<String>();
    if (source.startsWith("http")) {
      URLResource resource = new URLResource(source);
      for (String line : resource.lines()) {
        list.add(line);
      }
    } else {
      FileResource resource = new FileResource(source);
      for (String line : resource.lines()) {
        list.add(line);
      }
    }
    return list;
  }

  public int totalWordsInMap() {
    int totalWords = 0;
    for (ArrayList<String> list : map.values()) {
      totalWords += list.size();
    }
    return totalWords;
  }

  private int totalWordsConsidered() {
    int totalCategoriesUsed = 0;
    for (String category : usedCategories) {
      System.out.println("category: " + category);
      if (map.get(category) != null) {
        totalCategoriesUsed += map.get(category).size();
      }

    }
    return totalCategoriesUsed;
  }

  public void makeStory() {
    System.out.println("\n");
    String story = fromTemplate("data/GladLibData/data/madtemplate2.txt");
    printOut(story, 60);
    System.out.println();
    System.out.println("total modified words: " + numOfReplacedWords);
    int totalWords = totalWordsInMap();
    int totalCategoryUsed = totalWordsConsidered();
    System.out.println("total words: " + totalWords + "/total categories used: " + totalCategoryUsed);
  }

}