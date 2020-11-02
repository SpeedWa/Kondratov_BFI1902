import java.util.Scanner;

public class Lab1 {
    public static void main(String[] args) {

        String string;

        Scanner reader = new Scanner(System.in);

        System.out.println("Enter x1 coordinate:");
        string = reader.nextLine();
        double x1 = Double.parseDouble(string);

        System.out.println("Enter y1 coordinate:");
        string = reader.nextLine();
        double y1 = Double.parseDouble(string);

        System.out.println("Enter z1 coordinate:");
        string = reader.nextLine();
        double z1 = Double.parseDouble(string);

        System.out.println("Enter x2 coordinate:");
        string = reader.nextLine();
        double x2 = Double.parseDouble(string);

        System.out.println("Enter y2 coordinate:");
        string = reader.nextLine();
        double y2 = Double.parseDouble(string);

        System.out.println("Enter z2 coordinate:");
        string = reader.nextLine();
        double z2 = Double.parseDouble(string);

        System.out.println("Enter x3 coordinate:");
        string = reader.nextLine();
        double x3 = Double.parseDouble(string);

        System.out.println("Enter y3 coordinate:");
        string = reader.nextLine();
        double y3 = Double.parseDouble(string);

        System.out.println("Enter z3 coordinate:");
        string = reader.nextLine();
        double z3 = Double.parseDouble(string);

        reader.close();

        Point3d point1 = new Point3d(x1, y1, z1);
        Point3d point2 = new Point3d(x2, y2, z2);
        Point3d point3 = new Point3d(x3, y3, z3);

        if (!point1.isEqual3D(point2) && !point2.isEqual3D(point3) && !point3.isEqual3D(point1)) {
            System.out.println("Площадь треугольника на основе введенных точек: " + computeArea(point1, point2, point3));
        } else {
            System.out.println("Ошибка! Две или три точки треугольника имеют одинаковые координаты");
        }


    }

    public static Double computeArea(Point3d object1, Point3d object2, Point3d object3) {
        double a = object1.distanceTo(object2);
        double b = object2.distanceTo(object3);
        double c = object3.distanceTo(object1);
        double p = (a + b + c)/2;
        return Math.sqrt(p*(p-a)*(p-b)*(p-c));
    }
}
