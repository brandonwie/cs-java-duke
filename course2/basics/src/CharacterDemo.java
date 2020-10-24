public class CharacterDemo {
  public void digitTest() {
    String test = "ABCabc0123456789';#!";
    for (int i = 0; i < test.length(); i++) {
      char ch = test.charAt(i);
      if (Character.isDigit(ch)) {
        System.out.println(ch + " is a digit");
      }
      if (Character.isAlphabetic(ch)) {
        System.out.println(ch + " is alphabetic");
      }
      if (ch == '#') {
        System.out.println(ch + " #hashtag");
      }
    }
  }

  public void conversionTest() {
    String test = "ABCDEFabcdef123!#";
    for (int i = 0; i < test.length(); i++) {
      char ch = test.charAt(i);
      char uch = Character.toUpperCase(ch);
      char lch = Character.toLowerCase(ch);
      System.out.println(ch + " " + uch + " " + lch);
    }
  }
}
