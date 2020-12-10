package assignment4and8.Classes;

import assignment4and8.Comparators.BirthdateComparator;
import assignment4and8.Comparators.FirstNameComparator;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public final class PersonDatabase {

	private List<Person> list;

	public PersonDatabase() {
		this.list = InputParser.parse(new File("src/assignment4and8/input.data"));
	}

	public PersonDatabase(List<Person> list) {
		this.list = list;
	}

	public PersonDatabase(File file) {
		this.list = InputParser.parse(file);
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

	public static PersonDatabase deserialize(DataInputStream stream) {
		try {
			List<Person> list = new LinkedList<>();
			while (stream.available() > 0)
				list.add(Person.deserialize(stream));
			return new PersonDatabase(list);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	public void serialize(DataOutputStream stream) {
		list.forEach(e -> e.serialize(stream));
	}

	public List<Person> getList() {
		return list;
	}

	@Override
	public String toString() {
		return list.toString();
	}
}