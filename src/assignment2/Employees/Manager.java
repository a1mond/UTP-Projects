package assignment2.Employees;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.LinkedList;

public final class Manager extends Worker {

	// attributes
	// * subordinates (a list of immediate subordinates)
	// * all subordinates (derived --- i.e. calculated on the fly --- a list of subordinates in all hierarchy)
	private LinkedList<Employee> _subordinates;
	public Manager(String firstName, String lastName, LocalDate birthDate, BigDecimal salary, Manager manager,
				   LocalDate employmentDate, BigDecimal bonus, LinkedList<Employee> subordinates) {

		super(firstName, lastName, birthDate, salary, manager, employmentDate, bonus);
		_subordinates = subordinates;
	}

	public LinkedList<Employee> getSubordinates() {
		return _subordinates;
	}

	public LinkedList<Employee> getAllSubordinates() {
		LinkedList<Employee> list = new LinkedList<>();
		for (Employee e : _subordinates) {
			if (e.getClass().toString().split(" ")[1].equals("Manager")) {
				list.add(e);
				Manager tmp = (Manager) e;
				tmp.getAllSubordinates();
			} else
				list.add(e);
		}
		return list;
	}
}