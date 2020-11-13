package assignment4.Classes;

import java.util.Date;

public class Person implements Comparable<Person> {
	
	private final String _firstName;
	private final String _surname;
	private final Date _birthdate;
	
	public Person(String firstName, String surname, Date birthdate) {
		_firstName = firstName;
		_surname = surname;
		_birthdate = birthdate;
	}

	@Override
	public int compareTo(Person otherPerson) {
		if (_firstName.equals(otherPerson._firstName)) {
			if (_surname.equals(otherPerson._surname))
				return _birthdate.compareTo(otherPerson.get_birthdate());
		} else
			return _firstName.compareTo(otherPerson.get_firstName());
		return 0;
	}

	public String get_firstName() {
		return _firstName;
	}

	public String get_surname() {
		return _surname;
	}

	public Date get_birthdate() {
		return _birthdate;
	}
}