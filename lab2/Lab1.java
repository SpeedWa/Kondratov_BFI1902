import java.util.Scanner;

public class Lab1 {
    public static void main(String[] args) {

        String string;

        Scanner reader = new Scanner(System.in);

        System.out.println("Enter x1 coordinate (optionally):");
        string = reader.nextLine();
        Double x1 = inputValue(string);

        System.out.println("Enter y1 coordinate (optionally):");
        string = reader.nextLine();
        Double y1 = inputValue(string);

        System.out.println("Enter z1 coordinate (optionally):");
        string = reader.nextLine();
        Double z1 = inputValue(string);

        System.out.println("Enter x2 coordinate (optionally):");
        string = reader.nextLine();
        Double x2 = inputValue(string);

        System.out.println("Enter y2 coordinate (optionally):");
        string = reader.nextLine();
        Double y2 = inputValue(string);

        System.out.println("Enter z2 coordinate (optionally):");
        string = reader.nextLine();
        Double z2 = inputValue(string);

        System.out.println("Enter x3 coordinate (optionally):");
        string = reader.nextLine();
        Double x3 = inputValue(string);

        System.out.println("Enter y3 coordinate (optionally):");
        string = reader.nextLine();
        Double y3 = inputValue(string);

        System.out.println("Enter z3 coordinate (optionally):");
        string = reader.nextLine();
        Double z3 = inputValue(string);

        reader.close();

        Point3d point1 = new Point3d(x1, y1, z1);
        Point3d point2 = new Point3d(x2, y2, z2);
        Point3d point3 = new Point3d(x3, y3, z3);

        if (point1.isEqual(point2) == false && point2.isEqual(point3) == false && point3.isEqual(point1) == false) {
            System.out.println("Площадь треугольника на основе введенных точек: " + computeArea(point1, point2, point3));
        } else {
            System.out.println("Ошибка! Две или три точки треугольника имеют одинаковые координаты");
        }


    }

    public static Double inputValue (String string) {
        if (string.isEmpty()) {
            return null;
        } else {
            return Double.parseDouble(string);
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
