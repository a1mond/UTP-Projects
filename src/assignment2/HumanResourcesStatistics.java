package assignment2;

import assignment2.Employees.*;
import assignment2.Payroll.PayrollEntry;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public final class HumanResourcesStatistics {

	public static List<PayrollEntry> payroll(List<Employee> employees) {
		if (employees == null)
			return null;
		return employees
				.stream()
				.map(e -> new PayrollEntry(e, e.getSalary(), e.getClass() == Trainee.class ? BigDecimal.ZERO : ((Worker) e).getBonus()))
				.collect(Collectors.toList());
	}

	// payroll for all subordinates
	public static List<PayrollEntry> subordinatesPayroll(Manager manager) {
		if (manager == null)
			return null;
		return manager
				.getAllSubordinates()
				.stream()
				.map(e -> new PayrollEntry(e, e.getSalary(), e.getClass() == Trainee.class ? BigDecimal.ZERO : ((Worker) e).getBonus()))
				.collect(Collectors.toList());
	}

	public static BigDecimal bonusTotal(List<Employee> employees) {
		if (employees == null)
			return null;
		return employees
				.stream()
				.map(e -> e.getClass() == Trainee.class ? BigDecimal.ZERO : ((Worker) e).getBonus())
				.reduce(BigDecimal.ZERO, BigDecimal::add);
	}
	public static Employee getLongestSeniorityEmployee(List<Employee> employees) {
		if (employees == null)
			return null;
		return employees
				.stream()
				.filter(employee -> employee.getClass() != Trainee.class)
				.sorted(Comparator.comparing(e -> ((Worker) e).getEmploymentDate()))
				.collect(Collectors.toList())
				.get(0);
	}
	public static BigDecimal getHighestSalary(List<Employee> employees) {
		if (employees == null)
			return null;
		return employees
				.stream()
				.map(Employee::getSalary)
				.collect(Collectors.toList())
				.get(0);
	}
	public static BigDecimal getHighestSalaryWithBonus(List<Employee> employees) {
		if (employees == null)
			return null;
		return employees
				.stream()
				.map(e -> e.getClass() == Trainee.class ? e.getSalary() : e.getSalary().add(((Worker) e).getBonus()))
				.collect(Collectors.toList())
				.get(0);
	}
	public static List<Employee> getSurnameBeginWithA(List<Employee> employees) {
		if (employees == null)
			return null;
		return employees
				.stream()
				.filter(e -> e.getLastName().startsWith("A"))
				.collect(Collectors.toList());
	}
	public static List<Employee> moreThan1000(List<Employee> employees) {
		if (employees == null)
			return null;
		return employees
				.stream()
				.filter(e -> e.getSalary().intValue() > 1000)
				.collect(Collectors.toList());
	}

	/// ...
	// rest of the methods specified in the assignment description
	
	
	/**
	 * samples for functional processing in Java
	 * 
	 */
	public static List<Short> getAges(List<Employee> employees) {
		if (employees == null) {
			return null;
		}
		List<Short> ages = employees //
				.stream() //
				.map(emp -> emp.getAge()) //
				.collect(Collectors.toList());
		return ages;
	}

	public static void printAges(List<Employee> employees) {
		if (employees == null) {
			return;
		}
		employees //
				.stream() //
				.map(emp -> (int) emp.getAge()) //
				.forEach(age -> System.out.print(age + ", "));
	}

	//
	// average age for the Employees whose first name starts with 'A' and they are older than 20
	public static short getAverageAgeInline(List<Employee> employees) {
		if (employees == null) {
			return 0;
		}
		int employeeTotalAge = employees //
				.stream() //
				.filter(emp -> emp.getFirstName().startsWith("A") && emp.getAge() > 20) //
				.map(emp -> (int) emp.getAge()) //
				.reduce(0, //
						Integer::sum);

		long filteredEmployeesCount = employees //
				.stream() //
				.filter(emp -> emp.getFirstName().startsWith("A") && emp.getAge() > 20) //
				.count();

		return (short) (employeeTotalAge / filteredEmployeesCount);
	}

	public static short getAverageAgeMethodReference(List<Employee> employees) {
		if (employees == null) {
			return 0;
		}
		int employeeTotalAge = employees //
				.stream() //
				.map(emp -> (int) emp.getAge()) //
				.reduce(0, HumanResourcesStatistics::totalAge);
		return (short) (employeeTotalAge / employees.size());
	}

	public static short getMaxAgeInline(List<Employee> employees) {
		short employeeMaxAge = employees //
				.stream() //
				.map(Person::getAge) //
				.reduce((short) 0, //
						(maxAge, age) -> {
							if (maxAge < age) {
								return age;
							} else {
								return maxAge;
							}
						});
		return employeeMaxAge;
	}

	public static short getMaxAgeMethodReference(List<Employee> employees) {
		short employeeMaxAge = employees //
				.stream() //
				.map(emp -> emp.getAge()) //
				.reduce((short) 0, HumanResourcesStatistics::maxAge);
		return employeeMaxAge;
	}

	private static int totalAge(int totalAge, int age) {
		return totalAge + age;
	}

	private static short maxAge(short maxAge, short age) {
		if (maxAge < age) {
			return age;
		} else {
			return maxAge;
		}
	}
}