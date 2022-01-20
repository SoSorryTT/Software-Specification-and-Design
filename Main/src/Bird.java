public class Bird implements Flyable {
    @Override
    public void takeOff() {
        System.out.println("Bird is jumping off");
    }

    @Override
    public void fly() {
        System.out.println("Bird is flapping wing");
    }

    @Override
    public void land() {
        System.out.println("Bird is touching the ground");
    }
}