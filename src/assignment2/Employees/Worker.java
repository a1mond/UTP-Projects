package assignment2.Employees;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Worker extends Employee {

	// attributes
	// * employment date
	// * bonus
	private LocalDate _employmentDate;
	private BigDecimal _bonus;

	public Worker(String firstName, String lastName, LocalDate birthDate, BigDecimal salary, Manager manager,
				  LocalDate employmentDate, BigDecimal bonus) {
		super(firstName, lastName, birthDate, salary, manager);
		_employmentDate = employmentDate;
		_bonus = bonus;
	}
}