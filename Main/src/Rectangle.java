public class Rectangle implements Shape {
    public double width;
    public double height;

    public Rectangle(double width, double height) {
        this.width = width;
        this.height = height;
    }

    public double getArea() {
        double Area = width*height;
        return Area;
    }
}
