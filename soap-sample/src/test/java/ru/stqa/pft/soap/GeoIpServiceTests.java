package ru.stqa.pft.soap;

import com.lavasoft.GeoIPService;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class GeoIpServiceTests {

  @Test
  public void testMyIp() {
    String ipLocation = new GeoIPService().getGeoIPServiceSoap12().getIpLocation("188.242.131.96");
    assertTrue(ipLocation.contains("RU"));
  }

  @Test
  public void testInvalidMyIp() {
    String ipLocation = new GeoIPService().getGeoIPServiceSoap12().getIpLocation("188.242.131.xxx");
    assertTrue(ipLocation.contains("RU"));
  }
}
