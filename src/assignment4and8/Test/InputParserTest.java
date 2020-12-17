import assignment4and8.Classes.InputParser;
import assignment4and8.Classes.Person;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

public final class InputParserTest {
    private List<Person> list;
    private List<String> testList;
    @Before
    public void init() {
        list = InputParser.parse(new File("src/assignment4and8/input.data"));
        testList = List.of("Smith", "Brown", "Doe", "Depp", "Kotyk");
    }
    @Test
    public void parse() {

        Assert.assertEquals(testList,
                list.stream().map(Person::get_surname).collect(Collectors.toList()));
    }
}