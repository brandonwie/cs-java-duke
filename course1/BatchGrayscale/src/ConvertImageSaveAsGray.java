import edu.duke.*;
import java.io.*;

public class ConvertImageSaveAsGray {
  public ImageResource makeGray(ImageResource inImage) {
    // make a blank image of the same size
    ImageResource outImage = new ImageResource(inImage.getWidth(), inImage.getHeight());
    // for each pixel in outImage
    for (Pixel pixel : outImage.pixels()) {
      // look at the corresponding pixel in inImage
      Pixel inPixel = inImage.getPixel(pixel.getX(), pixel.getY());
      // compute inPixel's red + inPixel's green + inPixel's blue
      int average = (inPixel.getRed() + inPixel.getGreen() + inPixel.getBlue()) / 3;
      // set pixel's colors to average
      pixel.setRed(average);
      pixel.setGreen(average);
      pixel.setBlue(average);
    }
    // outImage is the answer
    return outImage;
  }

  public void convertAndSave() {
    DirectoryResource dr = new DirectoryResource();
    for (File f : dr.selectedFiles()) {
      ImageResource inFile = new ImageResource(f);
      ImageResource gray = makeGray(inFile);
      String fname = inFile.getFileName();
      String newName = "copy-" + fname;
      gray.setFileName(newName);
      gray.draw();
      gray.save();
    }
  }
}