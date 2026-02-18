package peaksoft.restaurantproject.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Entity
@Table(name = "stop_lists")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class StopList {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "stop_list_gen")
    @SequenceGenerator(
            name = "stop_list_gen",
            sequenceName = "stop_list_seq",
            allocationSize = 1)
    Long id;
    String reason;
    LocalDate date;

    @OneToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH})
    MenuItem menuItem;
}
