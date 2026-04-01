package se.lexicon.exercises;

// Functional interface (blueprint) for an action
@FunctionalInterface
public interface PersonAction {
    // Single method that all actions must have:
    void execute(Person person);
}


/*
// Functional Interface for performing actions on specific people
public interface PersonAction {
    // Method that performs operation on the person
    void execute(Person person);
}
*/