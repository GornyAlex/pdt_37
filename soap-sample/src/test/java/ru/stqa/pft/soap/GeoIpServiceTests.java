package ru.stqa.pft.soap;

import net.webservicex.GeoIP;
import net.webservicex.GeoIPService;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertEquals;

/**
 * Created by Alexander Gorny on 3/21/2017.
 */
public class GeoIpServiceTests {


  @Test
  public void testMyIp() {
    GeoIP geoIP = new GeoIPService().getGeoIPServiceSoap12().getGeoIP("4.16.98.158");
    assertEquals(geoIP.getCountryCode(), "USA");
  }

  @Test
  public void testInvalidIp() {
    GeoIP geoIP = new GeoIPService().getGeoIPServiceSoap12().getGeoIP("4.16.98.xxx");
    assertEquals(geoIP.getCountryCode(), "USA");
  }

}
