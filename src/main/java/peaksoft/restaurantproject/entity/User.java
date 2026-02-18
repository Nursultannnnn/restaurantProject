package peaksoft.restaurantproject.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import peaksoft.restaurantproject.enums.Role;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)

public class User {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "user_gen"
    )
    @SequenceGenerator(
            name = "user_gen",
            sequenceName = "user_seq",
            allocationSize = 1)
    Long id;

    String firstName;
    String lastName;
    LocalDate dateOfBirth;
    @Column(unique = true)
    String email;
    String password;
    @Column(unique = true)
    String phoneNumber;
    @Enumerated(EnumType.STRING)
    Role role;
    int experience;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH})
    Restaurant restaurant;

    @OneToMany(mappedBy = "user", cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH})
    List<Cheque> cheques;
}
