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

        this.list.add(book1);
        this.list.add(book2);
    }

    @Test
    public void aggregateAllElements() {
        Integer containerAgg = container.aggregateAllElements();
        Integer basicAgg = book1.aggregate(book2.aggregate(null));
        Assert.assertEquals(containerAgg, basicAgg);
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
        Assert.assertEquals(list.get(0).getDateOfIssue(), this.container.elements().get(0).getDateOfIssue());
        Assert.assertEquals(list.get(1).getDateOfIssue(), this.container.elements().get(1).getDateOfIssue());
        Assert.assertSame(list.get(0), this.container.elements().get(0));
    }
}
