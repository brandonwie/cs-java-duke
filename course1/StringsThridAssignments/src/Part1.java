import edu.duke.StorageResource;

public class Part1 {
  public int findStopCodon(String dna, int startIndex, String stopCodon) {
    int stopIndex = dna.indexOf(stopCodon, startIndex);
    if (((stopIndex - startIndex) % 3) == 0) {
      return stopIndex;
    } else {
      return dna.length();
    }
  }

  public String findGene(String dna) {
    int startIndex = dna.indexOf("ATG");
    if (startIndex == -1) {
      return "";
    }
    int minIndex = 0;
    int taaIndex = findStopCodon(dna, startIndex, "TAA");
    int tagIndex = findStopCodon(dna, startIndex, "TAG");
    int tgaIndex = findStopCodon(dna, startIndex, "TGA");
    if (taaIndex == -1 || tagIndex != -1 && tagIndex < taaIndex) {
      minIndex = tagIndex;
    } else {
      minIndex = taaIndex;
    }
    if (minIndex == -1 || tgaIndex != -1 && tgaIndex < minIndex) {
      minIndex = tgaIndex;
    }
    if (minIndex == -1) {
      return "";
    }
    return dna.substring(startIndex, minIndex + 3);
  }

  public void testFindStopCodon() {
    String a = "ATGATCTAATTTATGCTGCAACGGTGAAGA";
    String b = "ATGATCATAAGAAGATAATAGAGGGCCATGTAA";

    int result1 = findStopCodon(a, 0, "TAA");
    int result2 = findStopCodon(b, 0, "TAG");

    System.out.println(result1);
    System.out.println(result2);
  }

  public void testFindGene() {

    String a = "ATGATCTAATTTATGCTGCAACGGTGAAGA";
    String b = "";
    String c = "ATGATCATAAGAAGATAATAGAGGGCCATGTAA";

    String result1 = findGene(a);
    String result2 = findGene(b);
    String result3 = findGene(c);

    System.out.println(result1);
    System.out.println(result2);
    System.out.println(result3);
  }

  public void getAllGenes(String dna) {
    StorageResource sr = new StorageResource();
    int startIndex = 0;
    while (true) {
      String currGene = findGene(dna);
      if (currGene.isEmpty()) {
        break;
      }
      sr.add(currGene);
      startIndex = dna.indexOf(currGene, startIndex) + currGene.length();
      dna = dna.substring(startIndex);
    }
    for (String s : sr.data()) {
      System.out.println(s);
    }
  }
}
