package se.lexicon.exercises;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.function.Consumer;



public class Main {
public static void main(String[] args) {
    // This is where the list is created
    List<Person> people = List.of(
            new Person("Amina", 22, "Stockholm", true),
            new Person("Erik", 17, "Uppsala", true),
            new Person("Noah", 34, "Stockholm", false),
            new Person("Sara", 29, "Göteborg", true),
            new Person("Lina", 41, "Malmö", false),
            new Person("Omar", 19, "Stockholm", true)
    );

    // --- LAMBDA RULES ---

    // Rule for active people
    PersonRule isActive = (p) -> p.isActive();

    // Rule for adults (18+) - Getter method + comparison operator
    PersonRule isAdult = (p) -> p.getAge() >= 18;

    // Rule for people who lives in Stockholm
    // .equals() when comparing Strings
    PersonRule livesInStockholm = (p) -> p.getCity().equals("Stockholm");

    // Prints persons name
    PersonAction printName = (p) -> System.out.println(p.getName());

    // Simulate sending email
    PersonAction sendEmail = (p) -> System.out.println("Send email to: " + p.getName());

    // Rule for active AND adult
    PersonRule activeAndAdult = (p) -> isActive.test(p) && isAdult.test(p);

    // Rule for adult OR lives in Stockholm
    PersonRule adultOrStockholm = (p) -> isAdult.test(p) || livesInStockholm.test(p);

    // Rule for NOT active (negation)
    PersonRule notActive = (p) -> !isActive.test(p);

    // Testing
    System.out.println("People living in Stockholm:");
    List<Person> stockholmers = filterPeople(people, livesInStockholm);
    processPeople(stockholmers, printName);
    // Testing who is not active
    System.out.println("\nInactive People:");
    List<Person> inactivePeople = filterPeople(people, notActive);
    processPeople(inactivePeople, printName);
}

// Method that filters
    // Takes list and a rule, gives back new list with the winners:
    public static List<Person> filterPeople(List<Person> personList, PersonRule rule) {
    List<Person> result = new ArrayList<>();

    // Loop through every person in the first list
        for (Person p : personList) {
            // Check if person follows the rule
            if (rule.test(p)) {
                result.add(p);
            }
        }
    return result;
    }

    public static List<Person> filterWithPredicate(List<Person> personList, Predicate<Person> rule) {
        List<Person> result = new ArrayList<>();
        for (Person p : personList) {
            // Predicate also uses method name .test()
            if (rule.test(p)) {
                result.add(p);
            }
        }
        return result;
    }

    public static void processPeople(List<Person> personList, PersonAction action) {
    // Iterate through each person in the list:
        for (Person p : personList) {
            // Call the method in PersonAction interface:
            action.execute(p);
        }
    }
}
