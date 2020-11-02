package assignment2and3.Employees;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

public final class Manager extends Worker {

	// attributes
	// * subordinates (a list of immediate subordinates)
	// * all subordinates (derived --- i.e. calculated on the fly --- a list of subordinates in all hierarchy)
	private List<Employee> _subordinates;

	public Manager(String firstName, String lastName, LocalDate birthDate, BigDecimal salary, Manager manager,
				   LocalDate employmentDate, BigDecimal bonus, LinkedList<Employee> subordinates) {

		super(firstName, lastName, birthDate, salary, manager, employmentDate, bonus);
		_subordinates = subordinates;
	}

	public void setSubordinates(List<Employee> _subordinates) {
		this._subordinates = _subordinates;
	}

	public List<Employee> getAllSubordinates() {
		LinkedList<Employee> list = new LinkedList<>();
		for (Employee e : _subordinates) {
			if (e.getClass() == Manager.class)
				((Manager) e).getAllSubordinates();
			list.add(e);
		}
		return list;
	}
}