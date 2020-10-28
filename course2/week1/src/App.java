public class App {
  public static void main(String[] args) {
    // CaesarCipher cc = new CaesarCipher(15);
    // String q1 = cc.encrypt("Can you imagine life WITHOUT the internet AND
    // computers in your pocket?");
    // System.out.println(q1);

    CaesarCipherTwo cct = new CaesarCipherTwo(21, 8);

    String q6 = cct.encrypt("Can you imagine life WITHOUT the internet AND computers in your pocket?");
    System.out.println(q6);

    // WordLengths wl = new WordLengths();
    // wl.testCountWordLengths();

    // CaesarCipherTwo cct = new CaesarCipherTwo(1, 6);
    // cct.decryptCipherWithTwoKeys("Aal uttx hm aal Qtct Fhljha pl Wbdl.
    // Pvxvxlx!");

    // CaesarBreaker bcc = new CaesarBreaker();
    // bcc.decryptCipherWithTwoKeys();

  }
}
