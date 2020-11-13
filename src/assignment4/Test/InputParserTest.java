package assignment4.Test;

import assignment4.Classes.InputParser;
import assignment4.Classes.Person;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.text.ParseException;
import java.util.List;
import java.util.stream.Collectors;

import static assignment4.Classes.InputParser.DATEFORMAT;

public final class InputParserTest {
    List<Person> list;
    List<String> testList;
    @Before
    public void init() throws ParseException {
        list = InputParser.parse(new File("src/assignment4/input.data"));
        testList = List.of("Smith", "Brown", "Doe", "Depp", "Kotyk");
    }
    @Test
    public void parse() {
        Assert.assertEquals(list.stream().map(Person::get_surname).collect(Collectors.toList()),
                testList);
    }
}