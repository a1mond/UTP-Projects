package assignment6.University.Subject;

import assignment6.People.Teacher.Teacher;
import assignment6.University.Department.Department;

public class Subject {
    private final String name;
    private final Department supervisor;
    private final Teacher lecturer;

    public Subject(String name, Department supervisor, Teacher lecturer) {
        this.name = name;
        this.supervisor = supervisor;
        this.lecturer = lecturer;
    }

    public String getName() {
        return name;
    }

    public Department getSupervisor() {
        return supervisor;
    }

    public Teacher getLecturer() {
        return lecturer;
    }
}
