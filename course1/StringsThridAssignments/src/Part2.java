
public class Part2 {
  public float cgRatio(String dna) {
    int startIndex = 0;
    int cIndex = dna.indexOf("C", startIndex);
    int gIndex = dna.indexOf("G", startIndex);
    float count = 0;

    while (true) {
      if (cIndex == -1 && gIndex == -1) {
        break;
      }
      if (cIndex != -1) {
        cIndex = dna.indexOf("C", cIndex + 1);
        count++;
      }
      if (gIndex != -1) {
        gIndex = dna.indexOf("G", gIndex + 1);
        count++;
      }
    }
    return count / dna.length();
  }

  public int countCTG(String dna) {
    int startIndex = 0;
    int cIndex = dna.indexOf("C", startIndex);
    int tIndex = dna.indexOf("T", startIndex);
    int gIndex = dna.indexOf("G", startIndex);
    int count = 0;

    while (true) {
      if (cIndex == -1 && gIndex == -1) {
        break;
      }
      if (cIndex != -1) {
        cIndex = dna.indexOf("C", cIndex + 1);
        count++;
      }
      if (gIndex != -1) {
        gIndex = dna.indexOf("G", gIndex + 1);
        count++;
      }
      if (tIndex != -1) {
        tIndex = dna.indexOf("T", tIndex + 1);
        count++;
      }
    }
    return count;
  }
}
