
/**
 * Contains actual methods to break Caesar Cipher
 */

import edu.duke.*;

public class CaesarBreaker {
  public int[] countLetters(String message) {
    String alph = "abcdefghijklmnopqrstuvwxyz";
    int[] counts = new int[26];
    for (int i = 0; i < message.length(); i++) {
      char ch = Character.toLowerCase(message.charAt(i));
      int dex = alph.indexOf(ch);
      if (dex != -1) {
        counts[dex] += 1;
      }
    }
    return counts;
  }

  public String decrypt(String encrypted) {
    CaesarCipher cc = new CaesarCipher();
    int[] freqs = countLetters(encrypted);
    int maxDex = maxIndex(freqs);
    int dkey = maxDex - 4;
    if (maxDex < 4) {
      dkey = 26 - (4 - maxDex);
    }
    return cc.encrypt(encrypted, 26 - dkey);
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

  public int getKey(String s) {
    int[] counts = countLetters(s);
    int key = maxIndex(counts);
    return key;
  }

  public String decryptTwoKeys(String encrypted) {
    CaesarCipher cc = new CaesarCipher();
    String firstStr = halfOfString(encrypted, 0);
    String secondStr = halfOfString(encrypted, 1);
    int firstKey = getKey(firstStr);
    System.out.println("First Key Found: " + firstKey);
    int secondKey = getKey(secondStr);
    System.out.println("Second Key Found: " + secondKey);
    String decrypted = cc.encryptTwoKeys(encrypted, firstKey, secondKey);
    return decrypted;
  }

  public void testDecrypt() {
    String result = halfOfString("Qbkm Zgis", 0);
    System.out.println(result);
  }

  /**
   * Using For Loops to break two-key Caesar Cipher using eyeball method
   */
  public void decryptCipherWithTwoKeys() {
    CaesarCipher cc = new CaesarCipher();
    FileResource fr = new FileResource();
    String file = fr.asString();
    int mostCountedE = 0;
    int iIndex = 0;
    int jIndex = 0;
    String maxEStr = "";
    for (int i = 0; i < 26; i++) {
      for (int j = 0; j < 26; j++) {
        String s = cc.encryptTwoKeys(file, i, j);
        int countCharE = 0;
        for (int k = 0; k < s.length(); k++) {
          char c = s.charAt(k);
          if (Character.toLowerCase(c) == 'e') {
            countCharE++;
          }
        }
        if (countCharE > mostCountedE) {
          mostCountedE = countCharE;
          maxEStr = s;
          iIndex = i;
          jIndex = j;
        }
      }
    }
    System.out.println(maxEStr + "\nFirst Index: " + iIndex + "\tSecond Index: " + jIndex);
  }
}
