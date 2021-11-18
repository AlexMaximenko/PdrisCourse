package sbt;

import java.util.Comparator;

public class CarPowerComparator implements Comparator<Car> {
    @Override
    public int compare(Car o1, Car o2) {
        if (Integer.compare(o1.getPower(), o2.getPower()) != 0)
            return Integer.compare(o1.getPower(), o2.getPower());
        else
            return Integer.compare(o1.getCarId(), o2.getCarId());
    }
}

