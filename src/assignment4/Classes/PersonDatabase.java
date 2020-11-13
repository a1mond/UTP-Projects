package assignment4.Classes;

import assignment4.Comparators.BirthdateComparator;
import assignment4.Comparators.FirstNameComparator;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public final class PersonDatabase {

	List<Person> list;

	public PersonDatabase() {
		this.list = InputParser.parse(new File("src/assignment4/input.data"));
	}

	public List<Person> sortedByFirstName() {
		list.sort(new FirstNameComparator());
		return list;
	}
	
	public List<Person> sortedBySurnameFirstNameAndBirthdate() {
		list.sort(Person::compareTo);
		return list; // natural order (Comparable)
	}
	
	public List<Person> sortedByBirthdate() {
		list.sort(new BirthdateComparator());
		return list; // external rule for ordering (based on Comparator --- BirthdateComparator)
	}
	
	public List<Person> bornOnDay(Date date) {
		Map<Integer, Person> m = new HashMap<>();

		for (int i = 0; i < list.size(); i++)
			m.put(i, list.get(i));

		return m
				.values()
				.stream()
				.filter(e -> e.get_birthdate().equals(date))
				.collect(Collectors.toList());
	}
}