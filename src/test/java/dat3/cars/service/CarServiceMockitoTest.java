package dat3.cars.service;

import dat3.cars.dto.CarRequest;
import dat3.cars.dto.CarResponse;
import dat3.cars.entity.Car;
import dat3.cars.repository.CarRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class CarServiceMockitoTest {
    @Mock
    CarRepository carRepository;

    @Autowired
    CarService carService;

    @BeforeEach
    public void setup() {
        carService = new CarService(carRepository);
    }

    @Test
    void addCar() {
        Car carWithId = new Car("Porsche", "Y47", 300, 13);
        carWithId.setId(1000);
        Mockito.when(carRepository.save(any(Car.class))).thenReturn(carWithId);
        CarRequest request = new CarRequest(carWithId.getBrand(), carWithId.getModel(), carWithId.getPricePrDay(), carWithId.getBestDiscount());
        CarResponse response = carService.addCar(request, false);
        assertEquals(1000, response.getId());
    }
}
