package peaksoft.restaurantproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import peaksoft.restaurantproject.entity.MenuItem;

public interface MenuItemRepo extends JpaRepository<MenuItem,Long> {
}
