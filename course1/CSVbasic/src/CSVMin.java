import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class CSVMin {
  public CSVRecord coldestHourInFile(CSVParser parser) {
    CSVRecord coldestSoFar = null;
    for (CSVRecord currentRow : parser) {
      coldestSoFar = getSmallestOfTwo(currentRow, coldestSoFar);
      if (coldestSoFar == null) {
        coldestSoFar = currentRow;
      } else {
        double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
        double coldestTemp = Double.parseDouble(coldestSoFar.get("TemperatureF"));
        if (currentTemp < coldestTemp) {
          coldestSoFar = currentRow;
        }
      }
    }
    return coldestSoFar;
  }

  // public CSVRecord coldestInManyDays() {
  // CSVRecord coldestSoFar = null;
  // DirectoryResource dr = new DirectoryResource();
  // for (File f : dr.selectedFiles()) {
  // FileResource fr = new FileResource(f);
  // CSVRecord currentRow = coldestHourInFile(fr.getCSVParser());
  // coldestSoFar = getSmallestOfTwo(currentRow, coldestSoFar);
  // }
  // return coldestSoFar;
  // }

  public CSVRecord getSmallestOfTwo(CSVRecord currentRow, CSVRecord coldestSoFar) {
    if (coldestSoFar == null) {
      coldestSoFar = currentRow;
    } else {
      double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
      double coldestTemp = Double.parseDouble(coldestSoFar.get("TemperatureF"));
      if (currentTemp < coldestTemp) {
        coldestSoFar = currentRow;
      }
    }
    return coldestSoFar;
  }

  public String fileWithColdestTemperature() {
    CSVRecord coldestSoFar = null;
    String coldestFile = null;
    DirectoryResource dr = new DirectoryResource();
    for (File f : dr.selectedFiles()) {
      FileResource fr = new FileResource(f);
      CSVRecord currentRow = coldestHourInFile(fr.getCSVParser());
      if (coldestSoFar == null) {
        coldestSoFar = currentRow;
      } else {
        double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
        double coldestTemp = Double.parseDouble(coldestSoFar.get("TemperatureF"));
        if (currentTemp == -9999) {
          continue;
        }
        if (currentTemp < coldestTemp) {
          coldestSoFar = currentRow;
          coldestFile = f.getPath();
        } else {
          continue;
        }
      }
    }
    return coldestFile;
  }

  public void testFileWithColdestTemperature() {
    FileResource fr = new FileResource();
    CSVParser parser = fr.getCSVParser();
    CSVRecord coldestRow = coldestHourInFile(parser); // find coldest temp
    double coldestTemperature = Double.parseDouble(coldestRow.get("TemperatureF"));
    System.out.println("Coldest temperature on that day was " + coldestTemperature);
  }

  public void testManyFilesWithColdestTemperature() {
    String coldestFilePath = fileWithColdestTemperature(); // return coldest file path
    int lastSlashIndex = coldestFilePath.lastIndexOf("/");
    String coldestFileName = coldestFilePath.substring(lastSlashIndex + 1); // get file name
    FileResource fr = new FileResource(coldestFilePath);
    CSVParser parser = fr.getCSVParser();
    CSVRecord coldestRow = coldestHourInFile(parser); // find coldest temp
    double coldestTemperature = Double.parseDouble(coldestRow.get("TemperatureF"));
    System.out.println("Coldest day was in file " + coldestFileName);
    System.out.println("Coldest temperature on that day was " + coldestTemperature);
    System.out.println("All the Temperatures on the coldest day were:");
  }

  public void testColdestHourInFile() {
    FileResource fr = new FileResource();
    CSVRecord coldest = coldestHourInFile(fr.getCSVParser());
    System.out.println(coldest.get("DateUTC") + ": " + coldest.get("TemperatureF"));
  }

  // public void testColdestInManyDays() {
  // CSVRecord coolest = coldestInManyDays();
  // System.out.println("coldest temperature was " + coolest.get("TemperatureF") +
  // " at " + coolest.get("DateUTC"));
  // }

  public CSVRecord lowestHumidityInFile(CSVParser parser) {
    CSVRecord lowestSoFar = null;
    for (CSVRecord rec : parser) {
      try {
        if (lowestSoFar == null) {
          lowestSoFar = rec;
          continue;
        }
        int currentHumidity = Integer.parseInt(rec.get("Humidity"));
        int lowestHumidity = Integer.parseInt(lowestSoFar.get("Humidity"));
        if (lowestHumidity > currentHumidity) {
          lowestSoFar = rec;
        } else {
          continue;
        }
      } catch (Exception e) {
        // TODO: handle exception
        System.out.println(e.getMessage());
        continue;
      }
    }
    return lowestSoFar;
  }

  public CSVRecord lowestHumidityInManyFiles() {
    DirectoryResource dr = new DirectoryResource();
    CSVRecord lowestSoFar = null;
    for (File f : dr.selectedFiles()) {
      FileResource fr = new FileResource(f);
      CSVParser parser = fr.getCSVParser();
      CSVRecord currLowest = lowestHumidityInFile(parser);
      if (lowestSoFar == null) {
        lowestSoFar = currLowest;
      } else {
        int currentHumidity = Integer.parseInt(currLowest.get("Humidity"));
        int lowestHumidity = Integer.parseInt(lowestSoFar.get("Humidity"));
        if (lowestHumidity > currentHumidity) {
          lowestSoFar = currLowest;
        } else {
          continue;
        }
      }
    }
    return lowestSoFar;
  }

  public double averageTemperatureInFile(CSVParser parser) {
    double totalTemp = 0;
    int count = 0;
    for (CSVRecord rec : parser) {
      double temp = Double.parseDouble(rec.get("TemperatureF"));
      totalTemp += temp;
      count++;
    }
    double averageTemp = totalTemp / count;
    return averageTemp;
  }

  public double averageTemperatureWithHighHumidityInFile(CSVParser parser, int value) {
    int count = 0;
    double tempSum = 0;
    double averageTemp;

    for (CSVRecord rec : parser) {
      double humidity = Double.parseDouble(rec.get("Humidity"));
      double temperature = Double.parseDouble(rec.get("TemperatureF"));
      if (humidity >= value) {
        count++;
        tempSum += temperature;
      } else {
        continue;
      }
    }
    if (count == 0) {
      averageTemp = 0;
    } else {
      averageTemp = tempSum / count;
    }
    return averageTemp;
  }

  public void testLowestHumidityInFile() {
    FileResource fr = new FileResource();
    CSVParser parser = fr.getCSVParser();
    CSVRecord csv = lowestHumidityInFile(parser);
    System.out.println("Lowest Humidity was " + csv.get("Humidity") + " at " + csv.get("DateUTC"));
  }

  public void testLowestHumidityInManyFiles() {
    CSVRecord rec = lowestHumidityInManyFiles();
    System.out.println("Lowest Humidity was " + rec.get("Humidity") + " at " + rec.get("DateUTC"));
  }

  public void testAverageTemperatureInFile() {
    FileResource fr = new FileResource();
    CSVParser parser = fr.getCSVParser();
    double averageTemp = averageTemperatureInFile(parser);
    System.out.println("Average temperature in file is " + averageTemp);
  }

  public void testAverageTemperatureWithHighHumidityInFile() {
    FileResource fr = new FileResource();
    CSVParser parser = fr.getCSVParser();
    double averageTemp = averageTemperatureWithHighHumidityInFile(parser, 80);
    ;
    if (averageTemp == 0) {
      System.out.println("No temperatures with that humidity");
    } else {
      System.out.println("Average Temp when high Humidity is " + averageTemp);
    }
  }
}
