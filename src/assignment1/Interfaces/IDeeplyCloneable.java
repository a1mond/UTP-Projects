package assignment1.Interfaces;

public interface IDeeplyCloneable<T extends IDeeplyCloneable<T>> {
    T deepClone();
}
