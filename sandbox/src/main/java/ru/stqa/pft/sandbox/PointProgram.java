package ru.stqa.pft.sandbox;

public class PointProgram {

  public static void main(String[] args) {

    Point p1 = new Point(3.0, 0.0);

    System.out.println("Расстояние между р1 и р2 " + p1.distance( new Point(5.0, 0.0) ));
  }
}
