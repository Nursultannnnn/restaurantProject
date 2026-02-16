package peaksoft.restaurantproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import peaksoft.restaurantproject.entity.Category;

public interface CategoryRepo extends JpaRepository<Category,Long> {
}
