package dat3.cars.dto;

import dat3.cars.entity.Car;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CarRequest {

    String brand;

    String model;

    double pricePrDay;

    double bestDiscount;


    public static Car getCarEntity(CarRequest c) {
        return Car.builder()
                .brand(c.brand)
                .model(c.model)
                .pricePrDay(c.pricePrDay)
                .bestDiscount(c.bestDiscount)
                .build();
    }


    public CarRequest(Car car) {
        this.brand = car.getBrand();
        this.model = car.getModel();
        this.pricePrDay = car.getPricePrDay();
        this.bestDiscount = car.getBestDiscount();
    }
}
