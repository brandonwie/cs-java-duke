import java.util.*;

public class CountTester {
  public void testCounts() {
    LogAnalyzer la = new LogAnalyzer();
    la.readFile("weblog2_log");

    // HashMap<String, ArrayList<String>> ipsPerDay = la.iPsForDays();
    // ArrayList<String> mostVisitedIp = la.iPsWithMostVisitsOnDay(ipsPerDay, "Mar
    // 17");
    // System.out.println(mostVisitedIp);

    // System.out.println(ips);
    // HashMap<String, Integer> counts = la.countVisitsPerIP();
    // int maxVisit = la.mostNumberVisitsByIP(counts);
    // System.out.println(maxVisit);

    // HashMap<String, Integer> visitsPerIP = la.countVisitsPerIP();
    // ArrayList<String> ips = la.iPsMostVisits(visitsPerIP);
    // System.out.println(ips);
    // String date = la.dayWithMostIPVisits(ipsPerDay);
    // System.out.println(date);

    // int count = la.countUniqueIPs();
    // System.out.println(count);
    // System.out.println(la.uniqueIPVisitsOnDay("Sep 27").size());
    // System.out.println(la.countUniqueIPsInRange(200, 299));
    // HashMap<String, Integer> visitsPerIP = la.countVisitsPerIP();
    // System.out.println(la.mostNumberVisitsByIP(visitsPerIP));
    // System.out.println(la.iPsMostVisits(visitsPerIP));

    // String date = la.dayWithMostIPVisits(la.iPsForDays());
    // System.out.println(date);
    System.out.println(la.iPsWithMostVisitsOnDay(la.iPsForDays(), "Sep 30"));
  }
}
