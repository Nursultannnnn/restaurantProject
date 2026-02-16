package peaksoft.restaurantproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import peaksoft.restaurantproject.entity.SubCategory;

public interface SubCategoryRepo extends JpaRepository<SubCategory,Long> {
}
