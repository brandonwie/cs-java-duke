import java.util.*;
import edu.duke.*;

public class CodonPractice {
  private HashMap<String, Integer> map;

  // constructor
  public CodonPractice() {
    map = new HashMap<String, Integer>();
  }

  private void buildCodonMap(int start, String dna) {
    // clear map before building a new one
    map.clear();
    while (true) {
      if (start + 3 > dna.length()) {
        break;
      }
      String codon = dna.substring(start, start + 3);
      if (!map.containsKey(codon)) {
        map.put(codon, 1);
      } else {
        map.put(codon, map.get(codon) + 1);

      }
      start += 3;
    }

  }

  private String getMostCommonCodon() {
    int mostFreqCount = -1;
    String mostFreqStr = null;
    for (String codon : map.keySet()) {
      if (map.get(codon) > mostFreqCount) {
        mostFreqCount = map.get(codon);
        mostFreqStr = codon;
      }
    }
    return mostFreqStr;
  }

  private void printCodonCounts(int start, int end) {
    for (String s : map.keySet()) {
      int count = map.get(s);
      if (count >= start && count <= end) {
        System.out.println("Codon: " + s + " Count: " + count);
      }
    }
  }

  public void tester() {
    FileResource fr = new FileResource();
    String s = fr.asString().trim();
    for (int i = 0; i < 3; i++) {
      buildCodonMap(i, s.toUpperCase());
      String mostCommonCodon = getMostCommonCodon();
      int count = map.get(mostCommonCodon);
      System.out.println("Reading frame: " + i + " has " + map.size() + " unique codons");
      System.out.println("most common codon is " + mostCommonCodon + " with count " + count);
      if (i == 0) {
        printCodonCounts(7, 7);
      }
    }

  }
}
