package assignment1.Interfaces;

public interface IAggregable<T extends IAggregable<T, R>, R> {
    R aggregate(R result);
}
