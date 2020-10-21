package assignment1.Classes;

import assignment1.Interfaces.IAggregable;
import assignment1.Interfaces.IDeeplyCloneable;

public class Book implements IAggregable<Book, Integer>, IDeeplyCloneable<Book> {
    private Integer dateOfIssue;

    public Book(Integer dateOfIssue) {
        this.dateOfIssue = dateOfIssue;
    }

    public Integer getDateOfIssue() {
        return dateOfIssue;
    }

    @Override
    public Integer aggregate(Integer i) {
        if (i == null)
            return dateOfIssue;
        return dateOfIssue + i;
    }

    @Override
    public Book deepClone() {
        return new Book(dateOfIssue);
    }
}
