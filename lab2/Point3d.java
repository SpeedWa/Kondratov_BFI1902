public class Point3d extends Point2d{

    protected double zCoord;

    public Point3d(double x, double y, double z) {
        this.xCoord = x;
        this.yCoord = y;
        this.zCoord = z;
    }

    public Point3d () {
        this (0, 0, 0);
    }

    public double getZ () {
        return zCoord;
    }

    public void setZ (double val) {
        zCoord = val;
    }

    public boolean isEqual3D (Point3d object) {
        return this.xCoord == object.xCoord && this.yCoord == object.yCoord && this.zCoord == object.zCoord;

    }
    public double distanceTo (Point3d object) {
        return Math.floor(Math.sqrt(Math.pow(object.xCoord - this.xCoord, 2) + Math.pow(object.yCoord - this.yCoord, 2) + Math.pow(object.zCoord - this.zCoord, 2)) * 100)/100;
    }
}

