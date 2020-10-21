import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import edu.duke.FileResource;

public class ParsingExportData {

  public String countyInfo(CSVParser parser, String country) {
    for (CSVRecord record : parser) {
      String countryFound = record.get("Country");
      if (countryFound.equals(country)) {
        String exports = record.get("Exports");
        String value = record.get("Value (dollars)");
        System.out.println(countryFound + ": " + exports + ": " + value);
        return countryFound + ": " + exports + ": " + value;
      } else {
        continue;
      }
    }
    return "NOT FOUND";
  }

  public void listExportersTwoProducts(CSVParser parser, String exportItem1, String exportItem2) {
    for (CSVRecord record : parser) {
      String exportItem = record.get("Exports");
      if (exportItem.contains(exportItem1) && exportItem.contains(exportItem2)) {
        String country = record.get("Country");
        System.out.println(country);
      } else {
        continue;
      }
    }
  }

  public int numberOfExporters(CSVParser parser, String exportItem) {
    int numOfExporters = 0;
    for (CSVRecord record : parser) {
      String exportItems = record.get("Exports");
      if (exportItems.contains(exportItem)) {
        numOfExporters++;
      } else {
        continue;
      }
    }
    System.out.println(numOfExporters);
    return numOfExporters;
  }

  public void bigExporters(CSVParser parser, String amount) {
    for (CSVRecord record : parser) {
      String value = record.get("Value (dollars)");
      if (value.length() > amount.length()) {
        String country = record.get("Country");
        System.out.println(country + " " + value);
      }
    }
  }

  public void tester(String filePath) {
    FileResource fr = new FileResource(filePath);
    CSVParser parser = fr.getCSVParser();
    // countyInfo(parser, "Nauru");
    // listExportersTwoProducts(parser, "cotton", "flowers");
    // numberOfExporters(parser, "cocoa");
    bigExporters(parser, "$999,999,999,999");
  }
}
