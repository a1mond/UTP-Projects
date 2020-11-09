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

	public boolean isSalaryGreater(BigDecimal money) {
		return _salary.compareTo(money) > 0;
	}
	public boolean isSalarySmaller(BigDecimal money) {
		return _salary.compareTo(money) < 0;
	}
	public int compareSalary(BigDecimal money) {
		return _salary.compareTo(money);
	}

	public void setSalary(BigDecimal _salary) {
		this._salary = _salary;
	}

	public BigDecimal getSalary() {
		return _salary;
	}

	public Manager getManager() {
		return _manager;
	}
}