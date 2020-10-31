import edu.duke.*;
import java.util.ArrayList;

public class WordFrequencies {
  private ArrayList<String> myWords;
  private ArrayList<Integer> myFreqs;

  // constructor
  public WordFrequencies() {
    myWords = new ArrayList<String>();
    myFreqs = new ArrayList<Integer>();
  }

  public void findUnique() {
    // clear both before add a new file
    myWords.clear();
    myFreqs.clear();
    FileResource fr = new FileResource();
    for (String s : fr.words()) {
      s = s.toLowerCase();
      int index = myWords.indexOf(s);
      if (index == -1) {
        myWords.add(s);
        myFreqs.add(1);
      } else {
        int value = myFreqs.get(index);
        myFreqs.set(index, value + 1);
      }
    }
  }

  public void tester() {
    findUnique();
    int maxFreq = findIndexOfMax();
    System.out.println("# unique words: " + myWords.size());
    System.out.println("# max frequency: " + maxFreq);
    System.out.println(myWords.get(2));
    // for (int i = 0; i < myWords.size(); i++) {
    // System.out.println(myFreqs.get(i) + "\t" + myWords.get(i));
    // }
  }

  public int findIndexOfMax() {
    int maxFreq = 0;
    int maxIndex = -1;
    for (int i = 0; i < myWords.size(); i++) {
      // get frequency of index
      int currentFreq = myFreqs.get(i);
      if (maxFreq < currentFreq) {
        maxFreq = currentFreq;
        maxIndex = i;
      }
    }
    System.out.println(maxIndex);
    return maxFreq;
  }
}
