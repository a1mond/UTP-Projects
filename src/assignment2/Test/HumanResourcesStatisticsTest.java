package assignment2.Test;

import assignment2.Employees.Employee;
import assignment2.Employees.Manager;
import assignment2.Employees.Trainee;
import assignment2.Employees.Worker;
import assignment2.HumanResourcesStatistics;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

public class HumanResourcesStatisticsTest {
    private Manager d1;
    private Worker w1, w2, w3, w4, w5, w6, w7, w8, w9, w10;
    private Trainee t1, t2, t3, t4, t5, t6;
    private Manager m1, m2, m3;

    private LinkedList<Employee> d1sub;

    private LinkedList<Employee> m1sub;
    private LinkedList<Employee> m2sub;
    private LinkedList<Employee> m3sub;


    // Create a HR structure which resembles the below one:
    //
    // Director (an instance of Manager class (Director does not have a manager)
    //   |- Manager01
    //        |- Worker
    //        |- Worker
    //        |- Trainee
    //        |- ...
    //   |- Manager02
    //        |- ...
    //   |- ...
    //   |- Worker
    //   |- Worker
    //   |- Trainee

    private List<Employee> _allEmployees; // all employees ---  i.e. Workers, Trainees and their Managers and top Director (also an instance of Manager class)

    @Test
    public void payroll() {
        HumanResourcesStatistics.payroll(_allEmployees);
    }

    @Test
    public void subordinatesPayroll() {
        HumanResourcesStatistics.subordinatesPayroll(null);
    }

    @Test
    public void bonusTotal() {
        BigDecimal total = HumanResourcesStatistics.bonusTotal(_allEmployees);
        Assert.assertEquals(new BigDecimal("14844"), total);
    }

    @SuppressWarnings("Duplicates")
    @Before
    public void init() {
        d1 = new Manager("Dyadya", "Poshchovski", LocalDate.of(1950,7,21),
                new BigDecimal(20000), null, LocalDate.of(1980,7,12), new BigDecimal(10000), d1sub);

        m1 = new Manager("Naya", "Pacheco", LocalDate.of(1987,5,20),
                new BigDecimal(3490), d1, LocalDate.of(2020,5,21), new BigDecimal(300), m1sub);
        m2 = new Manager("Katerina", "Benjamin", LocalDate.of(1951,6,4),
                new BigDecimal(5500), d1, LocalDate.of(2019,1,18), new BigDecimal(550), m2sub);
        m3 = new Manager("Laura", "Giles", LocalDate.of(1994,1,15),
                new BigDecimal(3500), d1, LocalDate.of(2016,8,12), new BigDecimal(800), m3sub);

        w1 = new Worker("Bear", "Marshall", LocalDate.of(1956,2,25),
                new BigDecimal(3954), m1, LocalDate.of(2019,5,21), new BigDecimal(200));
        w2 = new Worker("Ayden", "Walton", LocalDate.of(1970,5,25),
                new BigDecimal(1133), m2, LocalDate.of(2018,5,12), new BigDecimal(334));
        w3 = new Worker("Missy", "Lozano", LocalDate.of(1985,7,27),
                new BigDecimal(1122), m2, LocalDate.of(2015,5,6), new BigDecimal(385));
        w4 = new Worker("Demi-Lee", "Mills", LocalDate.of(1989,12,17),
                new BigDecimal(3772), m3, LocalDate.of(2017,5,1), new BigDecimal(177));
        w5 = new Worker("Johnnie", "Milne", LocalDate.of(1976,5,14),
                new BigDecimal(2312), m3, LocalDate.of(2013,5,2), new BigDecimal(66));
        w6 = new Worker("Jazmyn", "Amos", LocalDate.of(1976,6,18),
                new BigDecimal(1559), m1, LocalDate.of(2018,5,5), new BigDecimal(468));
        w7 = new Worker("Jayde", "Farrow", LocalDate.of(1999,7,11),
                new BigDecimal(1877), m1, LocalDate.of(2019,5,10), new BigDecimal(384));
        w8 = new Worker("Farrow", "Lee", LocalDate.of(1987,10,15),
                new BigDecimal(1268), m2, LocalDate.of(2020,5,28), new BigDecimal(391));
        w9 = new Worker("Arianna", "Cresswell", LocalDate.of(1987,4,14),
                new BigDecimal(2470), m2, LocalDate.of(2020,5,21), new BigDecimal(451));
        w10 = new Worker("Yanis", "Stevenson", LocalDate.of(1950,1,17),
                new BigDecimal(3462), m3, LocalDate.of(2013,5,13), new BigDecimal(338));

        t1 = new Trainee("Kie", "Lowry", LocalDate.of(1999,2,10),
                new BigDecimal(1711), m1, LocalDate.of(2019,3,12), 60);
        t2 = new Trainee("Oran", "Riley", LocalDate.of(1996,5,9),
                new BigDecimal(1894), m1, LocalDate.of(2020,6,16), 120);
        t3 = new Trainee("Ridwan", "Kirkpatrick", LocalDate.of(1989,3,6),
                new BigDecimal(1352), m3, LocalDate.of(2020,2,26), 365);
        t4 = new Trainee("Borys", "Harding", LocalDate.of(1995,9,25),
                new BigDecimal(1534), m1, LocalDate.of(2018,8,11), 365);
        t5 = new Trainee("Fionn", "Orozco", LocalDate.of(1991,10,30),
                new BigDecimal(1715), m2, LocalDate.of(2017,12,10), 100);
        t6 = new Trainee("Elise", "Marsh", LocalDate.of(1990,12,10),
                new BigDecimal(1794), m2, LocalDate.of(2020,7,17), 30);

        d1sub = new LinkedList<>();
        d1sub.add(m1);
        d1sub.add(m2);
        d1sub.add(m3);

        m1sub = new LinkedList<>();
        m1sub.add(w1);
        m1sub.add(w6);
        m1sub.add(w7);
        m1sub.add(t1);
        m1sub.add(t2);
        m1sub.add(t4);

        m2sub = new LinkedList<>();
        m2sub.add(w2);
        m2sub.add(w3);
        m2sub.add(w8);
        m2sub.add(w9);
        m2sub.add(t5);
        m2sub.add(t6);

        m3sub = new LinkedList<>();
        m3sub.add(w4);
        m3sub.add(w5);
        m3sub.add(w10);
        m3sub.add(t3);

        _allEmployees = new LinkedList<>();
        _allEmployees.add(d1);
        _allEmployees.add(m1);
        _allEmployees.add(m2);
        _allEmployees.add(m3);
        _allEmployees.addAll(m1sub);
        _allEmployees.addAll(m2sub);
        _allEmployees.addAll(m3sub);
    }
}