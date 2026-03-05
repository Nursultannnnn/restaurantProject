package peaksoft.restaurantproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import peaksoft.restaurantproject.entity.StopList;

import java.time.LocalDate;

public interface StopListRepo extends JpaRepository<StopList, Long> {

    boolean existsByMenuItemIdAndDate(Long menuItemId, LocalDate date);}