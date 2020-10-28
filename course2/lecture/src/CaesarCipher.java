public class CaesarCipher {
  // Fields
  private String alphabet; // instance variable
  private String shiftedAlphabet;
  private int mainKey;

  // constructor
  public CaesarCipher(int key) {
    mainKey = key;
    alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    shiftedAlphabet = alphabet.substring(key) + alphabet.substring(0, key);
  }

  public String encrypt(String input) {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < sb.length(); i++) {
      char c = sb.charAt(i);
      int idx = alphabet.indexOf(c);
      if (idx != -1) {
        c = shiftedAlphabet.charAt(idx);
        sb.setCharAt(i, c);
      }
    }
    return sb.toString();
  }

  public String decrypt(String input) {
    CaesarCipher cc = new CaesarCipher(26 - mainKey);
    return cc.encrypt(input);
  }
}
