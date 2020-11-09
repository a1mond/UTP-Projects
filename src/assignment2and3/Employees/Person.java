package assignment2and3.Employees;

import java.time.LocalDate;

public abstract class Person {

	// To implement an attribute means that you provide a backing field and
	// getter or optionally setter for read-write properties/attributes
	// 
	// NO BACKING FIELDS SHOULD BE PROVIDED FOR DERIVED ATTRIBUTES
	// THOSE SHOULD BE COMPUTED ON-LINE
	//
	// attributes:
	// * first name (read-only)
	// * surname (read-only)
	// * birth date (read-only) --- date MUST BE represented by an instance of
	// the type designed for date representation (either Date or LocalDate)
	//
	// * age (derived --- computed based on birth date) --- implemented as a
	// getter calculating the difference between the current date and birth date

	private final String _firstName, // backing field
						 _lastName;
	private final LocalDate _birthDate;
	private short _age;

	public Person(String firstName, String lastName, LocalDate birthDate) {
		_firstName = firstName;
		_lastName = lastName;
		_birthDate = birthDate;
		calcAge();
	}
	public boolean isOlderThan(Person person) {
		return _age > person.getAge();
	}
	public boolean isOlderThan(int age) {
		return _age > age;
	}

	public boolean isYoungerThan(Person person) {
		return _age < person.getAge();
	}

	public boolean compareAge(Person person) {
		return getAge() > person.getAge();
	}
	public String getFirstName() { // getter
		return _firstName;
	}

	public String getLastName() {
		return _lastName;
	}

	public short getAge() {
		calcAge();
		return _age;
	}

	public void calcAge() {
		// TO BE IMPLEMENTED CORRECTLY
		_age = (short)(LocalDate.now().getYear() - _birthDate.getYear());
	}
}