package tp;

import java.time.LocalDate;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class PersonService {

    public static List<Person> filterByAddress(String address) {
        List<Person> mockPersonsDatabase = Arrays.asList(
                Person.builder().firstName("Ahlam").familyName("Jaidi").birthDate(LocalDate.of(1990, 5, 12))
                        .address("123 Rue A").build(),
                Person.builder().firstName("Imane").familyName("Elabbadi").birthDate(LocalDate.of(2005, 10, 15))
                        .address("456 Rue B").build(),
                Person.builder().firstName("Karim").familyName("Hamdi").birthDate(LocalDate.of(1985, 3, 9))
                        .address("123 Rue A").build()
        );

        Predicate<Person> hasAddress = person -> person.getAddress().equals(address);

        return mockPersonsDatabase.stream()
                .filter(hasAddress)
                .collect(Collectors.toList());
    }

    public static List<Person> filterAdults() {
        List<Person> mockPersonsDatabase = Arrays.asList(
                Person.builder().firstName("Ahlam").familyName("Jaidi").birthDate(LocalDate.of(1990, 5, 12))
                        .address("123 Rue A").build(),
                Person.builder().firstName("Imane").familyName("Elabbadi").birthDate(LocalDate.of(2005, 10, 15))
                        .address("456 Rue B").build(),
                Person.builder().firstName("Karim").familyName("Hamdi").birthDate(LocalDate.of(1985, 3, 9))
                        .address("123 Rue A").build()
        );

        Predicate<Person> isAdult = person -> person.calculerAge() >= 18;

        return mockPersonsDatabase.stream()
                .filter(isAdult)
                .collect(Collectors.toList());
    }

    public static Set<Person> removeBobWithoutIterator() {
        Set<Person> people = new HashSet<>();
        people.add(Person.builder().firstName("Hind").familyName("Ali").build());
        people.add(Person.builder().firstName("Abdellah").familyName("Kenzi").build());
        people.add(Person.builder().firstName("Zouani").familyName("Charlie").build());

        // Collecter les éléments à supprimer pour éviter ConcurrentModificationException
        Set<Person> toRemove = people.stream()
                .filter(person -> person.getFamilyName().equals("Kenzi"))
                .collect(Collectors.toSet());

        people.removeAll(toRemove); // Supprimer les éléments collectés
        return people;
    }

    public static Set<Person> removeBobUsingIterator() {
        Set<Person> people = new HashSet<>();
        people.add(Person.builder().firstName("Imane").familyName("Elabbadi").build());
        people.add(Person.builder().firstName("Ahlam").familyName("Jaidi").build());
        people.add(Person.builder().firstName("Karim").familyName("Hamdi").build());

        // Utilisation de l'iterator pour une suppression sécurisée
        Iterator<Person> iterator = people.iterator();
        while (iterator.hasNext()) {
            Person person = iterator.next();
            if (person.getFamilyName().equals("Hamdi")) {
                iterator.remove(); // Suppression sécurisée
            }
        }
        return people;
    }
}
