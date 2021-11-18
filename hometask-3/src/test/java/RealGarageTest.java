//import org.junit.BeforeAll;
import org.junit.Test;
//import org.junit.TestInstance;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

import static org.junit.Assert.*;

public class RealGarageTest {
    private ArrayList<Owner> testOwners = getTestOwners();
    private final ArrayList<Car> testCars = getTestCars();

    private ArrayList<Car> getTestCars(){
//        Создание тестовых машин
        ArrayList<Car> testCars = new ArrayList<Car>();
        testCars.add(new Car(11, "Lada", "Kalina", 160, 81, 1));
        testCars.add(new Car(12, "Toyota", "Camry", 180, 100, 2));
        testCars.add(new Car(13, "BMW", "X5", 230, 260, 3));
        testCars.add(new Car(14, "Lada", "Xray", 185, 110, 4));
        testCars.add(new Car(15, "Toyota", "Corolla", 200, 180, 4));
        testCars.add(new Car(16, "BMW", "X6", 250, 400, 5));
        testCars.add(new Car(17, "Tesla", "ModelX", 210, 333, 5));
        testCars.add(new Car(18, "Tesla", "ModelX", 250, 762, 6));
        testCars.add(new Car(19, "BMW", "X6", 250, 400, 6));
        testCars.add(new Car(10, "Toyota", "Land Cruiser", 195, 309, 6));
        return testCars;
    }

    private ArrayList<Owner> getTestOwners() {
        //        Создание тестовых владельцев
        ArrayList<Owner> testOwners = new ArrayList<Owner>();
        testOwners.add(new Owner("Oleg", "Li", 20, 1));
        testOwners.add(new Owner("Alex", "Volkov", 30, 2));
        testOwners.add(new Owner("Danil", "Egorov", 27, 3));
        testOwners.add(new Owner("Lev", "Semenov", 50, 4));
        testOwners.add(new Owner("Olga", "Maximova", 35, 5));
        testOwners.add(new Owner("Nurlan", "Saburov", 27, 6));
        return testOwners;
    }

    private RealGarage createGarage(ArrayList<Owner> testOwners, ArrayList<Car> testCars){
        RealGarage testGarage = new RealGarage();
        testGarage.addNewCar(testCars.get(0), testOwners.get(0));
        testGarage.addNewCar(testCars.get(1), testOwners.get(1));
        testGarage.addNewCar(testCars.get(2), testOwners.get(2));
        testGarage.addNewCar(testCars.get(3), testOwners.get(3));
        testGarage.addNewCar(testCars.get(4), testOwners.get(3));
        testGarage.addNewCar(testCars.get(5), testOwners.get(4));
        testGarage.addNewCar(testCars.get(6), testOwners.get(4));
        testGarage.addNewCar(testCars.get(7), testOwners.get(5));
        testGarage.addNewCar(testCars.get(8), testOwners.get(5));
        testGarage.addNewCar(testCars.get(9), testOwners.get(5));
        return testGarage;
    }

    @Test
    public void testMeanCarNumberForEachOwner(){
        RealGarage testGarage = this.createGarage(this.testOwners, this.testCars);
        int actual = testGarage.meanCarNumberForEachOwner();
        int expected = this.testCars.size() / this.testOwners.size();
        assertEquals(expected, actual);

        testGarage.removeCar(10);
        actual = testGarage.meanCarNumberForEachOwner();
        expected = (this.testCars.size() - 1) / this.testOwners.size();
        assertEquals(expected, actual);
    }

    @Test
    public void testMeanOwnersAgeOfCarBrand(){
        RealGarage testGarage = this.createGarage(this.testOwners, this.testCars);
        int actual = testGarage.meanOwnersAgeOfCarBrand("Lada");
        int expected = 35;
        assertEquals(expected, actual);

        actual = testGarage.meanOwnersAgeOfCarBrand("Tesla");
        expected = (35 + 27) / 2;
        assertEquals(expected, actual);

        actual = testGarage.meanOwnersAgeOfCarBrand("BMW");
        expected = (27 + 35 + 27) / 3;
        assertEquals(expected, actual);
    }

    @Test
    public void testCarsWithPowerMoreThan(){
        RealGarage testGarage = this.createGarage(this.testOwners, this.testCars);
        Collection<Car> actual = testGarage.carsWithPowerMoreThan(310);
        HashSet<Car> expected = new HashSet<Car>();
        expected.add(new Car(16, "BMW", "X6", 250, 400, 5));
        expected.add(new Car(17, "Tesla", "ModelX", 210, 333, 5));
        expected.add(new Car(18, "Tesla", "ModelX", 250, 762, 6));
        expected.add(new Car(19, "BMW", "X6", 250, 400, 6));
        System.out.println(actual);
        assert(expected.equals(actual));
    }

    @Test
    public void testTopThreeCarsByVelocity(){
        RealGarage testGarage = this.createGarage(this.testOwners, this.testCars);
        Collection<Car> actual = testGarage.topThreeCarsByMaxVelocity();
        HashSet<Car> expected = new HashSet<Car>();
        expected.add(new Car(18, "Tesla", "ModelX", 250, 762, 6));
        expected.add(new Car(16, "BMW", "X6", 250, 400, 5));
        expected.add(new Car(19, "BMW", "X6", 250, 400, 6));
        System.out.println(expected);
        System.out.println(actual);
        assert(expected.equals(actual));
        testGarage.removeCar(18);
        expected.remove(new Car(18, "Tesla", "ModelX", 250, 762, 6));
        expected.add(new Car(13, "BMW", "X5", 230, 260, 3));
        actual = testGarage.topThreeCarsByMaxVelocity();
        assert(expected.equals(actual));
    }

    @Test
    public void testAllCarsOfBrand(){
        RealGarage testGarage = createGarage(this.testOwners, this.testCars);
        Collection<Car> actual = testGarage.allCarsOfBrand("BMW");
        Collection<Car> expected = new HashSet<Car>();
        expected.add(new Car(13, "BMW", "X5", 230, 260, 3));
        expected.add(new Car(16, "BMW", "X6", 250, 400, 5));
        expected.add(new Car(19, "BMW", "X6", 250, 400, 5));
        assert(expected.equals(actual));

        actual = testGarage.allCarsOfBrand("Lada");
        expected.clear();
        expected.add(new Car(11, "Lada", "Kalina", 160, 81, 1));
        expected.add(new Car(14, "Lada", "Xray", 185, 110, 4));
        assert(expected.equals(actual));

        actual = testGarage.allCarsOfBrand("Tesla");
        expected.clear();
        expected.add(new Car(17, "Tesla", "ModelX", 210, 333, 5));
        expected.add(new Car(18, "Tesla", "ModelX", 250, 762, 6));
        assert(expected.equals(actual));
    }

    @Test
    public void testAllCarsOfOwnerAndRemove() {
        RealGarage testGarage = new RealGarage();
        testGarage.addNewCar(this.testCars.get(0), this.testOwners.get(0));
        testGarage.addNewCar(this.testCars.get(1), this.testOwners.get(1));
        testGarage.addNewCar(this.testCars.get(2), this.testOwners.get(2));
        Collection<Car> actual = testGarage.allCarsOfOwner(this.testOwners.get(0));
        HashSet<Car> expected = new HashSet<Car>();
        expected.add(testCars.get(0));
        assert(expected.equals(actual));

        testGarage.addNewCar(testCars.get(7), testOwners.get(5));
        testGarage.addNewCar(testCars.get(8), testOwners.get(5));
        actual = testGarage.allCarsOfOwner(this.testOwners.get(5));
        expected.clear();
        expected.add(this.testCars.get(7));
        expected.add(this.testCars.get(8));
        assert(expected.equals(actual));

        testGarage.addNewCar(this.testCars.get(9), this.testOwners.get(5));
        testGarage.removeCar(testCars.get(8).getCarId());
        actual = testGarage.allCarsOfOwner(this.testOwners.get(5));
        expected.clear();
        expected.add(this.testCars.get(7));
        expected.add(this.testCars.get(9));
        assert(expected.equals(actual));


    }

    @Test
    public void testAllCarsUniqueOwnersAndAdd(){
        RealGarage testGarage = this.createGarage(this.testOwners, this.testCars);
        Collection<Owner> actual = testGarage.allCarsUniqueOwners();
        HashSet<Owner> expected = new HashSet<Owner>(testOwners);
        assert(expected.equals(actual));

        testGarage.removeCar(this.testCars.get(0).getCarId());
        expected.remove(testOwners.get(0));
        actual = testGarage.allCarsUniqueOwners();
        assert(expected.equals(actual));
    }
}