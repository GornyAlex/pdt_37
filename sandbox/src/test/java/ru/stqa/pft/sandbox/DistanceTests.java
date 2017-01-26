package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by Alexander Gorny on 1/25/2017.
 */
public class DistanceTests {

  @Test
  public void testDistance(){

    Point p1 = new Point(0, 0);
    Point p2 = new Point(60, 80);

    Assert.assertEquals(p1.distance(p2), 100.0);

    p2.x = 30;
    p2.y = 40;
    Assert.assertEquals(p1.distance(p2), 50.0);

    p1.x = -2.3;
    p1.y = 4;
    p2.x = 8.5;
    p2.y = 0.7;
    Assert.assertEquals(p1.distance(p2), 11.292918134831227);

    p1.x = -25;
    p1.y = -1;
    p2.x = -25;
    p2.y = -1;
    Assert.assertEquals(p1.distance(p2), 0.0);

    p1.x = -25;
    p1.y = -1;
    p2.x = -25;
    p2.y = -1;
    Assert.assertEquals(p1.distance(p2), 0.0);

    p1.x = 67;
    p1.y = 13;
    p2.x = 67;
    p2.y = 1313;
    Assert.assertEquals(p1.distance(p2), 1300.0);

  }

}
