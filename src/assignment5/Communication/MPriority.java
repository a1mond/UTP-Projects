package assignment5.Communication;

public enum MPriority {
    HIGH, MIDDLE, LOW;

    public static MPriority random() {
        return MPriority.values()[(int) (Math.random() * 3)];
    }
}
