package ru.stqa.pft.sandbox;

/**
 * Created by aliak on 1/19/2017.
 */
public class Point {

  public double x;
  public double y;

  public Point(double x, double y){
    this.x = x;
    this.y = y;
  }

  double distanceBetweenPoints(double x, double y) {
    return Math.sqrt(Math.pow(this.x - x, 2) + Math.pow(this.y - y, 2));
  }

  double distance(Point p){
    return distanceBetweenPoints(p.x, p.y);
  }
}
