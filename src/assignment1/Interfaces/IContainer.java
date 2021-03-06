package assignment1.Interfaces;

import java.util.List;

public interface IContainer<T extends IAggregable<T, R> & IDeeplyCloneable<T>, R> {
    List<T> elements();
    R aggregateAllElements();
    T cloneElementAtIndex(int index);
}
