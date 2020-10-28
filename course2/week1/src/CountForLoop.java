public class CountForLoop {
  public String reverseFor(String s) {
    String ret = "";
    for (int i = 0; i < s.length(); i++) {
      ret = s.charAt(i) + ret;
    }
    return ret;
  }

  public String reverseWhile(String s) {
    String ret = "";
    int i = 0;
    while (i < s.length()) {
      ret = s.charAt(i) + ret;
      i++;
    }
    return ret;
  }
}
