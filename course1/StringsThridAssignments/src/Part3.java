import java.util.ArrayList;
import edu.duke.*;

public class Part3 {
  /**
   *
   * @param dna
   * @param startIndex
   * @param stopCodon  "TAA" or "TAG" or "TGA"
   * @return the stopCondon's index OR if nothing found, returns full length of
   *         dna string
   */
  public int findStopCodon(String dna, int startIndex, String stopCodon) {
    int stopIndex = dna.indexOf(stopCodon, startIndex + 3);
    // while loop until stopIndex is found
    while (stopIndex != -1) {
      int diff = stopIndex - startIndex;
      if (diff % 3 == 0) {
        return stopIndex;
      } else {
        stopIndex = dna.indexOf(stopCodon, stopIndex + 1);
      }
    }
    return dna.length();
  }

  public int findCTGCodon(String dna) {
    int startIndex = 0;
    int count = 0;
    int ctgIndex = dna.indexOf("ctg", startIndex);
    while (ctgIndex != -1) {
      count++;
      ctgIndex = dna.indexOf("ctg", ctgIndex + 3);
    }
    return count;
  }

  /**
   *
   * @param dna
   * @return A substring that satisfies DNA format OR an empty string if nothing
   *         is found
   */
  public String findGene(String dna) {
    int startIndex = dna.indexOf("atg");
    // if startCodon doesn't exist, return empty string
    if (startIndex == -1) {
      return "";
    } else {
      // start finding stopCodons (return stopIndex OR dna.length())
      int taaIndex = findStopCodon(dna, startIndex, "taa");
      int tagIndex = findStopCodon(dna, startIndex, "tag");
      int tgaIndex = findStopCodon(dna, startIndex, "tga");
      // findStopCodon() will return full length if nothing's found
      int len = dna.length();

      // if no stopCodon is found, return an empty string
      if (taaIndex == len && tagIndex == len && tgaIndex == len) {
        return "";
      }
      // if one is found, set minIndex(a right stopCodon's index)
      int minIndex = Math.min(taaIndex, Math.min(tagIndex, tgaIndex));
      return dna.substring(startIndex, minIndex + 3);
    }
  }

  /**
   *
   * @param dna
   * @return StorageResource consist of genes
   */
  public StorageResource getAllGenes(String dna) {
    StorageResource geneList = new StorageResource();
    int startIndex = 0;
    while (true) {
      String gene = findGene(dna.substring(startIndex));
      if (gene.isEmpty()) {
        break;
      } else {
        geneList.add(gene);
        startIndex = dna.indexOf(gene, startIndex) + gene.length();
      }
    }
    return geneList;
  }

  public float cgRatio(String dna) {
    int startIndex = 0;
    int cIndex = dna.indexOf("c", startIndex);
    int gIndex = dna.indexOf("g", startIndex);
    int count = 0;

    while (true) {
      if (cIndex == -1 && gIndex == -1) {
        break;
      }
      if (cIndex != -1) {
        cIndex = dna.indexOf("c", cIndex + 1);
        count++;
      }
      if (gIndex != -1) {
        gIndex = dna.indexOf("g", gIndex + 1);
        count++;
      }
    }
    return (float) count / dna.length();
  }

  public float ctgRatio(String dna) {
    int startIndex = 0;
    int cIndex = dna.indexOf("c", startIndex);
    int tIndex = dna.indexOf("t", startIndex);
    int gIndex = dna.indexOf("g", startIndex);
    int count = 0;

    while (true) {
      if (cIndex == -1 && tIndex == -1 && gIndex == -1) {
        break;
      }
      if (cIndex != -1) {
        cIndex = dna.indexOf("c", cIndex + 1);
        count++;
      }
      if (tIndex != -1) {
        tIndex = dna.indexOf("g", tIndex + 1);
        count++;
      }
      if (gIndex != -1) {
        gIndex = dna.indexOf("g", gIndex + 1);
        count++;
      }
    }
    return (float) count / dna.length();
  }

  public void processGenes(StorageResource sr) {
    System.out.println("processGene starts...");
    int cgCount = 0;
    int ctgCount = 0;
    int longStrCount = 0;
    ArrayList<String> longStr = new ArrayList<String>();
    ArrayList<String> cgStr = new ArrayList<String>();
    ArrayList<String> ctgStr = new ArrayList<String>();
    String longestStr = "";
    int numOfGenes = 0;
    int sixtyPlus = 0;

    for (String s : sr.data()) {
      numOfGenes++;
      float cgRate = cgRatio(s);
      if (cgRate > 0.35) {
        cgStr.add(s);
        cgCount++;
      }
      float ctgRate = ctgRatio(s);
      if (ctgRate > 0.35) {
        ctgStr.add(s);
        ctgCount++;
      }
      if (s.length() > 9) {
        longStr.add(s);
        longStrCount++;
      }
      if (s.length() > longestStr.length()) {
        longestStr = s;
      }
      if (s.length() > 60) {
        sixtyPlus++;
      }
    }
    System.out.println("### Num of Total Genes: " + numOfGenes + " ###");
    System.out.println("### Strings > 9 char " + "(total) " + longStrCount + " ###");
    for (String s : longStr) {
      System.out.println("- " + s);
    }
    System.out.println("### C-G Ratio > 0.35 " + "(total) " + cgCount + " ###");
    for (String s : cgStr) {
      System.out.println("- " + s);
    }
    System.out.println("### CTG Ratio > 0.35 " + "(total) " + ctgCount + " ###");
    for (String s : ctgStr) {
      System.out.println("- " + s);
    }
    System.out.println("### Longest String:###\n" + "- " + longestStr);
    System.out.println("### Length of Longest String: " + longestStr.length());
    System.out.println("### Strings > 60 char: " + sixtyPlus + " ###");
  }

  public void testProcessGenes() {
    System.out.println("Test Starts...");
    FileResource fr = new FileResource("dna/GRch38dnapart.fa");
    String dna = fr.asString();
    dna = dna.toLowerCase();
    System.out.println("### DNA file length: " + dna.length());
    StorageResource data = getAllGenes(dna);
    processGenes(data);
    int ctgCount = findCTGCodon(dna);
    System.out.println("### CTG Count in DNA File: " + ctgCount);
  }
}