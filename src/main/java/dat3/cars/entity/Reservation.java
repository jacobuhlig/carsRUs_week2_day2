package dat3.cars.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

//Lombok
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
// -------------
@Entity
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @ManyToOne
    Member member;

    @ManyToOne
    Car car;

    @CreationTimestamp
    LocalDateTime reservationDate;

    LocalDate rentalDate;


    public Reservation(Member member, Car car, LocalDate rentalDate) {
        this.member = member;
        this.car = car;
        this.rentalDate = rentalDate;
    }
}
