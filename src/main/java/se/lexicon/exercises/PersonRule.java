package se.lexicon.exercises;
// Functional interface - only has 1 abstract method
public interface PersonRule {
    // Method that takes Person and returns true or false
    boolean test(Person person);
}
