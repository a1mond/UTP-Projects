package assignment6.People.Teacher;

import assignment6.People.Person.Enum.Nationality;
import assignment6.People.Person.Enum.Sex;
import assignment6.People.Person.Person;
import assignment6.People.Teacher.Enum.AcademicDegree;

import java.time.LocalDate;

public class Teacher extends Person {
    private final AcademicDegree academicDegree;
    private final LocalDate hireDate;

    public Teacher(long pesel, String firstName, String lastName, Sex sex, LocalDate birthDate, Nationality nationality, AcademicDegree academicDegree, LocalDate hireDate) {
        super(pesel, firstName, lastName, sex, birthDate, nationality);
        this.academicDegree = academicDegree;
        this.hireDate = hireDate;
    }

    public AcademicDegree getAcademicDegree() {
        return academicDegree;
    }

    public LocalDate getHireDate() {
        return hireDate;
    }
}
