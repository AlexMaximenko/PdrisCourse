package sbt;

import org.junit.Test;
import sbt.Car;
import sbt.CarVelocityComparator;

public class CarVelocityComparatorTest {

    @Test
    public void testCompare() {
        CarVelocityComparator comparator = new CarVelocityComparator();

        Car car1 = new Car(1, "Lada", "Kalina", 100, 100, 10);
        Car car2 = new Car(2, "BMW", "X5", 200, 200, 10);
        Integer actual = comparator.compare(car1, car2);
        assert(actual < 0);

        car1 = new Car(1, "Lada", "Kalina", 200, 100, 10);
        car2 = new Car(2, "BMW", "X5", 200, 200, 10);
        actual = comparator.compare(car1, car2);
        assert(actual < 0);

        car1 = new Car(1, "Lada", "Kalina", 300, 100, 10);
        car2 = new Car(2, "BMW", "X5", 200, 200, 10);
        actual = comparator.compare(car1, car2);
        assert(actual > 0);
    }
}
