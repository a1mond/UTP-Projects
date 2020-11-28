package assignment6.Generator;

import assignment6.People.Person.Enum.Nationality;
import assignment6.People.Person.Enum.Sex;
import assignment6.People.Student.Student;
import assignment6.People.Student.StudentC;
import assignment6.People.Teacher.Enum.AcademicDegree;
import assignment6.People.Teacher.Teacher;
import assignment6.People.Teacher.TeacherC;
import assignment6.University.Department.Department;
import assignment6.University.Department.DepartmentC;
import assignment6.University.StudentGroup.StudentGroup;
import assignment6.University.StudentGroup.StudentGroupC;
import assignment6.University.Subject.Subject;
import assignment6.University.Subject.SubjectC;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.*;

public class DataGenerator {
    private static final File FNAME_FILE = new File("src/assignment6/Data/firstname.data"),
                             LNAME_FILE = new File("src/assignment6/Data/lastname.data");

    public static final int STUDENT_COUNTER = 100,
                            TEACHER_COUNTER = 12,
                            DEPARTMENT_COUNTER = 4,
                            DEPARTMENT_TEACHER_COUNTER = 3,
                            SUBJECT_COUNTER = 10,
                            STUDENT_GROUP_COUNTER = 10,
                            STUDENT_GROUP_STUDENTS_COUNTER = 10;

    private static final int[] checkSumArr = {1, 3, 7, 9, 1, 3, 7, 9, 1, 3};

    public static StudentC generateStudents() {
        StudentC studentList = new StudentC();

        try {
            Map<Integer, String> firstNames = readFile(FNAME_FILE);
            Map<Integer, String> lastNames = readFile(LNAME_FILE);

            for (int i = 0; i < STUDENT_COUNTER; i++) {
                studentList.add(new Student(
                        getRandomPesel(),
                        firstNames.get((int)(Math.random() * firstNames.size())),
                        lastNames.get((int)(Math.random() * lastNames.size())),
                        Sex.random(),
                        LocalDate.of(
                                getRandom("year"),
                                getRandom("month"),
                                getRandom("day")),
                        Nationality.random(),
                        getRandom("bookno")
                ));
            }

        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
        return studentList;
    }
    public static TeacherC generateTeachers() {
        TeacherC teacherList = new TeacherC();
        try {
            Map<Integer, String> firstNames = readFile(FNAME_FILE);
            Map<Integer, String> lastNames = readFile(LNAME_FILE);


            for (int i = 0; i < TEACHER_COUNTER; i++) {
                LocalDate localDate = LocalDate.of(
                        getRandom("year"),
                        getRandom("month"),
                        getRandom("day"));
                Sex sex = Sex.random();
                teacherList.add(new Teacher(
                        getRandomPesel(),
                        firstNames.get((int) (Math.random() * firstNames.size())),
                        lastNames.get((int) (Math.random() * lastNames.size())),
                        sex,
                        localDate,
                        Nationality.random(),
                        AcademicDegree.random(),
                        LocalDate.of(
                                getRandom("hire-year"),
                                getRandom("month"),
                                getRandom("day")
                        )
                ));
            }
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
        return teacherList;
    }
    public static StudentGroupC generateStudentGroups(StudentC students) {
        int counter = 0;
        StudentGroupC groups = new StudentGroupC();
        for (int i = 0; i < STUDENT_GROUP_COUNTER; i++) {
            List<Student> tmp = new LinkedList<>();
            for (int j = 0; j < STUDENT_GROUP_STUDENTS_COUNTER; j++) {
                tmp.add(students.get(counter));
                counter++;
            }
            groups.add(new StudentGroup(
                    getRandom("group") + "-" + getRandomName(2),
                    tmp
            ));
        }
        return groups;
    }
    public static DepartmentC generateDepartments(TeacherC teachers) {
        int counter = 0;
        DepartmentC departments = new DepartmentC();
        for (int i = 0; i < DEPARTMENT_COUNTER; i++) {
            List<Teacher> tmp = new LinkedList<>();
            for (int j = 0; j < DEPARTMENT_TEACHER_COUNTER; j++) {
                tmp.add(teachers.get(counter));
                counter++;
            }
            departments.add(new Department(
                    getRandom("dep") + "-" + getRandomName(2) + "-D",
                    tmp
            ));
        }
        return departments;
    }
    public static SubjectC generateSubjects(TeacherC teachers, DepartmentC departments) {
        SubjectC subjects = new SubjectC();
        for (int i = 0; i < SUBJECT_COUNTER; i++) {
            subjects.add(new Subject(
                    getRandomName(3),
                    departments.get((int) (Math.random() * departments.size())),
                    teachers.get((int) (Math.random() * teachers.size()))
            ));
        }
        return subjects;
    }
    private static Map<Integer, String> readFile(File file) throws FileNotFoundException {
        Map<Integer, String> map = new HashMap<>();
        int counter = 1;

        Scanner sc = new Scanner(file);
        while (sc.hasNextLine()) {
            map.put(counter, sc.nextLine());
            counter++;
        }
        return map;
    }
    private static int getRandom(String type) {
        return switch (type) {
            case "year" -> (int) (Math.random() * 30) + 1970;
            case "month" -> (int) (Math.random() * 12) + 1;
            case "day" -> (int) (Math.random() * 27) + 1;
            case "bookno" -> (int) (Math.random() * 10000) + 10000;
            case "hire-year" -> (int) (Math.random() * 19) + 2000;
            case "group" -> (int) (Math.random() * 20) + 1;
            case "dep" -> (int) (Math.random() * 20) + 1;
            default -> 0;
        };
    }
    private static String getRandomName(int bound) {
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

        StringBuilder sb = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < bound; i++) {
            int index = random.nextInt(alphabet.length());
            char rand = alphabet.charAt(index);
            sb.append(rand);
        }
        return sb.toString();
    }
    private static long getPesel (LocalDate localdate, Sex sex) {
        return Long.parseLong(getFormatedDate(localdate) + getRandomZZZ() + sex.ordinal());
    }
    private static String getFormatedDate(LocalDate localDate) {
        return String.valueOf(localDate.getYear()).substring(3, 4) + localDate.getMonthValue() + localDate.getDayOfMonth();
    }
    private static int getRandomZZZ() {
        return 100 + new Random().nextInt(899);
    }
    private static int getCheckSum() {

    }
}
