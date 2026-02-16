package peaksoft.restaurantproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import peaksoft.restaurantproject.entity.Cheque;

public interface ChequeRepo extends JpaRepository<Cheque, Long> {
}
