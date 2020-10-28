public class WordPlay {
  public boolean isVowel(char ch) {
    String vowel = "AEIOU";
    if (Character.isLowerCase(ch)) {
      ch = Character.toUpperCase(ch);
    }
    int chIndex = vowel.indexOf(ch);
    if (chIndex != -1) {
      return true;
    } else {
      return false;
    }
  }

  public String replaceVowels(String phrase, char ch) {
    StringBuilder sb = new StringBuilder(phrase);
    for (int i = 0; i < phrase.length(); i++) {
      char c = phrase.charAt(i);
      boolean vowelCheck = isVowel(c);
      if (vowelCheck == true) {
        sb.setCharAt(i, ch);
      }
    }
    return sb.toString();
  }

  public String emphasize(String phrase, char ch) {
    StringBuilder sb = new StringBuilder(phrase);
    for (int i = 0; i < sb.length(); i++) {
      char c = sb.charAt(i);
      boolean vowelCheck = isVowel(c);
      if (vowelCheck == true && (i % 2 == 0)) {
        System.out.println("odd position: " + i);
        sb.setCharAt(i, '*');
      } else if (vowelCheck == true && (i % 2 == 1)) {
        System.out.println("even position: " + i);
        sb.setCharAt(i, '+');
      }
    }
    return sb.toString();
  }
}
