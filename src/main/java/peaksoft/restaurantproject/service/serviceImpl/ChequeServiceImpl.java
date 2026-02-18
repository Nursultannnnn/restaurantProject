package peaksoft.restaurantproject.service.serviceImpl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import peaksoft.restaurantproject.dto.SimpleResponse;
import peaksoft.restaurantproject.dto.cheque.ChequeRequest;
import peaksoft.restaurantproject.dto.cheque.ChequeResponse;
import peaksoft.restaurantproject.entity.Cheque;
import peaksoft.restaurantproject.entity.MenuItem;
import peaksoft.restaurantproject.entity.Restaurant;
import peaksoft.restaurantproject.entity.User;
import peaksoft.restaurantproject.repository.ChequeRepo;
import peaksoft.restaurantproject.repository.MenuItemRepo;
import peaksoft.restaurantproject.repository.UserRepo;
import peaksoft.restaurantproject.service.ChequeService;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class ChequeServiceImpl implements ChequeService {

    private final ChequeRepo chequeRepo;
    private final UserRepo userRepo;
    private final MenuItemRepo menuItemRepo;

    @Override
    public ChequeResponse saveCheque(Long userId, ChequeRequest request) {
        User waiter = userRepo.findById(userId).orElseThrow(() ->
                new RuntimeException("Официант не найден"));

        Restaurant restaurant = waiter.getRestaurant();
        if (restaurant == null) {
            throw new RuntimeException("Этот сотрудник не привязан ни к какому ресторану!");
        }

        List<MenuItem> items = menuItemRepo.findAllById(request.menuItemIds());
        if (items.isEmpty()) {
            throw new RuntimeException("Блюда не найдены");
        }

        int totalSum = items.stream().mapToInt(MenuItem::getPrice).sum();

        int servicePercent = restaurant.getService();
        int grandTotal = totalSum + (totalSum * servicePercent / 100);

        Cheque cheque = new Cheque();
        cheque.setPriceAverage(grandTotal);
        cheque.setCreatedAt(LocalDate.now());
        cheque.setUser(waiter);
        cheque.setMenuItems(items);

        Cheque savedCheque = chequeRepo.save(cheque);
        return mapToResponse(savedCheque, servicePercent);
    }

    @Override
    public List<ChequeResponse> getAllCheques() {
        return chequeRepo.findAll().stream()
                .map(cheque -> mapToResponse(cheque, cheque.getUser().getRestaurant().getService()))
                .collect(Collectors.toList());
    }

    @Override
    public ChequeResponse getChequeById(Long id) {
        Cheque cheque = chequeRepo.findById(id).orElseThrow(() ->
                new RuntimeException("Чек не найден"));
        return mapToResponse(cheque, cheque.getUser().getRestaurant().getService());
    }

    @Override
    public SimpleResponse deleteCheque(Long id) {
        if (!chequeRepo.existsById(id)) {
            throw new RuntimeException("Чек не найден");
        }
        chequeRepo.deleteById(id);
        return SimpleResponse.builder()
                .status(HttpStatus.OK)
                .message("Чек успешно удален")
                .build();
    }

    @Override
    public int getTotalAmountByUserId(Long userId) {
        if (!userRepo.existsById(userId)) {
            throw new RuntimeException("Сотрудник не найден");
        }

        return chequeRepo.findAllByUserId(userId).stream()
                .mapToInt(Cheque::getPriceAverage)
                .sum();
    }

    private ChequeResponse mapToResponse(Cheque cheque, int servicePercent) {
        return ChequeResponse.builder()
                .id(cheque.getId())
                .waiterName(cheque.getUser().getFirstName() + " " + cheque.getUser().getLastName())
                .items(cheque.getMenuItems().stream().map(MenuItem::getName).collect(Collectors.toList()))
                .priceAverage(cheque.getPriceAverage())
                .serviceFeePercent(servicePercent)
                .createdAt(cheque.getCreatedAt())
                .build();
    }
}