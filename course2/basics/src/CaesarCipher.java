import edu.duke.*;

public class CaesarCipher {
  public String encrypt(String input, int key) {
    // Make a StringBuilder with message (encrypted)
    StringBuilder encrypted = new StringBuilder(input);
    // Write down the alphabet
    String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    // Compute the shifted alphabet
    String shiftedAlphabet = alphabet.substring(key) + alphabet.substring(0, key);

    for (int i = 0; i < encrypted.length(); i++) {
      char currChar = encrypted.charAt(i);
      if (Character.isLowerCase(currChar)) {
        char currUpperChar = Character.toUpperCase(currChar);
        int idx = alphabet.indexOf(currUpperChar);
        if (idx != -1) {
          char newChar = shiftedAlphabet.charAt(idx);
          char originalChar = Character.toLowerCase(newChar);
          encrypted.setCharAt(i, originalChar);
        }
      } else if (Character.isUpperCase(currChar)) {
        int idx = alphabet.indexOf(currChar);
        if (idx != -1) {
          char newChar = shiftedAlphabet.charAt(idx);
          encrypted.setCharAt(i, newChar);
        }
      }
    }
    return encrypted.toString();
  }

  public void testCaesar() {
    int key = 19;
    FileResource fr = new FileResource();
    String message = fr.asString();
    String encrypted = encrypt(message, key);
    System.out.println(encrypted);
    String decrypted = encrypt(encrypted, (26 - key));
    System.out.println(decrypted);
  }
}
