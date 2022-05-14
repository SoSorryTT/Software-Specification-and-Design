import java.time.LocalDate;

public class Person {
    private String name;
    private LocalDate birthday;

    public Person(String name, LocalDate birthday) {
        this.name = name;
        this.birthday = birthday;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void greet() {
        System.out.println("Hello, " + name);
    }

    @Override
    public String toString() {
        return "A person named " + this.name;
    }

    public  static void main(String[] args) {
        Person p = new Person("Ping", null);
        System.out.println(p);
        p.greet();
    }
}
