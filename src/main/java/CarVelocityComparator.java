import java.util.Comparator;

public class CarVelocityComparator implements Comparator<Car> {

    @Override
    public int compare(Car o1, Car o2) {
        if (Integer.compare(o1.getMaxVelocity(), o2.getMaxVelocity()) != 0)
            return Integer.compare(o1.getMaxVelocity(), o2.getMaxVelocity());
        else
            return Integer.compare(o1.getCarId(), o2.getCarId());
    }
}
