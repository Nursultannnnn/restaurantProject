package peaksoft.restaurantproject.service.serviceImpl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import peaksoft.restaurantproject.repository.UserRepo;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl {
    private final UserRepo userRepo;
}
