package peaksoft.restaurantproject.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import peaksoft.restaurantproject.enums.RestType;

import java.util.List;

@Entity
@Table(name = "restaurants")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)

public class Restaurant {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "restaurant_gen"
    )
    @SequenceGenerator(
            name = "restaurant_gen",
            sequenceName = "restaurant_seq",
            allocationSize = 1)
    Long id;
    String name;
    String location;
    @Enumerated(EnumType.STRING)
    RestType restType;
    int numberOfEmployees;
    int service;

    // В класс Restaurant.java
    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL)
    List<User> users;

    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL)
    List<MenuItem> menuItems;


}
