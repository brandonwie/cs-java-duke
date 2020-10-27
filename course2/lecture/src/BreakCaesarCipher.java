/**
 * Contains basic methods to break Caesar Cipher
 */

public class BreakCaesarCipher {
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
}
