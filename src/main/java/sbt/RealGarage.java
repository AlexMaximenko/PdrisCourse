package sbt;

import java.util.*;

public class RealGarage implements Garage{
    private HashMap<String, HashSet<Car>> carsByBrand;
    private HashMap<Owner, HashSet<Car>> carsByOwner;
    private TreeSet<Car> carsByPower;
    private TreeSet<Car> carsByVelocity;
    private HashMap<Integer, Car> carById;
    private HashMap<Integer, Owner> ownerById;

    public RealGarage() {
        carsByBrand = new HashMap<String, HashSet<Car>>();
        carsByOwner = new HashMap<Owner, HashSet<Car>>();
        carsByPower = new TreeSet<Car>(new CarPowerComparator());
        carsByVelocity = new TreeSet<Car>(new CarVelocityComparator());
        carById = new HashMap<Integer, Car>();
        ownerById = new HashMap<Integer, Owner>();
    }


    @Override
    public Collection<Owner> allCarsUniqueOwners() {
        return carsByOwner.keySet();
    }

    @Override
    public Collection<Car> topThreeCarsByMaxVelocity() {
        HashSet<Car> fastestCars = new HashSet<Car>();
        Iterator<Car> iterator = carsByVelocity.descendingIterator();
        for (int i = 0; i < 3; i++) {
            fastestCars.add(iterator.next());
        }
        return fastestCars;
    }

    @Override
    public Collection<Car> allCarsOfBrand(String brand) {
        return carsByBrand.get(brand);
    }

    @Override
    public Collection<Car> carsWithPowerMoreThan(int power) {
        return carsByPower.tailSet(new Car(-1, "def", "def", 1, power, -1));
    }

    @Override
    public Collection<Car> allCarsOfOwner(Owner owner) {
        return carsByOwner.get(owner);
    }

    @Override
    public int meanOwnersAgeOfCarBrand(String brand) {
        Collection<Car> cars = this.allCarsOfBrand(brand);
        HashSet<Owner> uniqueOwners = new HashSet<Owner>();

        for (Car car : cars) {
            uniqueOwners.add(ownerById.get(car.getOwnerId()));
        }

        double result = 0;
        for (Owner uniqueOwner : uniqueOwners) {
            result += uniqueOwner.getAge();
        }
        return (int) result / uniqueOwners.size();
    }

    @Override
    public int meanCarNumberForEachOwner() {
        return carById.size() / ownerById.size();
    }

    @Override
    public Car removeCar(int carId) {
        if (!this.carById.containsKey(carId)) {
            throw new IllegalArgumentException("В гараже нет такой машины");
        }

        Car removingCar = carById.get(carId);

        if (this.allCarsOfOwner(ownerById.get(removingCar.getOwnerId())).size() == 1) {
            carsByOwner.remove(ownerById.get(removingCar.getOwnerId()));
            ownerById.remove(removingCar.getOwnerId());
        }
        else
            carsByOwner.get(new Owner("def", "def", 0, removingCar.getOwnerId())).remove(removingCar);

        carById.remove(carId);
        carsByBrand.get(removingCar.getBrand()).remove(removingCar);
        carsByVelocity.remove(removingCar);
        carsByPower.remove(removingCar);

        return removingCar;
    }

    @Override
    public void addNewCar(Car car, Owner owner) {
        if (this.carById.containsKey(car.getCarId()))
            throw new IllegalArgumentException("Данная машина уже добавлена");

        carById.put(car.getCarId(), car);
        if (!this.carsByBrand.containsKey(car.getBrand()))
            carsByBrand.put(car.getBrand(), new HashSet<Car>());
        carsByBrand.get(car.getBrand()).add(car);

        this.carsByOwner.computeIfAbsent(owner, k -> new HashSet<Car>());
        carsByOwner.get(owner).add(car);

        carsByVelocity.add(car);
        carsByPower.add(car);

        if (!ownerById.containsKey(owner.getOwnerId()))
            ownerById.put(owner.getOwnerId(), owner);

    }
}
