package assignment6.University.StudentGroup;

import assignment6.People.Student.Student;

import java.util.List;
import java.util.Objects;

public class StudentGroup implements Comparable<StudentGroup> {
    private final String name;
    private List<Student> studentList;

    public StudentGroup(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public List<Student> getStudentList() {
        return studentList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StudentGroup)) return false;
        StudentGroup that = (StudentGroup) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(studentList, that.studentList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, studentList);
    }

    @Override
    public int compareTo(StudentGroup o) {
        int result;
        result = this.name.compareTo(o.getName());
        if (result == 0)
            return Integer.compare(studentList.size(), o.getStudentList().size());
        return result;
    }
}
