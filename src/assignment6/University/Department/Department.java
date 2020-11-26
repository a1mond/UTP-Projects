package assignment6.University.Department;

import assignment6.People.Teacher.Teacher;

import java.util.List;

public class Department {
    private final String name;
    private final List<Teacher> list;

    public Department(String name, List<Teacher> list) {
        this.name = name;
        this.list = list;
    }

    public String getName() {
        return name;
    }

    public List<Teacher> getList() {
        return list;
    }
}
