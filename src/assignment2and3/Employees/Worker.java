package assignment2and3.Employees;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Worker extends Employee {

	// attributes
	// * employment date
	// * bonus
	private final LocalDate _employmentDate;
	private final BigDecimal _bonus;
	private boolean hasBonus;

	public Worker(String firstName, String lastName, LocalDate birthDate, BigDecimal salary, Manager manager,
				  LocalDate employmentDate, BigDecimal bonus) {
		super(firstName, lastName, birthDate, salary, manager);
		_employmentDate = employmentDate;
		_bonus = bonus;
	}

	public int getMonthOfSen() {
		return (_employmentDate.getMonthValue() + ((_employmentDate.getYear() - LocalDate.now().getYear()) * 12));
	}

	public int getYearOFSen() {
		return _employmentDate.getYear() - LocalDate.now().getYear();
	}

	public boolean isSenGreaterByYear(int years) {
		return getYearOFSen() > years;
	}
	public boolean isSenGreaterByMonth(int months) {
		return getMonthOfSen() > months;
	}
	public boolean isBonGreaterThan(BigDecimal bonus) {
		return _bonus.compareTo(bonus) > 0;
	}

	public boolean hasBonus() {
		return hasBonus;
	}

	public BigDecimal getBonus() {
		return _bonus;
	}

	public LocalDate getEmploymentDate() {
		return _employmentDate;
	}
}