package ru.stqa.pft.sandbox;

/**
 * Created by Alexander Gorny on 1/31/2017.
 */
public class Equality {

  public static void main(String[] args){
    String s1 = "firefox";
    String s2 = new String(s1);

    System.out.println(s1 == s2);
    System.out.println(s1.equals(s2));
  }
}
