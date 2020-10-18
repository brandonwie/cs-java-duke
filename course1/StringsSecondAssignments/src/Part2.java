
public class Part2 {
  public int howMany(String a, String b) {
    int startIndex = 0;
    int count = 0;
    while (true) {
      int aIndex = b.indexOf(a, startIndex);
      if (aIndex == -1) {
        break;
      } else {
        startIndex = aIndex + a.length();
        count++;
      }
    }
    return count;
  }

  public void testHowMany() {
    int result1 = howMany("GAA", "ATGAACGAATTGAATC");
    int result2 = howMany("AA", "ATAAAA");

    System.out.println(result1);
    System.out.println(result2);
  }
}
