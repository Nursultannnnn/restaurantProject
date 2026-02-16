package peaksoft.restaurantproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import peaksoft.restaurantproject.entity.StopList;

public interface StopListRepo extends JpaRepository<StopList,Long> {
}
