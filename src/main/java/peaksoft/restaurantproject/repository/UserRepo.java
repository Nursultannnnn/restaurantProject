package peaksoft.restaurantproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import peaksoft.restaurantproject.entity.User;

public interface UserRepo extends JpaRepository<User,Long> {
}
