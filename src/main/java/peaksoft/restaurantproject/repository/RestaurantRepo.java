package peaksoft.restaurantproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import peaksoft.restaurantproject.entity.Restaurant;

public interface RestaurantRepo extends JpaRepository<Restaurant,Long> {
}
