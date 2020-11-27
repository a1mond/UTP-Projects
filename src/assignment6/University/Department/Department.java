package assignment6.University.Department;

import assignment6.People.Teacher.Teacher;

import java.util.List;
import java.util.Objects;

public class Department implements Comparable<Department> {
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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Department)) return false;
        Department that = (Department) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(list, that.list);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, list);
    }

    @Override
    public int compareTo(Department o) {
        int result;
        result = name.compareTo(o.getName());
        if (result == 0)
            return Integer.compare(list.size(), o.getList().size());
        return result;
    }
}
