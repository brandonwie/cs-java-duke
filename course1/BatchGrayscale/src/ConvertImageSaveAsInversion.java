import edu.duke.*;
import java.io.*;

public class ConvertImageSaveAsInversion {
  public ImageResource makeInversion(ImageResource inImage) {
    // make a blank image of the same size
    ImageResource outImage = new ImageResource(inImage.getWidth(), inImage.getHeight());
    // for each pixel in outImage
    for (Pixel pixel : outImage.pixels()) {
      // look at the corresponding pixel in inImage
      Pixel inPixel = inImage.getPixel(pixel.getX(), pixel.getY());
      // invert inPixel's red + inPixel's green + inPixel's blue
      int red = inPixel.getRed();
      int green = inPixel.getGreen();
      int blue = inPixel.getBlue();
      int invertedRed = invertColor(red);
      int invertedGreen = invertColor(green);
      int invertedBlue = invertColor(blue);
      // set pixel's colors to average
      pixel.setRed(invertedRed);
      pixel.setGreen(invertedGreen);
      pixel.setBlue(invertedBlue);
    }
    // outImage is the answer
    return outImage;
  }

  public int invertColor(int color) {
    int inverted = Math.abs(255 - color);
    return inverted;
  }

  public void convertAndSave() {
    DirectoryResource dr = new DirectoryResource();
    for (File f : dr.selectedFiles()) {
      ImageResource inFile = new ImageResource(f);
      ImageResource invert = makeInversion(inFile);
      String fname = inFile.getFileName();
      String newName = "copy-" + fname;
      invert.setFileName(newName);
      invert.draw();
      invert.save();
    }
  }
}
