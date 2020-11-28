package assignment6.People.Person;

import assignment6.People.Person.Enum.Nationality;

import java.util.HashSet;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class PersonC extends HashSet<Person> {
    public HashSet<Person> filterByNationality(Nationality n) {
        Predicate<Person> filterByNationality = e -> e.getNationality().equals(n);

        return (HashSet<Person>) super
                .stream()
                .filter(filterByNationality)
                .collect(Collectors.toSet());
    }
}
