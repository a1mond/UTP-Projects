package assignment1.Classes;

import assignment1.Interfaces.IAggregable;
import assignment1.Interfaces.IContainer;
import assignment1.Interfaces.IDeeplyCloneable;

import java.util.LinkedList;
import java.util.List;

public class Container<T extends IAggregable<T, R> & IDeeplyCloneable<T>, R> implements IContainer<T, R> {
    private final LinkedList<T> elements;

    public Container(LinkedList<T> t) {
        this.elements = t;
    }

    public void add(T t) {
        this.elements.add(t);
    }

    @Override
    public List<T> elements() {
        return this.elements;
    }

    @Override
    public R aggregateAll() {
        R aggregate = null;
        for (T t : this.elements)
            aggregate = t.aggregate(aggregate);
        return aggregate;
    }

    @Override
    public T cloneElementAtIndex(int index) throws IndexOutOfBoundsException {
        if (index > this.elements.size() || index < 0)
            throw new IndexOutOfBoundsException();
        return this.elements.get(index).deepClone();
    }
}
