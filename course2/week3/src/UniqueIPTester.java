/**
 * @author Seokhyun Wie
 * @version 11/02/2020
 */
public class UniqueIPTester {
  public void testUniqIP() {
    LogAnalyzer la = new LogAnalyzer();
    la.readFile("short-test_log");
    int uniqueIPs = la.countUniqueIPs();
    System.out.println("There are " + uniqueIPs + " IPs");
  }
}
