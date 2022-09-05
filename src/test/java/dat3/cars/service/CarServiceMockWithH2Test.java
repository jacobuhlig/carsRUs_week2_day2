package dat3.cars.service;

import dat3.cars.dto.CarRequest;
import dat3.cars.dto.CarResponse;
import dat3.cars.entity.Car;
import dat3.cars.repository.CarRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
public class CarServiceMockWithH2Test {

    public CarService carService;

    public static CarRepository carRepository;


    static int c1id;

    static int c2id;


/*tstatic int car1;

    static int car2;

    //Tests
    @BeforeAll
    public static void setupData(@Autowired CarRepository carRepository) {
        //Creation of objects
        Car c1 = new Car("Volvo", "PC60", 200, 10);
        Car c2 = new Car("Volvo", "P1900", 250, 15);

        //Saving objects to local database (H2)
        carRepository.save(c1);
        carRepository.save(c2);

        car1 = c1.getId();
        car2 = c2.getId();
    }

    @Test
    public void testFindById() {

        Car found1 = carRepository.findById(car1).get();
        Car found2 = carRepository.findById(car2).get();

        assertEquals(car1, found1.getId());
        assertEquals(car2, found2.getId());

        assertEquals("PC60", found1.getModel());
        assertEquals("P1900", found2.getModel());
    }t*/


    @BeforeAll
    public static void setupData(@Autowired CarRepository car_Repository) {
        carRepository = car_Repository;
        carRepository.deleteAll();

        //Creation of objects
        Car c1 = new Car("Volvo", "PC60", 200, 10);
        Car c2 = new Car("Volvo", "P1900", 250, 15);

        c1 = carRepository.save(c1);
        c2 = carRepository.save(c2);

        c1id = c1.getId();
        c2id = c2.getId();

        carRepository = car_Repository;
    }


    @BeforeEach
    public void initCarService() {
        carService = new CarService(carRepository);
    }


    @Test
    void addCar() {
        CarRequest request = new CarRequest("someBrand", "someModel", 170, 20);
        CarResponse response = carService.addCar(request, true);
        assertTrue(response.getId() > 0);
    }

    @Test
    void editCar() {
        Car carEntityToEdit = carRepository.findById(c1id).get();
        LocalDateTime lastEdited = carEntityToEdit.getEdited();
        carEntityToEdit.setPricePrDay(400);
        carEntityToEdit.setBestDiscount(40);
        CarRequest request = new CarRequest(carEntityToEdit);
        carService.editCar(request, c1id);

        //Verifying changes
        Car edited = carRepository.findById(c1id).get();
        assertEquals(400, edited.getPricePrDay());
        assertEquals(40, edited.getBestDiscount());
        assertTrue(edited.getEdited().isBefore(LocalDateTime.now()));
    }


    /*@Test
    void getCars() {
        List<CarResponse> cars = carService.getCars(false);
        assertEquals(2, cars.size());
        //assertNull(cars.get(0).getBestDiscount()); (what's the idea of this one)
    }*/

}
