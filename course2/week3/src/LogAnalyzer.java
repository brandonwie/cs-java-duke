import java.util.*;
import edu.duke.*;

public class LogAnalyzer {
  private ArrayList<LogEntry> records;

  public LogAnalyzer() {
    records = new ArrayList<>();
  }

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
        System.out.println(ipAddr);
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
          System.out.println(ip);
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
