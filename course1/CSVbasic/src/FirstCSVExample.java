import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import edu.duke.FileResource;

public class FirstCSVExample {
  public void readFood() {
    FileResource fr = new FileResource("csv/foods.csv");
    CSVParser parser = fr.getCSVParser();

    for (CSVRecord record : parser) {
      System.out.print(record.get("Name") + " ");
      System.out.print(record.get("Favorite Food") + " ");
      System.out.println(record.get("Favorite Color"));
    }
  }
}
