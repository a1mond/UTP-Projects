package assignment6.People.Student;

import assignment6.People.Person.Enum.Nationality;
import assignment6.People.Person.Enum.Sex;
import assignment6.People.Person.Person;

import java.time.LocalDate;

public class Student extends Person {
    private final long bookNum;

    public Student(long pesel, String firstName, String lastName, Sex sex, LocalDate birthDate, Nationality nationality, long bookNum) {
        super(pesel, firstName, lastName, sex, birthDate, nationality);
        this.bookNum = bookNum;
    }

    public long getBookNum() {
        return bookNum;
    }
}
