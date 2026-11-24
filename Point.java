//1

import java.util.Locale;
import java.util.Objects;
import java.util.Scanner;

public class Point {
    private double x;
    private double y;

    private static int pointCount;
    public static final double DEFAULT_COORD = 0.0;

    {
        this.x = DEFAULT_COORD;
        this.y = DEFAULT_COORD;
        pointCount++;
    }

    public Point() {
    }

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Point(Point other) {
        if (other != null) {
            this.x = other.x;
            this.y = other.y;
        }
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public static int getPointCount() {
        return pointCount;
    }

    public void moveX(double dx) {
        this.x += dx;
    }

    public void moveY(double dy) {
        this.y += dy;
    }

    public void move(double dx, double dy) {
        this.x += dx;
        this.y += dy;
    }

    public double distanceToOrigin() {
        return Math.hypot(x, y);
    }

    public double distanceTo(Point other) {
        return Math.hypot(this.x - other.x, this.y - other.y);
    }

    public static double distanceBetween(Point p1, Point p2) {
        return p1.distanceTo(p2);
    }

    public double[] toPolar() {
        double r = Math.hypot(x, y);
        double theta = Math.atan2(y, x);
        return new double[]{r, theta};
    }

    public boolean coincidesWith(Point other) {
        return this.equals(other);
    }

    public boolean differsFrom(Point other) {
        return !this.equals(other);
    }

    @Override
    public String toString() {
        return String.format(Locale.US, "Point(%.2f, %.2f)", x, y);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Point point)) return false;
        return Double.compare(point.x, x) == 0 && Double.compare(point.y, y) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in).useLocale(Locale.US);

        Point p1 = new Point();
        System.out.print("Enter x and y for second point: ");
        double x = in.nextDouble();
        double y = in.nextDouble();
        Point p2 = new Point(x, y);
        Point p3 = new Point(p2);

        System.out.print("Enter dx and dy to move second point: ");
        double dx = in.nextDouble();
        double dy = in.nextDouble();
        p2.moveX(dx);
        p2.moveY(dy);

        System.out.println("p1: " + p1);
        System.out.println("p2: " + p2);
        System.out.printf(Locale.US, "Distance p2 to origin: %.4f%n", p2.distanceToOrigin());
        System.out.printf(Locale.US, "Distance p1 to p2: %.4f%n", p1.distanceTo(p2));
        System.out.printf(Locale.US, "Static distance p1, p2: %.4f%n", Point.distanceBetween(p1, p2));
        double[] polar = p2.toPolar();
        System.out.printf(Locale.US, "Polar p2: r=%.4f, theta=%.4f%n", polar[0], polar[1]);
        System.out.println("p2 coincidesWith p3: " + p2.coincidesWith(p3));
        System.out.println("p1 differsFrom p2: " + p1.differsFrom(p2));
        System.out.println("p2 equals p3: " + p2.equals(p3));
        System.out.println("Total points: " + Point.getPointCount());

        in.close();
    }
}