public class App {
  public static void main(String[] args) throws Exception {
    // UniqueIPTester tester = new UniqueIPTester();
    // tester.testUniqIP();

    LogAnalyzer la = new LogAnalyzer();
    la.readFile("weblog1_log");
    int numUnique = la.countUniqueIPsInRange(300, 399);
    // System.out.println(numUnique);
    // la.printAllHigherThanNum(400);
    int uniqueSize = la.uniqueIPVisitsOnDay("Mar 17").size();
    System.out.println(uniqueSize);
    System.out.println(numUnique);
  }
}