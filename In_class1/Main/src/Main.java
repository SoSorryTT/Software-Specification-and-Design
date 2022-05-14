public class Main {
    public static void main(String[] args) {
        // int number = 3;

        // House bobHouse = new House("Bob's House", 3);
        // bobHouse.name = "Bob's House";
        // bobHouse.numFloor = 3;

        // House markHouse = new House("Mark's House", 2);
        // markHouse.name = "Mark's House";
        // markHouse.numFloor = 2;

        // System.out.println(bobHouse.name + " has " + bobHouse.numFloor + " floors");
        // System.out.println(markHouse.name + " has " + markHouse.numFloor + " floors");

        // Person Alice = new Person("Alice", 180, 60.5);
        // Person Second = new Person("Second", 170.5, 80.5);

        // System.out.println(Alice.getName()+" has BMI of "+Alice.getBMI()+".");
        // System.out.println(Second.getName()+" has BMI of "+Second.getBMI()+".");

        // System.out.println(Person.PEOPLE_COUNT);

        Rectangle rectangle1 = new Rectangle(10, 20);
        Rectangle rectangle2 = new Rectangle(100, 200);
        Rectangle rectangle3 = new Rectangle(5.5, 6.9);

        Triangle triangle1 = new Triangle(10, 20);
        Triangle triangle2 = new Triangle(100, 200);
        Triangle triangle3 = new Triangle(5.5, 6.9);

        // System.out.println("Total area of all objects are "+(
        //     rectangle1.getArea()+rectangle2.getArea()+rectangle3.getArea()+
        //     triangle1.getArea()+triangle2.getArea()+triangle3.getArea()));

        Circle circle1 = new Circle(10);
        Circle circle2 = new Circle(6);

        Shape [] shapes = { rectangle1, rectangle2, rectangle3, 
            triangle1, triangle2, triangle3, 
            circle1, circle2 };
        double totalArea = sumArea(shapes);
        System.out.println("Total area is " + totalArea);

        // public static double sumArea(Rectangle [] reacts, Triangle [] tris) {
        //     double totalArea = 0;
        //     for(Rectangle rect : reacts) {
        //         totalArea += rect.getArea();
        //     }
        //     for(Triangle tri : tris) {
        //         totalArea += tri.getArea();
        //     }
        //     return totalArea;
        // }
        // double totalArea = sumArea(Rectangle [] react, Triangle [] tri);
        // System.out.println("Total area is "+totalArea);
    }

    public static double sumArea(Shape [] shapes) {
        double totalArea = 0;
        for(Shape shape : shapes) {
            totalArea += shape.getArea();
        }
        return totalArea;
    }
}
