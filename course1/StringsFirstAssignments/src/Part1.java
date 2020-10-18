import edu.duke.*;

public class Part1 {
  public void findYouTube() {
    URLResource url = new URLResource("https://www.dukelearntoprogram.com//course2/data/manylinks.html");
    for (String s : url.lines()) {
      String line = s.toLowerCase();
      int index = line.indexOf("youtube.com");
      if (index != -1) {
        int endQuote = line.indexOf("\"", index);
        if (endQuote != -1) {
          int startQuote = line.lastIndexOf("\"", endQuote - 1);
          if (startQuote != -1) {
            System.out.println(s.substring(startQuote + 1, endQuote));
          }
        }
      }
    }
  }

  public void test() {
    System.out.println("test starting..");
    findYouTube();
  }
}
