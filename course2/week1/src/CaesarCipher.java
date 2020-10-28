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
    StringBuilder sb = new StringBuilder(input);
    for (int i = 0; i < sb.length(); i++) {
      char currChar = sb.charAt(i);
      if (Character.isLowerCase(currChar)) {
        char currUpperChar = Character.toUpperCase(currChar);
        int idx = alphabet.indexOf(currUpperChar);
        if (idx != -1) {
          char newChar = shiftedAlphabet.charAt(idx);
          newChar = Character.toLowerCase(newChar);
          sb.setCharAt(i, newChar);
        }
      } else if (Character.isUpperCase(currChar)) {
        int idx = alphabet.indexOf(currChar);
        if (idx != -1) {
          char newChar = shiftedAlphabet.charAt(idx);
          sb.setCharAt(i, newChar);
        }
      }
    }
    return sb.toString();
  }

  public String decrypt(String input) {
    CaesarCipher cc = new CaesarCipher(26 - mainKey);
    return cc.encrypt(input);
  }
}
