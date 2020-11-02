package assignment2and3.Employees;

import java.math.BigDecimal;
import java.time.LocalDate;

public abstract class Employee extends Person {

	//
	// attributes:
	// * salary (use BigDecimal type for representing currency values)
	// * manager (empty if at top of hierarchy)
	private BigDecimal _salary;

	private Manager _manager;
	
	protected Employee(String firstName, String lastName, LocalDate birthDate, BigDecimal salary, Manager manager) {
		super(firstName, lastName, birthDate);
		_salary = salary;
		_manager = manager;
	}

	public BigDecimal getSalary() {
		return _salary;
	}

	public Manager getManager() {
		return _manager;
	}
}