import assignment4.Classes.InputParser;
import assignment4.Classes.Person;
import assignment4.Classes.PersonDatabase;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;
import java.util.List;
import java.util.stream.Collectors;

public class PersonDatabaseTest {
    private PersonDatabase pd;

    @Before
    public void init() {
        pd = new PersonDatabase();
    }
    @Test
    public void sortedByFirstName() {
        Assert.assertEquals(pd.sortedByFirstName().stream().map(Person::get_firstName).collect(Collectors.toList()),
                List.of("John", "John", "Johnny", "Mark", "Vladyslav"));
    }
    @Test
    public void sortedBySurnameFirstNameAndBirthdate() {
        Assert.assertEquals(pd.sortedBySurnameFirstNameAndBirthdate().stream().map(Person::get_surname).collect(Collectors.toList()),
                List.of("Smith", "Doe", "Depp", "Brown", "Kotyk"));
    }
    @Test
    public void sortedByBirthdate() {
        Assert.assertEquals(pd.sortedByBirthdate().stream().map(Person::get_firstName).collect(Collectors.toList()),
                List.of("Johnny", "John", "Mark", "John", "Vladyslav"));
    }
    @Test
    public void bornOnDay() throws ParseException {
        Assert.assertEquals(pd.bornOnDay(InputParser.DATEFORMAT.parse("2002-08-24")).stream().map(Person::get_firstName).collect(Collectors.toList()),
                List.of("Vladyslav"));
    }
}