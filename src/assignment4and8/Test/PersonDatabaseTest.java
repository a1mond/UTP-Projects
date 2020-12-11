import assignment4and8.Classes.InputParser;
import assignment4and8.Classes.Person;
import assignment4and8.Classes.PersonDatabase;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.text.ParseException;
import java.util.List;
import java.util.stream.Collectors;

public class PersonDatabaseTest {
    private PersonDatabase pd;
    private PersonDatabase pd_s;

    @Before
    public void init() throws FileNotFoundException {
        pd = new PersonDatabase();

        File streamFile = new File("src/assignment4and8/input_serialized");

        DataOutputStream dos = new DataOutputStream(
                new FileOutputStream(streamFile)
        );
        pd.serialize(dos);
        DataInputStream dis = new DataInputStream(
                new FileInputStream(streamFile)
        );
        pd_s = PersonDatabase.deserialize(dis);
    }
    @Test
    public void sortedByFirstName() {
        Assert.assertEquals(List.of("John", "John", "Johnny", "Mark", "Vladyslav"),
                pd.sortedByFirstName().stream().map(Person::get_firstName).collect(Collectors.toList()));
    }
    @Test
    public void sortedBySurnameFirstNameAndBirthdate() {
        Assert.assertEquals(List.of("Smith", "Doe", "Depp", "Brown", "Kotyk"),
                pd.sortedBySurnameFirstNameAndBirthdate().stream().map(Person::get_surname).collect(Collectors.toList()));
    }
    @Test
    public void sortedByBirthdate() {
        Assert.assertEquals(List.of("Johnny", "John", "Mark", "John", "Vladyslav"),
                pd.sortedByBirthdate().stream().map(Person::get_firstName).collect(Collectors.toList()));
    }
    @Test
    public void bornOnDay() throws ParseException {
        Assert.assertEquals(List.of("Vladyslav"),
               pd.bornOnDay(InputParser.DATEFORMAT.parse("2002-08-24")).stream().map(Person::get_firstName).collect(Collectors.toList())
        );
    }
    @Test
    public void serializeDeserialize() {
        Assert.assertEquals(pd.getList().size(), pd_s.getList().size());
        Assert.assertEquals(pd.toString(), pd_s.toString());
        System.out.println(pd_s);
    }
}