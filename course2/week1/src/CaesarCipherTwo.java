public class CaesarCipherTwo {
  private String alphabet;
  private String shiftedAlphabet1;
  private String shiftedAlphabet2;
  private int mainKey1, mainKey2;

  public CaesarCipherTwo(int key1, int key2) {
    alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    shiftedAlphabet1 = alphabet.substring(key1) + alphabet.substring(0, key1);
    shiftedAlphabet2 = alphabet.substring(key2) + alphabet.substring(0, key2);
    mainKey1 = key1;
    mainKey2 = key2;
  }

  public String encrypt(String input) {
    StringBuilder encrypted = new StringBuilder(input);
    for (int i = 0; i < encrypted.length(); i++) {
      char currChar = encrypted.charAt(i);
      int idx = alphabet.indexOf(Character.toUpperCase(currChar));

      if (idx != -1) {
        char newCh;
        if (i % 2 == 0) {
          newCh = shiftedAlphabet1.charAt(idx);
        } else {
          newCh = shiftedAlphabet2.charAt(idx);
        }

        if (Character.isUpperCase(currChar)) {
          encrypted.setCharAt(i, newCh);
        } else {
          encrypted.setCharAt(i, Character.toLowerCase(newCh));
        }
      }
    }
    return encrypted.toString();
  }

  public String decrypt(String input) {
    CaesarCipherTwo cct = new CaesarCipherTwo(26 - mainKey1, 26 - mainKey2);
    return cct.encrypt(input);
  }

  /**
   * customized solution
   */
  public void decryptCipherWithTwoKeys(String input) {
    CaesarCipherOld cc = new CaesarCipherOld();
    int mostCountedE = 0;
    int iIndex = 0;
    int jIndex = 0;
    String maxEStr = "";
    for (int i = 0; i < 26; i++) {
      for (int j = 0; j < 26; j++) {
        String s = cc.encryptTwoKeys(input, i, j);
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
