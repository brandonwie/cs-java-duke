import edu.duke.*;

public class TestCaesarCipher {

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

  public int maxIndex(int[] vals) {
    int maxDex = 0;
    for (int i = 0; i < vals.length; i++) {
      if (vals[i] > vals[maxDex]) {
        maxDex = i;
      }
    }
    return maxDex;
  }

  public String breakCaesarCipher(String input) {
    int[] counts = countLetters(input);
    int key = maxIndex(counts);
    CaesarCipher cc = new CaesarCipher(key);
    String breakCipher = cc.encrypt(input);
    return breakCipher;
  }

  public void simpleTests() {
    FileResource fr = new FileResource();
    String file = fr.asString();
    CaesarCipher cc = new CaesarCipher(18);
    String encrypted = cc.encrypt(file);
    String broke = breakCaesarCipher(encrypted);
    String decrypted = cc.decrypt(file);
    System.out.println("Encrypted: " + encrypted);
    System.out.println("Decrypted: " + decrypted);
    System.out.println("Broken Cipher: " + broke);

  }
}
