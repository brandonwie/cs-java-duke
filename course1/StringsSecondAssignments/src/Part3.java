public class Part3 {
  // Copied from Part1
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

  public void printAllGenes(String dna) {
    int startIndex = 0;
    while (true) {
      String currGene = findGene(dna);
      if (currGene.isEmpty()) {
        break;
      }
      System.out.println(currGene);
      startIndex = dna.indexOf(currGene, startIndex) + currGene.length();
      dna = dna.substring(startIndex);
    }

  }

  // New Methods
  public int countGenes(String dna) {
    int startIndex = 0;
    int count = 0;
    while (true) {
      String currGene = findGene(dna);
      if (currGene.isEmpty()) {
        break;
      }
      startIndex = dna.indexOf(currGene, startIndex) + currGene.length();
      dna = dna.substring(startIndex);
      count++;
    }
    return count;
  }

  public void testCountGene() {
    int result1 = countGenes("ATGTAAGATGCCCTAGT");
    int result2 = countGenes("");
    int result3 = countGenes("AAATGAAACCCTGAACCATGGGGCTTTAA");

    System.out.println(result1);
    System.out.println(result2);
    System.out.println(result3);

  }
}
