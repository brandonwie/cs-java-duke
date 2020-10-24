public class App {
  public static void main(String[] args) throws Exception {
    // GrayScaleConverter gs = new GrayScaleConverter();
    // gs.testGray();
    // gs.selectAndConvert();

    // ConvertImageSaveAsGray gs = new ConvertImageSaveAsGray();
    // gs.convertAndSave();
    ConvertImageSaveAsInversion inv = new ConvertImageSaveAsInversion();
    inv.convertAndSave();
  }
}
