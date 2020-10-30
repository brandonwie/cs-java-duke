import java.util.*;
import edu.duke.*;

public class CharactersInPlay {
  private ArrayList<String> names;
  private ArrayList<Integer> counts;

  // constructor
  public CharactersInPlay() {
    names = new ArrayList<String>();
    counts = new ArrayList<Integer>();
  }

  public void update(String person) {
    int personIndex = names.indexOf(person);
    if (personIndex == -1) {
      names.add(person);
      counts.add(1);
    } else {
      int value = counts.get(personIndex);
      counts.set(personIndex, value + 1);
    }
  }

  public void findAllChracters() {
    names.clear();
    counts.clear();
    FileResource fr = new FileResource();
    for (String s : fr.lines()) {
      int periodIndex = s.indexOf('.');
      int startIndex = 0;
      for (int i = 0; i < s.length(); i++) {
        char c = s.charAt(i);
        if (Character.isLetter(c)) {
          startIndex = i;
          break;
        }
      }
      if (periodIndex != -1) {
        String person = s.substring(startIndex, periodIndex);
        update(person);
      }
    }
  }

  public void tester() {
    findAllChracters();
    int max = 0;
    int index = 0;
    for (int i = 0; i < names.size(); i++) {
      int count = counts.get(i);
      if (count > max) {
        max = count;
        index = i;
      }
    }
    System.out.println("main character: " + names.get(index) + " with " + max + " lines");
    charactersWithNumParts(10, 15);
  }

  public void charactersWithNumParts(int num1, int num2) {
    System.out.println("Characters List with lines" + num1 + " ~ " + num2);
    for (int i = 0; i < names.size(); i++) {
      int count = counts.get(i);
      if (count >= num1 && count <= num2) {
        System.out.println(names.get(i));
      }
    }
  }
}
