package assignment1.Classes;

import assignment1.Interfaces.IAggregable;
import assignment1.Interfaces.IDeeplyCloneable;

public class Car implements IAggregable<Car, String>, IDeeplyCloneable<Car> {
    private String model;

    public Car(String model) {
        this.model = model;
    }

    @Override
    public Car deepClone() {
        return new Car(this.model);
    }

    @Override
    public String aggregate(String result) throws NullPointerException {
        return model.concat(" ").concat(model);
    }
}
