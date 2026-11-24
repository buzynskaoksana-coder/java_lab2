//2

import java.util.Locale;
import java.util.Objects;
import java.util.Scanner;

public class Parallelogram {
    private double sideA;
    private double sideB;
    private double angleDegrees;

    private static int parallelogramCount;
    private static final double EPS = 1e-6;

    {
        this.sideA = 1.0;
        this.sideB = 1.0;
        this.angleDegrees = 90.0;
        parallelogramCount++;
    }

    public Parallelogram() {
    }

    public Parallelogram(double sideA, double sideB, double angleDegrees) {
        validate(sideA, sideB, angleDegrees);
        this.sideA = sideA;
        this.sideB = sideB;
        this.angleDegrees = angleDegrees;
    }

    public Parallelogram(Parallelogram other) {
        if (other != null) {
            this.sideA = other.sideA;
            this.sideB = other.sideB;
            this.angleDegrees = other.angleDegrees;
        }
    }

    private static void validate(double a, double b, double angle) {
        if (a <= 0 || b <= 0 || angle <= 0 || angle >= 180) {
            throw new IllegalArgumentException("Invalid parallelogram parameters");
        }
    }

    public static int getParallelogramCount() {
        return parallelogramCount;
    }

    public static double areaFromSidesAndAngle(double a, double b, double angleDegrees) {
        validate(a, b, angleDegrees);
        return a * b * Math.sin(Math.toRadians(angleDegrees));
    }

    public double perimeter() {
        return 2 * (sideA + sideB);
    }

    public double area() {
        return sideA * sideB * Math.sin(Math.toRadians(angleDegrees));
    }

    public boolean isSimilarTo(Parallelogram other) {
        if (other == null) return false;
        boolean sameAngle = Math.abs(this.angleDegrees - other.angleDegrees) < EPS;
        if (!sameAngle) return false;

        double r1 = this.sideA / other.sideA;
        double r2 = this.sideB / other.sideB;
        if (Math.abs(r1 - r2) < EPS) return true;

        double r1Swap = this.sideA / other.sideB;
        double r2Swap = this.sideB / other.sideA;
        return Math.abs(r1Swap - r2Swap) < EPS;
    }

    @Override
    public String toString() {
        return String.format(Locale.US, "Parallelogram(a=%.2f, b=%.2f, angle=%.2f°)", sideA, sideB, angleDegrees);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Parallelogram that)) return false;
        return Math.abs(that.sideA - sideA) < EPS
                && Math.abs(that.sideB - sideB) < EPS
                && Math.abs(that.angleDegrees - angleDegrees) < EPS;
    }

    @Override
    public int hashCode() {
        return Objects.hash(sideA, sideB, angleDegrees);
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in).useLocale(Locale.US);

        Parallelogram par1 = new Parallelogram();

        System.out.print("Enter side A, side B and angle (degrees) for first parallelogram: ");
        double a1 = in.nextDouble();
        double b1 = in.nextDouble();
        double ang1 = in.nextDouble();
        Parallelogram par2 = new Parallelogram(a1, b1, ang1);
        Parallelogram par3 = new Parallelogram(par2);

        System.out.print("Enter side A, side B and angle (degrees) for another parallelogram: ");
        double a2 = in.nextDouble();
        double b2 = in.nextDouble();
        double ang2 = in.nextDouble();
        Parallelogram par4 = new Parallelogram(a2, b2, ang2);

        System.out.println("par1: " + par1);
        System.out.println("par2: " + par2);
        System.out.printf(Locale.US, "par2 perimeter: %.4f, area: %.4f%n", par2.perimeter(), par2.area());
        System.out.printf(Locale.US, "Static area(a1, b1, ang1): %.4f%n", Parallelogram.areaFromSidesAndAngle(a1, b1, ang1));
        System.out.println("par2 similar to par4: " + par2.isSimilarTo(par4));
        System.out.println("par2 equals par3: " + par2.equals(par3));
        System.out.println("Total parallelograms: " + Parallelogram.getParallelogramCount());

        in.close();
    }
}