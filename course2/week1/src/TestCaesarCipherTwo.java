import edu.duke.*;

public class TestCaesarCipherTwo {
  public int[] countLetters(String input) {
    String alph = "abcdefghijklmnopqrstuvwxyz";
    int[] counts = new int[26];
    for (int i = 0; i < input.length(); i++) {
      char ch = Character.toLowerCase(input.charAt(i));
      int dex = alph.indexOf(ch);
      if (dex != -1) {
        counts[dex] += 1;
      }
    }
    return counts;
  }

  public int maxIndex(int[] vals) {
    int maxDex = 0;
    for (int i = 0; i < vals.length; i++) {
      if (vals[i] > vals[maxDex]) {
        maxDex = i;
      }
    }
    return maxDex;
  }

  public String halfOfString(String message, int start) {
    StringBuilder sb = new StringBuilder();
    for (int i = start; i < message.length(); i += 2) {
      char ch = message.charAt(i);
      sb.append(ch);
    }
    return sb.toString();
  }

  /**
   * You need to put keys directly in the file "TestCaesarCipher"
   *
   * @param None
   * @return print out both encrypted & decrypted string
   */
  public void simpleTests() {
    FileResource fr = new FileResource();
    String file = fr.asString();
    CaesarCipherTwo cct = new CaesarCipherTwo(17, 3);
    String encrypted = cct.encrypt(file);
    String decrypted = cct.decrypt(file);
    System.out.println("Encrypted: " + encrypted);
    System.out.println("Decrypted: " + decrypted);

    String decryptWithoutKeys = breakCaesarCipher(file);
    System.out.println("Decrypted with No Key: " + decryptWithoutKeys);
  }

  /**
   * Break Caesar Cipher with two keys and decrypt
   *
   * @param (String)input
   */
  public String breakCaesarCipher(String input) {
    String firstHalf = halfOfString(input, 0);
    String secondHalf = halfOfString(input, 1);
    int[] firstCounts = countLetters(firstHalf);
    int[] secondCounts = countLetters(secondHalf);
    int firstKey = maxIndex(firstCounts);
    int secondKey = maxIndex(secondCounts);
    CaesarCipherTwo cct = new CaesarCipherTwo(firstKey, secondKey);
    String decrypted = cct.decrypt(input);

    return decrypted;
  }
}
