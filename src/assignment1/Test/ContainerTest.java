package assignment1.Test;

import assignment1.Classes.Book;
import assignment1.Classes.Container;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.LinkedList;

public class ContainerTest {
    private Book book1,
                 book2;
    private final LinkedList<Book> list = new LinkedList<>();

    private final Container<Book, Integer> container = new Container<>(list);

    @Before
    public void before() {
        book1 = new Book(1996);
        book2 = new Book(2005);

        this.container.add(book1);
        this.container.add(book2);

        this.list.add(book1);
        this.list.add(book2);
    }

    @Test
    public void cloneElementAtIndex() {
        Book cloned1 = this.container.cloneElementAtIndex(0);

        Assert.assertEquals(this.container.elements().get(0).getDateOfIssue(), cloned1.getDateOfIssue());
        //System.out.println(this.container.elements().get(0) + " ||| " + cloned1);
        Assert.assertNotSame(this.container.elements().get(0), cloned1);
    }

    @Test
    public void elements() {
        LinkedList<Book> testlist = new LinkedList<>();
        testlist.add(book1);
        testlist.add(book2);

        Assert.assertEquals(testlist.get(0).getDateOfIssue(), this.container.elements().get(0).getDateOfIssue());
        //System.out.println(testlist.get(0).getDateOfIssue() + "   " + this.container.elements().get(0).getDateOfIssue());
        Assert.assertEquals(testlist.get(1).getDateOfIssue(), this.container.elements().get(1).getDateOfIssue());
        //System.out.println(testlist.get(1).getDateOfIssue() + "   " + this.container.elements().get(1).getDateOfIssue());
    }
}
