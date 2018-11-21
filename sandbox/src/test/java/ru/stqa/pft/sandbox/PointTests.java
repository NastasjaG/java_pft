package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PointTests {

  @Test
  public void testDistance() {
    Point p1 = new Point(4.0, 0.0);

    Assert.assertEquals(p1.distance(new Point(7.0, 0.0)), 3.0);


  }
}
