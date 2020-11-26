package assignment6.People.Person;

import assignment6.People.Person.Enum.Nationality;
import assignment6.People.Person.Enum.Sex;

import java.time.LocalDate;

public abstract class Person implements Comparable<Person> {
    private final long pesel;
    private final String firstName,
                         lastName;
    private final Sex sex;
    private final LocalDate birthDate;
    private final Nationality nationality;

    public Person(long pesel, String firstName, String lastName, Sex sex, LocalDate birthDate, Nationality nationality) {
        this.pesel = pesel;
        this.firstName = firstName;
        this.lastName = lastName;
        this.sex = sex;
        this.birthDate = birthDate;
        this.nationality = nationality;
    }

    public long getPesel() {
        return pesel;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public Nationality getNationality() {
        return nationality;
    }

    @Override
    public int compareTo(Person o) {
        int result;
        result = firstName.compareTo(o.firstName);
        if (result == 0) {
            result = lastName.compareTo(o.lastName);
            if (result == 0)
                return birthDate.compareTo(o.birthDate);
        }
        return result;
    }
}
