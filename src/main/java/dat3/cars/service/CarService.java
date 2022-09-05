package dat3.cars.service;

import dat3.cars.dto.CarRequest;
import dat3.cars.dto.CarResponse;
import dat3.cars.entity.Car;
import dat3.cars.repository.CarRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class CarService {

    private CarRepository carRepository;

    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }


    public CarResponse addCar(CarRequest carRequest, boolean includeAll) {

        Car newCar = CarRequest.getCarEntity(carRequest);
        newCar = carRepository.save(newCar);

        return new CarResponse(newCar, includeAll);
    }


    public void editCar(CarRequest body, int carId) {
        Car car = carRepository.findById(carId).orElseThrow(
                ()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Car with this id not found"));
        car.setBrand(body.getBrand());
        car.setModel(body.getModel());
        car.setPricePrDay(body.getPricePrDay());
        car.setBestDiscount(body.getBestDiscount());

        carRepository.save(car);
    }


    public List<CarResponse> getCars(boolean includeAll) {
        List<Car> cars = carRepository.findAll();

        ArrayList<CarResponse> response = new ArrayList<>();

        for(Car car : cars) {
            response.add(new CarResponse(car, includeAll));
        }
        return response;
    }


    public CarResponse findCarById(int carId, boolean includeAll) throws Exception {
        Car found = carRepository.findById(carId).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Car not found"));
        return new CarResponse(found, includeAll);
    }


    //could be implemented but shouldn't
    /*public void deleteCarById(int carId) {
        carRepository.deleteById(carId);
    }*/

}
