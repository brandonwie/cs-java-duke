public class App {
  public static void main(String[] args) throws Exception {
    // CountForLoop fl = new CountForLoop();
    // String result1 = fl.reverseFor("who are you");
    // String result2 = fl.reverseWhile("who are you");
    // System.out.println(result1);
    // System.out.println(result2);

    // CharacterDemo cd = new CharacterDemo();
    // cd.digitTest();
    // cd.conversionTest();

    // Breaking two-key encryption using an eyeball method
    // CaesarCipher cc = new CaesarCipher();
    // String quiz8 = cc.encryptTwoKeys("Top ncmy qkff vi vguv vbg ycpx", 24, 65);
    // System.out.println(quiz8);
    // String result = cc
    // .encryptTwoKeys("At noon be in the conference room with your hat on for a
    // surprise party. YELL LOUD!", 8, 21);
    // System.out.println(result);
    // String result = cc.encrypt("At noon be in the conference room with your hat
    // on for a surprise party. YELL LOUD!",
    // 15);
    // System.out.println(result);
    // WordPlay wp = new WordPlay();
    // String result = wp.emphasize("dna ctgaaactga", 'a');
    // System.out.println(result);
    // DiceRolling dr = new DiceRolling();
    // dr.simulate(1000);
    // CommonWords cw = new CommonWords();
    // cw.countShakespeare();
    // WordLengths wl = new WordLengths();
    // wl.testCountWordLengths();
    // CaesarBreaker cb = new CaesarBreaker();
    // cb.testDecrypt();

    // Graded Quiz
    CaesarCipher cc = new CaesarCipher(15);
    String answer1 = cc.encrypt("Can you imagine life WITHOUT the internet AND computers in your pocket?");
    System.out.println(answer1);

  }
}
