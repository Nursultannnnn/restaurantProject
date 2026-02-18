package peaksoft.restaurantproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import peaksoft.restaurantproject.entity.Cheque;
import java.util.List;

public interface ChequeRepo extends JpaRepository<Cheque, Long> {
    List<Cheque> findAllByUserId(Long userId);
}