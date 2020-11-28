import assignment6.Generator.DataGenerator;
import assignment6.People.Student.Student;
import assignment6.People.Student.StudentC;
import assignment6.People.Teacher.Teacher;
import assignment6.People.Teacher.TeacherC;
import assignment6.University.Department.DepartmentC;
import assignment6.University.StudentGroup.StudentGroupC;
import assignment6.University.Subject.SubjectC;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class GeneralTest {
    private StudentC students;
    private TeacherC teachers;
    private DepartmentC departments;
    private StudentGroupC studentGroups;
    private SubjectC subjects;

    @Before
    public void init() {
        students = DataGenerator.generateStudents();
        teachers = DataGenerator.generateTeachers();
        departments = DataGenerator.generateDepartments(teachers);
        studentGroups = DataGenerator.generateStudentGroups(students);
        subjects = DataGenerator.generateSubjects(teachers, departments);
    }

    @Test
    public void generateStudents() {
        Assert.assertEquals(DataGenerator.STUDENT_COUNTER, students.size());

        Student tmp = students.get(0);
        students.sort(Student::compareTo);
        Assert.assertNotSame(tmp, students.get(0));
    }
    @Test
    public void generateTeachers() {
        Assert.assertEquals(DataGenerator.TEACHER_COUNTER, teachers.size());

        Teacher tmp = teachers.get(0);
        teachers.sort(Teacher::compareTo);
        Assert.assertNotSame(tmp, teachers.get(0));
    }
    @Test
    public void generateStudentGroups() {
        Assert.assertEquals(DataGenerator.STUDENT_GROUP_COUNTER, studentGroups.size());
    }
    @Test
    public void generateDepartments() {
        Assert.assertEquals(DataGenerator.DEPARTMENT_COUNTER, departments.size());
    }
    @Test
    public void generateSubjects() {
        Assert.assertEquals(DataGenerator.SUBJECT_COUNTER, subjects.size());
    }
}
