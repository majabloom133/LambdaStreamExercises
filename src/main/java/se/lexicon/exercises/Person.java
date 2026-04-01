package se.lexicon.exercises;

public class Person {

    // Fields (variables)
    private String name;
    private int age;
    private String city;
    private boolean active;

    // Constructor
    public Person(String name, int age, String city, boolean active) {
        this.name = name;
        this.age = age;
        this.city = city;
        this.active = active;
    }

    // Getters
    public String getName() {
        return name;
    }
    public int getAge() {
        return age;
    }
    public String getCity() {
        return city;
    }
    public boolean isActive() {
        return active;
    }

    // toString - to print person in the terminal
    @Override
    public String toString() {
        return "Person{" +
                "name ='" + name + '\'' +
                ", age=" + age +
                ", city='" + city + '\'' +
                ", active=" + active +
                '}';
    }
}
