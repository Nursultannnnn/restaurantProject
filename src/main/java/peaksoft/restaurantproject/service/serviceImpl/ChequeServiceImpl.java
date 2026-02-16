package peaksoft.restaurantproject.service.serviceImpl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import peaksoft.restaurantproject.repository.ChequeRepo;

@Service
@Transactional
@RequiredArgsConstructor
public class ChequeServiceImpl {
    private final ChequeRepo chequeRepo;
}

