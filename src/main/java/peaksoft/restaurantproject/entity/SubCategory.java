package peaksoft.restaurantproject.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Entity
@Table(name = "sub_categories")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)

public class SubCategory {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "sub_category_gen")
    @SequenceGenerator(
            name = "sub_category_gen",
            sequenceName = "sub_category_seq",
            allocationSize = 1)
    Long id;
    String name;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH})
    Category category;

    @OneToMany(mappedBy = "subCategory", cascade = CascadeType.ALL)
    List<MenuItem> menuItems;
}