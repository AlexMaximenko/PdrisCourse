package sbt;

import org.junit.Test;
import sbt.Car;
import sbt.CarPowerComparator;

public class CarPowerComparatorTest {

    @Test
    public void testCompare() {
        CarPowerComparator comparator = new CarPowerComparator();

        Car car1 = new Car(1, "Lada", "Kalina", 100, 100, 10);
        Car car2 = new Car(2, "BMW", "X5", 200, 200, 10);
        Integer actual = comparator.compare(car1, car2);
        assert(actual < 0);

        car1 = new Car(1, "Lada", "Kalina", 100, 200, 10);
        car2 = new Car(2, "BMW", "X5", 200, 200, 10);
        actual = comparator.compare(car1, car2);
        assert(actual < 0);

        car1 = new Car(1, "Lada", "Kalina", 100, 300, 10);
        car2 = new Car(2, "BMW", "X5", 200, 200, 10);
        actual = comparator.compare(car1, car2);
        assert(actual > 0);
    }
}
