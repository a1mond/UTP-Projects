package assignment2and3.Employees;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Trainee extends Employee {

	// attributes:
	// * apprenticeship start date
	// * apprenticeship length (in days)

	private LocalDate _apprenticeShipStart;
	private int _apprenticeShipLength;
	
	public Trainee(String firstName, String lastName, LocalDate birthDate, BigDecimal salary, Manager manager,
				   LocalDate apprenticeShipStart, int apprenticeShipLength) {

		super(firstName, lastName, birthDate, salary, manager);
		_apprenticeShipStart = apprenticeShipStart;
		_apprenticeShipLength = apprenticeShipLength;
	}
}