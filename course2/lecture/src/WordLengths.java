import edu.duke.*;

public class WordLengths {
  public void countWordLengths(FileResource resource, int[] counts) {
    for (String word : resource.words()) {
      char firstChar = word.charAt(0);
      char lastChar = word.charAt(word.length() - 1);
      if (!Character.isLetter(firstChar) && !Character.isLetter(lastChar)) {
        word = word.substring(1, word.length() - 1);
      } else if (Character.isLetter(firstChar) && !Character.isLetter(lastChar)) {
        word = word.substring(0, word.length() - 1);
      } else if (!Character.isLetter(firstChar) && Character.isLetter(lastChar)) {
        word = word.substring(1);
      }
      int len = word.length();
      counts[len] += 1;
    }
  }

  public int indexOfMax(int[] values) {
    int maxIndex = 0;
    for (int n : values) {
      if (n > maxIndex) {
        maxIndex = n;
      }
    }
    return maxIndex;
  }

  public void testCountWordLengths() {
    FileResource fr = new FileResource();
    int[] counts = new int[31];
    countWordLengths(fr, counts);
    int maxIndex = indexOfMax(counts);
    int mostCommonLen = 0;
    for (int i = 0; i < counts.length; i++) {
      int len = counts[i];
      if (len == maxIndex) {
        mostCommonLen = i;
      }
      if (len != 0) {
        System.out.println(counts[i] + " words of length of " + i);
      }
    }
    System.out.println("Num of most common length: " + maxIndex);
    System.out.println("Most common length: " + mostCommonLen);
  }
}
