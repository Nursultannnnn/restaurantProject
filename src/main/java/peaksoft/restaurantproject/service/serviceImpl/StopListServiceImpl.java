package peaksoft.restaurantproject.service.serviceImpl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import peaksoft.restaurantproject.repository.StopListRepo;

@Service
@Transactional
@RequiredArgsConstructor
public class StopListServiceImpl {
    private final StopListRepo stopListRepo;
}
