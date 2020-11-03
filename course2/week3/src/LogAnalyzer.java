
/**
 * Write a description of class LogAnalyzer here.
 *
 * @author Seokhyun Wie
 * @version 11/02/2020
 */
import java.util.*;
import edu.duke.*;

public class LogAnalyzer {
  private ArrayList<LogEntry> records;

  public LogAnalyzer() {
    records = new ArrayList<>();
  }

  /**
   *
   * @param fname
   * @return create <code>ArrayList of LogEntries</code> by reading line by line
   */
  public void readFile(String fname) {
    records.clear();
    String path = "data/" + fname;
    FileResource fr = new FileResource(path);
    for (String log : fr.lines()) {
      LogEntry logEntry = WebLogParser.parseEntry(log);
      records.add(logEntry);
    }
  }

  /**
   * Read LogEntry Records
   *
   * @return HashMap [IPs, Count]
   */
  public HashMap<String, Integer> countVisitsPerIP() {
    // <String IP, Integer count>
    HashMap<String, Integer> counts = new HashMap<>();
    for (LogEntry le : records) {
      String ip = le.getIpAddress();
      // check if ip is in counts
      // if (!counts.containsKey(ip)) {
      // counts.put(ip, 1);
      // } else {
      // int val = counts.get(ip);
      // counts.replace(ip, val + 1);
      // }
      // Java 8 feature
      counts.merge(ip, 1, Integer::sum);
    }
    return counts;
  }

  /**
   *
   * @param countsPerIP HashMap [IPs, Counts]
   * @return max count only
   */
  public int mostNumberVisitsByIP(HashMap<String, Integer> countsPerIP) {
    int max = 0;
    for (Integer count : countsPerIP.values()) {
      if (count > max)
        max = count;
    }
    return max;
  }

  /**
   *
   * @param ipCount HashMap<String IP, Integer Count>
   * @return most visited IPs
   */
  public ArrayList<String> iPsMostVisits(HashMap<String, Integer> ipCount) {
    ArrayList<String> ips = new ArrayList<>();
    int max = mostNumberVisitsByIP(ipCount);
    for (String ip : ipCount.keySet()) {
      if (ipCount.get(ip).equals(max)) {
        ips.add(ip);
      }
    }
    return ips;
  }

  /**
   * Read records(LogEntries)
   *
   * @return HashMap [String Date, ArrayList IPs] : IPs per Date
   */
  public HashMap<String, ArrayList<String>> iPsForDays() {
    HashMap<String, ArrayList<String>> ipsPerDay = new HashMap<>();
    for (LogEntry le : records) {
      String date = le.getAccessTime().toString();
      date = date.substring(4, 10);
      String ip = le.getIpAddress();
      if (ipsPerDay.containsKey(date)) {
        ipsPerDay.get(date).add(ip);
      } else {
        ArrayList<String> temp = new ArrayList<>();
        temp.add(ip);
        ipsPerDay.put(date, temp);
      }
    }
    return ipsPerDay;
  }

  /**
   *
   * @param ipsPerDay HashMap [String Date, ArrayList IPs]
   * @return Date with max length of value
   */
  public String dayWithMostIPVisits(HashMap<String, ArrayList<String>> ipsPerDay) {
    int max = 0;
    String maxDate = "";
    for (String date : ipsPerDay.keySet()) {
      int len = ipsPerDay.get(date).size();
      if (len > max) {
        max = len;
        maxDate = date;
      }
    }
    return maxDate;
  }

  /**
   *
   * @param ipsPerDay
   * @param day
   * @return IPs that visit the most on the day
   */
  public ArrayList<String> iPsWithMostVisitsOnDay(HashMap<String, ArrayList<String>> ipsPerDay, String day) {
    // new hashmap to count ips
    HashMap<String, Integer> counter = new HashMap<>();

    ArrayList<String> ips = ipsPerDay.get(day);
    for (String ip : ips) {
      if (counter.containsKey(ip)) {
        counter.put(ip, counter.get(ip) + 1);
      } else {
        counter.put(ip, 1);
      }
    }
    return iPsMostVisits(counter);
  }

  /**
   * create <code>ArrayList<String></code> with unique IPs and count it
   *
   * @return <code>Size</code> of uniqueIP ArrayList's length
   */
  public int countUniqueIPs() {
    ArrayList<String> uniqueIPs = new ArrayList<>();
    for (LogEntry le : records) {
      String ipAddr = le.getIpAddress();
      if (!uniqueIPs.contains(ipAddr)) {
        uniqueIPs.add(ipAddr);
      }
    }
    return uniqueIPs.size();
  }

  public void printAllHigherThanNum(int num) {
    for (LogEntry le : records) {
      int statusCode = le.getStatusCode();
      if (statusCode > num) {
        System.out.println(le);
      }
    }
  }

  /**
   *
   * @param someday must be <code>MMM DD</code> format
   * @return <code>ArrayList<String></code> of unique days
   */
  public ArrayList<String> uniqueIPVisitsOnDay(String someday) {
    ArrayList<String> visits = new ArrayList<>();
    for (LogEntry le : records) {
      Date getDate = le.getAccessTime();
      String fullDate = getDate.toString();
      String date = fullDate.substring(4, 10);
      String ipAddr = le.getIpAddress();
      if (date.equals(someday) && !visits.contains(ipAddr)) {
        visits.add(ipAddr);
      }
    }
    return visits;
  }

  public int countUniqueIPsInRange(int low, int high) {
    ArrayList<String> ips = new ArrayList<>();
    int total = 0;
    for (LogEntry le : records) {
      String ip = le.getIpAddress();
      int statusCode = le.getStatusCode();
      if (!ips.contains(ip)) {
        if (statusCode >= low && statusCode <= high) {
          ips.add(ip);
          total += 1;
        }
      }

    }
    return total;
  }

  public void printAll() {
    for (LogEntry le : records) {
      System.out.println(le);
    }
  }
}
