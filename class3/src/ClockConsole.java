public class ClockConsole implements Observer {
    @Override
    public void update(Object changed) {
        System.out.printf("Tick " + changed);
    }
}
