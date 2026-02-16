package peaksoft.restaurantproject.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "menu_items")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MenuItem {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "menu_item_gen")
    @SequenceGenerator(name = "menu_item_gen", sequenceName = "menu_item_seq", allocationSize = 1)
    Long id;
    String name;
    String image;
    int price;
    String description;
    boolean isVegetarian;

    // Связь с рестораном: у одного ресторана много блюд
    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH})
    Restaurant restaurant;

    // Связь с категорией: у одной категории (например, "Напитки") много блюд
    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH})
    Category category;
}
