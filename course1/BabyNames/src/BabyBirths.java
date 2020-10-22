
/**
 * Write a description of Project here.
 *
 * @author Neo Wie
 * @version (a version number or a date)
 */

import edu.duke.DirectoryResource;
import edu.duke.FileResource;
import java.io.File;
import org.apache.commons.csv.*;

public class BabyBirths {

  public void printNames() {
    FileResource fr = new FileResource();
    for (CSVRecord rec : fr.getCSVParser(false)) {
      int numBorn = Integer.parseInt(rec.get("2"));
      if (numBorn <= 100) {
        System.out.println("Name" + rec.get(0) + " Gender " + rec.get(1) + " Num Born " + rec.get(2));
      }
    }
  }

  public void totalBirths(FileResource fr) {
    int totalBirths = 0;
    int totalBoys = 0;
    int totalGirls = 0;
    int numOfBoysName = 0;
    int numOfGirlsName = 0;

    for (CSVRecord rec : fr.getCSVParser(false)) {
      int numBorn = Integer.parseInt(rec.get(2));
      totalBirths += numBorn;
      if (rec.get(1).equals("M")) {
        totalBoys += numBorn;
        numOfBoysName++;
      } else {
        totalGirls += numBorn;
        numOfGirlsName++;
      }
    }
    System.out.println("total births = " + totalBirths);
    System.out.println("total girls = " + totalGirls);
    System.out.println("total boys = " + totalBoys);
    System.out.println("number of girls name: " + numOfGirlsName);
    System.out.println("number of boys name: " + numOfBoysName);
  }

  /**
   *
   * @param year   to find file name
   * @param name
   * @param gender
   * @return the rank of the name of the year
   */
  public int getRank(int year, String name, String gender) {
    String filePath = "us_babynames/us_babynames_by_year/yob" + year + ".csv";
    FileResource fr = new FileResource(filePath);
    CSVParser parser = fr.getCSVParser(false);
    int rank = 0;
    for (CSVRecord rec : parser) {
      if (rec.get(1).equals(gender)) {
        rank++;
      }
      if (rec.get(0).equals(name) && rec.get(1).equals(gender)) {
        return rank;
      }
    }
    return -1;
  }

  public String getName(int year, int rank, String gender) {
    String filePath = "us_babynames/us_babynames_by_year/yob" + year + ".csv";
    FileResource fr = new FileResource(filePath);
    CSVParser parser = fr.getCSVParser(false);
    int prevRank = 0;
    for (CSVRecord rec : parser) {
      if (rec.get(1).equals(gender)) {
        prevRank++;
      }
      if (prevRank == rank) {
        return rec.get(0);
      }
    }
    return "NO NAME";
  }

  public String whatIsNameYear(String name, int year, int newYear, String gender) {
    // get rank of given name
    int rank = getRank(year, name, gender);
    // get name of newYear matches the rank that is same rank of the year
    String getNewName = getName(newYear, rank, gender);
    return name + " born in " + year + " would be " + getNewName + " if " + gender + " was born in " + newYear;
  }

  public int yearOfHighestRank(String name, String gender) {
    int highestRankYear = -1;
    int highestRank = Integer.MAX_VALUE;
    DirectoryResource dr = new DirectoryResource();
    for (File f : dr.selectedFiles()) {
      String fileName = f.getName();
      String yearStr = fileName.substring(3, 7);
      int year = Integer.parseInt(yearStr);
      int rank = getRank(year, name, gender);
      if (rank != -1 && rank < highestRank) {
        highestRank = rank;
        highestRankYear = year;
      } else {
        continue;
      }
    }
    return highestRankYear;
  }

  public double getAverageRank(String name, String gender) {
    double totalRank = 0;
    int fileCount = 0;
    DirectoryResource dr = new DirectoryResource();
    for (File f : dr.selectedFiles()) {
      String fileName = f.getName();
      String yearStr = fileName.substring(3, 7);
      int year = Integer.parseInt(yearStr);
      int rank = getRank(year, name, gender);
      totalRank += rank;
      fileCount++;
    }
    return totalRank / fileCount;
  }

  public int getTotalBirthsRankedHigher(int year, String name, String gender) {
    String filePath = "us_babynames/us_babynames_by_year/yob" + year + ".csv";
    FileResource fr = new FileResource(filePath);
    CSVParser parser = fr.getCSVParser(false);
    int totalBirth = 0;
    for (CSVRecord rec : parser) {
      if (rec.get(1).equals(gender)) {
        if (rec.get(0).equals(name)) {
          break;
        } else {
          totalBirth += Integer.parseInt(rec.get(2));
        }
      }
    }
    return totalBirth;
  }

}
