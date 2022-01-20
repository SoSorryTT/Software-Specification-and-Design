public class Person {
    public static int PEOPLE_COUNT = 0;

    private String name;
    private double height;
    private double weight;
    
    public Person(String name, double height, double weight) {
        this.name = name;
        this.height = height;
        this.weight = weight;
        PEOPLE_COUNT++;
    }

    public String getName() {
        return name;
    }

    public double getHeight() {
        return height;
    }

    public double getWeight() {
        return weight;
    }

    public double getBMI() {
        double BMI = weight / ((height/100)*(height/100));
        return BMI;
    }
}
