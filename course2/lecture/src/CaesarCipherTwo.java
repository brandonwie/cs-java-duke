public class CaesarCipherTwo {
  private String alphabet;
  private String shiftedAlphabet1;
  private String shiftedAlphabet2;
  private String reShiftedAlphabet1;
  private String reShiftedAlphabet2;

  public CaesarCipherTwo(int key1, int key2) {
    alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    shiftedAlphabet1 = alphabet.substring(key1) + alphabet.substring(0, key1);
    shiftedAlphabet2 = alphabet.substring(key2) + alphabet.substring(0, key2);
    reShiftedAlphabet2 = alphabet.substring(26 - key1) + alphabet.substring(26 - key1);
    reShiftedAlphabet2 = alphabet.substring(26 - key2) + alphabet.substring(26 - key2);

  }

  public String encrypt(String input) {
    StringBuilder sb = new StringBuilder(input);
    for (int i = 0; i < sb.length(); i++) {
      char c = sb.charAt(i);
      int idx = alphabet.indexOf(c);
      // first and every other
      if (idx != -1 && i % 2 == 0) {
        c = shiftedAlphabet1.charAt(idx);
        sb.setCharAt(i, c);
      } else if (idx != -1 && i % 2 == 1) { // second and every other
        c = shiftedAlphabet2.charAt(idx);
        sb.setCharAt(i, c);
      }
    }
    return sb.toString();
  }

  public String decrypt(String input) {
    StringBuilder sb = new StringBuilder(input);
    for (int i = 0; i < sb.length(); i++) {
      char c = sb.charAt(i);
      int idx = alphabet.indexOf(c);
      // first and every other
      if (idx != -1 && i % 2 == 0) {
        c = reShiftedAlphabet1.charAt(idx);
        sb.setCharAt(i, c);
      } else if (idx != -1 && i % 2 == 1) { // second and every other
        c = reShiftedAlphabet2.charAt(idx);
        sb.setCharAt(i, c);
      }
    }
    return sb.toString();
  }

}
