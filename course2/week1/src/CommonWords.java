import edu.duke.*;

public class CommonWords {
  public String[] getCommon() {
    FileResource fr = new FileResource("data/common.txt");
    String[] common = new String[20];
    int index = 0;
    for (String s : fr.words()) {
      common[index] = s;
      index++;
    }
    return common;
  }

  public int indexOf(String[] list, String word) {
    for (int i = 0; i < list.length; i++) {
      if (list[i].equals(word)) {
        return i;
      }
    }
    return -1;
  }

  public void countWords(FileResource fr, String[] common, int[] counts) {
    for (String word : fr.words()) {
      word = word.toLowerCase();
      int index = indexOf(common, word);
      if (index != -1) {
        counts[index] += 1;
      }
    }
  }

  public void countShakespeare() {
    String[] plays = { "caesar.txt", "errors.txt", "hamlet.txt", "likeit.txt", "macbeth.txt", "romeo.txt" };
    String[] common = getCommon();
    int[] counts = new int[common.length];
    for (int i = 0; i < plays.length; i++) {
      FileResource fr = new FileResource("data/" + plays[i]);
      countWords(fr, common, counts);
      System.out.println("done with " + plays[i]);
    }

    for (int i = 0; i < common.length; i++) {
      System.out.println(common[i] + "\t" + counts[i]);
    }
  }
}
