package peaksoft.restaurantproject.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Entity
@Table(name = "cheques")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Cheque {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "cheque_gen"
    )
    @SequenceGenerator(
            name = "cheque_gen",
            sequenceName = "cheque_seq",
            allocationSize = 1)
    Long id;
    int priceAverage;
    LocalDate createdAt;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH})
    User user;
// dont understand why dont worked
    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH})
    MenuItem menuItem;
}
