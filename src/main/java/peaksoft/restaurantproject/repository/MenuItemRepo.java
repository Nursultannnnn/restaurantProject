package peaksoft.restaurantproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import peaksoft.restaurantproject.entity.MenuItem;
import java.util.List;

public interface MenuItemRepo extends JpaRepository<MenuItem, Long> {


    List<MenuItem> findAllByNameContainingIgnoreCase(String name);
}