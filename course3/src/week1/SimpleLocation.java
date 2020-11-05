package week1;

public class SimpleLocation {
  private double latitude;
  private double longitude;

  public double getLatitude() {
    return this.latitude;
  }

  public double getLongitude() {
    return this.longitude;
  }

  public void setLatitude(double lat) {
    if (lat < -90 || lat > 90) {
      System.out.println("Illegal value for latitude");
    } else {
      this.latitude = lat;
    }
  }

  public void setLongitude(double lon) {
    if (lon < -180 || lon > 180) {
      System.out.println("Illegal value for longitude");
    } else {
      this.longitude = lon;
    }
  }

  // OVERLOAD CONSTRUCTOR
  // Member variables not shown
  public SimpleLocation() {
    this.latitude = 32.9;
    this.longitude = -117.2;
  }

  public SimpleLocation(double lat, double lon) {
    this.latitude = lat;
    this.longitude = lon;
  }

  // OVERLOAD METHOD
  public double distance(double otherLat, double otherLon) {
    return getDist(this.latitude, this.longitude, otherLat, otherLon);
  }

  public double distance(SimpleLocation other) {
    return getDist(this.latitude, this.longitude, other.latitude, other.longitude);
  }

  public double getDist(double lat, double lon, double otherLat, double otherLon) {
    double a = Math.pow(Math.abs(lat - otherLat), 2);
    double b = Math.pow(Math.abs(lon - otherLon), 2);
    double dist = Math.sqrt(a + b);
    return dist;
  }
}
