public class Point3d {

    private Double x;
    private Double y;
    private Double z;
    public Point3d (Double x, Double y, Double z) {
        if (x != null) {
            this.x = x;
        } else {
            this.x = 0.0;
        }
        if (y != null) {
            this.y = y;
        } else {
            this.y = 0.0;
        }
        if (z != null) {
            this.z = z;
        } else {
            this.z = 0.0;
        }
    }

    public void getX () {
        System.out.println("x = " + this.x);
    }
    public void getY () {
        System.out.println("y = " + this.y);
    }
    public void getZ () {
        System.out.println("z = " + this.z);
    }

    public void modify (Double x, Double y, Double z) {
        if (x != null) {
            this.x = x;
        }
        if (y != null) {
            this.y = y;
        }
        if (z != null) {
            this.z = z;
        }
    }
    public boolean isEqual (Point3d object) {
        if (this.x.equals(object.x) && this.y.equals(object.y) && this.z.equals(object.z)) {
            return true;
        } else {
            return false;
        }

    }
    public Double distanceTo (Point3d object) {
        return Math.floor(Math.sqrt(Math.pow(object.x - this.x, 2) + Math.pow(object.y - this.y, 2) + Math.pow(object.z - this.z, 2)) * 100)/100;
    }
}

