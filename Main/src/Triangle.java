public class Triangle implements Shape {
    public double width;
    public double height;

    public Triangle(double width, double height) {
        this.width = width;
        this.height = height;
    }

    public double getArea() {
        double Area = 0.5*width*height;
        return Area;
    }
}
