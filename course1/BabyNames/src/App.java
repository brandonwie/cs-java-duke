// import edu.duke.FileResource;

public class App {
  public static void main(String[] args) throws Exception {
    BabyBirths birthData = new BabyBirths();
    // int test = birthData.getRank(1971, "Frank", "M");
    // System.out.println(test);
    // String test1 = birthData.getName(1982, 450, "M");
    // System.out.println(test1);
    // String test2 = birthData.whatIsNameYear("Owen", 1974, 2014, "M");
    // System.out.println(test2);
    // int test3 = birthData.yearOfHighestRank("Mich", "M");
    // System.out.println(test3);
    // double test4 = birthData.getAverageRank("Robert", "M");
    // System.out.println(test4);
    int test5 = birthData.getTotalBirthsRankedHigher(1990, "Drew", "M");
    System.out.println(test5);
    // FileResource fr = new
    // FileResource("us_babynames/us_babynames_by_year/yob1905.csv");
    // birthData.totalBirths(fr);
  }
}