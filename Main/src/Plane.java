public class Plane implements Flyable {
    @Override
    public void takeOff() {
        System.out.println("Plane is taking off");
    }

    @Override
    public void fly() {
        System.out.println("Plane is flying");
    }

    @Override
    public void land() {
        System.out.println("Plane is landing");
    }
}
