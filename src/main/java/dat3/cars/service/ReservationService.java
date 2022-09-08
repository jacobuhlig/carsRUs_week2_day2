package dat3.cars.service;

import dat3.cars.entity.Car;
import dat3.cars.entity.Member;
import dat3.cars.entity.Reservation;
import dat3.cars.repository.CarRepository;
import dat3.cars.repository.MemberRepository;
import dat3.cars.repository.ReservationRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;

@Service
public class ReservationService {

    //needed instances
    private ReservationRepository reservationRepository;

    private MemberRepository memberRepository;

    private CarRepository carRepository;



    public void reserveCar(String username, int carId, LocalDate date) {

        Member member = memberRepository.findById(username).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Member not found"));

        Car car = carRepository.findById(carId).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Car not found"));


        if(reservationRepository.existsByCar_IdAndRentalDate(carId, date)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Car already reserved");
        }


        //This reserves the car ALSO if already exists
        Reservation reservation = new Reservation(member, car, date);


        reservationRepository.save(reservation);
    }

}
