package assignment4.Comparators;

import java.util.Comparator;

import assignment4.Classes.Person;

public class FirstNameComparator implements Comparator<Person> {

	@Override
	public int compare(Person person1, Person person2) {
		return person1.get_firstName().compareTo(person2.get_firstName());
	}
}