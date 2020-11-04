import edu.duke.*;

public class App {
  public static void main(String[] args) throws Exception {

    // String encrypted = cc.encrypt(input);
    // String decrypted = cc.decrypt(encrypted);
    // System.out.println(encrypted);
    // System.out.println(decrypted);

    // CaesarCracker cc = new CaesarCracker('a');
    // String result = cc.decrypt(input);
    // System.out.println(result);

    // int[] rome = { 17, 14, 12, 4 };
    // VigenereCipher vc = new VigenereCipher(rome);
    // String encrypted = vc.encrypt(input);
    // System.out.println(encrypted);

    VigenereBreaker vb = new VigenereBreaker();
    // FileResource fr = new FileResource();
    // String input = fr.asString();
    // int[] keys = vb.tryKeyLength(input, 45, 'e');
    // for (int key : keys) {
    // System.out.println(key);
    // }
    // vb.breakVigenereAllLang();
    vb.breakVigenere();
  }
}
