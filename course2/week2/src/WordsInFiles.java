import java.io.File;
import java.util.*;
import edu.duke.*;

public class WordsInFiles {
  // <word, fname>
  private HashMap<String, ArrayList<String>> map;

  // constructor
  public WordsInFiles() {
    map = new HashMap<>();
  }

  private void addWordsFromFile(File f) {
    String fname = f.getName();
    FileResource fr = new FileResource(f);

    for (String s : fr.words()) {
      if (map.containsKey(s)) {
        int fnameIndex = map.get(s).indexOf(fname);
        if (fnameIndex == -1) {
          map.get(s).add(fname);
        }
      } else {
        map.put(s, new ArrayList<String>());
        map.get(s).add(fname);
      }
    }
  }

  private void buildWordFileMap() {
    map.clear();
    DirectoryResource dr = new DirectoryResource();
    for (File f : dr.selectedFiles()) {
      addWordsFromFile(f);
    }
  }

  private int maxNumber() {
    int max = -1;
    for (ArrayList<String> files : map.values()) {
      if (files.size() > max) {
        max = files.size();
      }
    }
    return max;
  }

  private ArrayList<String> wordsInNumFiles(int number) {
    ArrayList<String> words = new ArrayList<>();
    for (String word : map.keySet()) {
      if (map.get(word).size() == number) {
        words.add(word);
      }
    }
    return words;
  }

  private void printFilesIn(String word) {
    ArrayList<String> filenames = map.get(word);
    // hashmap .get returns null if there's no key
    if (filenames != null) {
      for (String file : filenames) {
        System.out.println(file);
      }
    } else {
      System.out.println("No key was found");
    }
  }

  public void tester() {
    buildWordFileMap();
    // int count = wordsInNumFiles(4).size();
    // System.out.println(count);
    // int count = 0;
    // for (ArrayList<String> filename : map.values()) {
    // if (filename.size() == 4) {
    // count++;
    // }
    // }
    // System.out.println(count);

    // int max = maxNumber();
    // ArrayList<String> filenames = wordsInNumFiles(max);
    // System.out.println("The greatest number of files: " + max);
    // for (String s : filenames) {
    // System.out.println("word: " + "\"" + s + "\"");
    // System.out.println("file names: ");
    // printFilesIn(s);
    // }
    // System.out.println(filenames.size());

    printFilesIn("sea");
  }
}
