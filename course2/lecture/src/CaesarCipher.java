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
          newChar = Character.toLowerCase(newChar);
          encrypted.setCharAt(i, newChar);
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
    int key = 15;
    FileResource fr = new FileResource();
    String message = fr.asString();
    String encrypted = encrypt(message, key);
    System.out.println(encrypted);
    String decrypted = encrypt(encrypted, (26 - key));
    System.out.println(decrypted);
  }

  public String encryptTwoKeys(String input, int key1, int key2) {
    StringBuilder sb = new StringBuilder(input);
    for (int i = 0; i < sb.length(); i++) {
      char ch = sb.charAt(i);
      String chToStr = Character.toString(ch);
      if (i % 2 == 0) {
        String oddEncrypt = encrypt(chToStr, key1);
        char encryptedCh = oddEncrypt.charAt(0);
        sb.setCharAt(i, encryptedCh);
      } else {
        String evenEncrypt = encrypt(chToStr, key2);
        char encryptedCh = evenEncrypt.charAt(0);
        sb.setCharAt(i, encryptedCh);
      }
    }
    return sb.toString();
  }

  /**
   * Using For Loops to break two-key Caesar Cipher using eyeball method
   */
  public void decryptCipherWithTwoKeys() {
    FileResource fr = new FileResource();
    String file = fr.asString();
    int mostCountedE = 0;
    int iIndex = 0;
    int jIndex = 0;
    String maxEStr = "";
    for (int i = 0; i < 26; i++) {
      for (int j = 0; j < 26; j++) {
        String s = encryptTwoKeys(file, i, j);
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
