package assignment4and8.Classes;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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

	public void serialize(DataOutputStream stream) {
		try {
			stream.writeUTF(_firstName);
			stream.writeUTF(_surname);
			stream.writeUTF(new SimpleDateFormat("yyyy-MM-dd").format(_birthdate));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static Person deserialize(DataInputStream stream) {
		try {
			return new Person(
					stream.readUTF(),
					stream.readUTF(),
					new SimpleDateFormat("yyyy-MM-dd").parse(stream.readUTF())
			);
		} catch (ParseException | IOException e) {
			e.printStackTrace();
			return null;
		}
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

	@Override
	public String toString() {
		return _firstName + " " + _surname + " " + new SimpleDateFormat("yyyy-MM-dd").format(_birthdate);
	}
}