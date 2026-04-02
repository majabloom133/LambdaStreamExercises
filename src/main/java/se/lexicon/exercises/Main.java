package se.lexicon.exercises;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.function.Consumer;

        public class Main {
            public static void main(String[] args) {
                // Initial List
                List<Person> people = List.of(
                        new Person("Amina", 22, "Stockholm", true),
                        new Person("Erik", 17, "Uppsala", true),
                        new Person("Noah", 34, "Stockholm", false),
                        new Person("Sara", 29, "Göteborg", true),
                        new Person("Lina", 41, "Malmö", false),
                        new Person("Omar", 19, "Stockholm", true)
                );

                // --- LAMBDA RULES ---

                PersonRule isActive = (p) -> p.isActive();
                PersonRule isAdult = (p) -> p.getAge() >= 18;
                PersonRule livesInStockholm = (p) -> p.getCity().equals("Stockholm");
                PersonAction printName = (p) -> System.out.println(p.getName());
                PersonRule notActive = (p) -> !isActive.test(p);

                // Testing part 1
                System.out.println("People living in Stockholm:");
                List<Person> stockholmers = filterPeople(people, livesInStockholm);
                processPeople(stockholmers, printName);

                System.out.println("\nInactive People");
                List<Person> inactivePeople = filterPeople(people, notActive);
                processPeople(inactivePeople, printName);

                System.out.println("\n--- Testing with Predicate ---");
                List<Person> adultsWithPredicate = filterWithPredicate(people, p -> p.getAge() >= 18);
                processPeople(adultsWithPredicate, p -> System.out.println(p.getName() + " is an adult"));

                // Part 2:

                System.out.println();
                System.out.println("\n--- PART 2 ---");

                // Filter all active
                List<Person> activeStream = people.stream()
                        .filter(p -> p.isActive())
                        .toList();
                System.out.println("Number of active people (Via Stream): " + activeStream.size());

                // Map Names
                List<String> names = people.stream()
                        .map(p -> p.getName())
                        .toList();
                System.out.println("\nAll names: " + names);

                // Count adults
                long adultCount = people.stream()
                        .filter(p -> p.getAge() >= 18)
                        .count();
                System.out.println("\nAmount of adults: " + adultCount);

                // Get unique cities
                List<String> cities = people.stream()
                        .map(p -> p.getCity())
                        .distinct()
                        .sorted()
                        .toList();
                System.out.println("\nCities in the list: " + cities);

                // Sort by age
                List<Person> sortedByAge = people.stream()
                        .sorted((p1, p2) -> Integer.compare(p1.getAge(), p2.getAge()))
                        .toList();
                System.out.println("\nPeople sorted by age: ");
                sortedByAge.forEach(p -> System.out.println(" * " + p.getName() + " (" + p.getAge() + " years)"));
                System.out.println();

                // Find first active person in Stockholm
                // Use .ifPresent to only print IF I find someone.
                people.stream()
                        .filter(p -> p.isActive() && p.getCity().equals("Stockholm"))
                        .findFirst()
                        .ifPresent(p -> System.out.println("\nFirst active in Stockholm: " + p.getName()));

                // Mapping + Formatting
                List<String> formattedInfo = people.stream()
                        .map(p -> p.getName() + " (" + p.getAge() + ") - " + p.getCity())
                        .toList();

                System.out.println("\nFormatted info list: ");
                formattedInfo.forEach(line -> System.out.println(" - " + line));
            }

            // --- REUSABLE METHODS ---
            public static List<Person> filterPeople (List<Person> personList, PersonRule rule) {
                List<Person> result = new ArrayList<>();
                for (Person p : personList) {
                    if (rule.test(p)) {
                        result.add(p);
                    }
                }
                return result;
            }

            public static List<Person> filterWithPredicate(List<Person> personList, Predicate<Person> rule) {
                List<Person> result = new ArrayList<>();
                for (Person p : personList) {
                    if (rule.test(p)) {
                        result.add(p);
                    }
                }
                return result;
            }

            public static void processPeople(List<Person> personList, PersonAction action) {
                for (Person p : personList) {
                    action.execute(p);
                }
            }

        }
