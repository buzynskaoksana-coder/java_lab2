//3

import java.util.Locale;
import java.util.Objects;
import java.util.Scanner;

public class Balloon {
    private int x;
    private int y;
    private int z;
    private int radius;

    private static int balloonCount;
    private static final double EPS = 1e-9;

    {
        this.x = 0;
        this.y = 0;
        this.z = 0;
        this.radius = 1;
        balloonCount++;
    }

    public Balloon() {
    }

    public Balloon(int x, int y, int z, int radius) {
        if (radius <= 0) {
            throw new IllegalArgumentException("Radius must be positive");
        }
        this.x = x;
        this.y = y;
        this.z = z;
        this.radius = radius;
    }

    public Balloon(Balloon other) {
        if (other != null) {
            this.x = other.x;
            this.y = other.y;
            this.z = other.z;
            this.radius = other.radius;
        }
    }

    public static int getBalloonCount() {
        return balloonCount;
    }

    public static double sphereVolume(double r) {
        return (4.0 / 3.0) * Math.PI * r * r * r;
    }

    public double volume() {
        return sphereVolume(radius);
    }

    public double intersectionVolume(Balloon other) {
        double d = Math.sqrt(
                Math.pow((double) this.x - other.x, 2) +
                Math.pow((double) this.y - other.y, 2) +
                Math.pow((double) this.z - other.z, 2)
        );
        double r1 = this.radius;
        double r2 = other.radius;

        if (d >= r1 + r2) {
            return 0.0;
        }
        if (d <= Math.abs(r1 - r2) + EPS) {
            double rMin = Math.min(r1, r2);
            return sphereVolume(rMin);
        }

        double part1 = Math.pow(r1 + r2 - d, 2);
        double part2 = d * d + 2 * d * (r1 + r2) - 3 * (r1 * r1 + r2 * r2) + 6 * r1 * r2;
        return (Math.PI / (12.0 * d)) * part1 * part2;
    }

    public double unionVolume(Balloon other) {
        return this.volume() + other.volume() - this.intersectionVolume(other);
    }

    @Override
    public String toString() {
        return String.format(Locale.US, "Balloon(center=(%d, %d, %d), r=%d)", x, y, z, radius);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Balloon balloon)) return false;
        return x == balloon.x && y == balloon.y && z == balloon.z && radius == balloon.radius;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, z, radius);
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        System.out.print("Enter 6 integers (x1 y1 r1 x2 y2 r2): ");
        int x1 = in.nextInt();
        int y1 = in.nextInt();
        int r1 = in.nextInt();
        int x2 = in.nextInt();
        int y2 = in.nextInt();
        int r2 = in.nextInt();

        Balloon b1 = new Balloon(x1, y1, 0, r1);
        Balloon b2 = new Balloon(x2, y2, 0, r2);
        Balloon b3 = new Balloon(b1);

        System.out.println("b1: " + b1);
        System.out.println("b2: " + b2);
        System.out.printf(Locale.US, "b1 volume: %.4f%n", b1.volume());
        System.out.printf(Locale.US, "b2 volume: %.4f%n", b2.volume());
        System.out.printf(Locale.US, "Intersection volume: %.4f%n", b1.intersectionVolume(b2));
        System.out.printf(Locale.US, "Union volume: %.4f%n", b1.unionVolume(b2));
        System.out.println("b1 equals b3: " + b1.equals(b3));
        System.out.println("Total balloons: " + Balloon.getBalloonCount());

        in.close();
    }
}