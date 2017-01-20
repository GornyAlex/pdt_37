package ru.stqa.pft.sandbox;

/**
 * Created by aliak on 1/19/2017.
 */
public class CalculateDistance {

  public static void main(String[] args) {

    Point p1 = new Point(1, 1);
    Point p2 = new Point(2, 2);

    System.out.println("Растояние между точками с координатами " + p1.x + "," + p1.y + " и " + p2.x + "," + p2.y + " равно " + distance(p1,p2));

  }

  public static double distance(Point p1, Point p2) {
    return Math.sqrt(Math.pow(p1.x - p2.x, 2) + Math.pow(p1.y - p2.y, 2));
  }

}

