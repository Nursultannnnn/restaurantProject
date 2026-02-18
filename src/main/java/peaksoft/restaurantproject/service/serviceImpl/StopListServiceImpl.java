package peaksoft.restaurantproject.service.serviceImpl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import peaksoft.restaurantproject.dto.SimpleResponse;
import peaksoft.restaurantproject.dto.stopList.StopListRequest;
import peaksoft.restaurantproject.dto.stopList.StopListResponse;
import peaksoft.restaurantproject.entity.MenuItem;
import peaksoft.restaurantproject.entity.StopList;
import peaksoft.restaurantproject.repository.MenuItemRepo;
import peaksoft.restaurantproject.repository.StopListRepo;
import peaksoft.restaurantproject.service.StopListService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class StopListServiceImpl implements StopListService {

    private final StopListRepo stopListRepo;
    private final MenuItemRepo menuItemRepo;

    @Override
    public StopListResponse saveStopList(Long menuItemId, StopListRequest request) {
        MenuItem menuItem = menuItemRepo.findById(menuItemId).orElseThrow(() ->
                new RuntimeException("Блюдо не найдено"));

        if (stopListRepo.existsByMenuItemId(menuItemId)) {
            throw new RuntimeException("Блюдо уже находится в стоп-листе!");
        }

        StopList stopList = new StopList();
        stopList.setReason(request.reason());
        stopList.setDate(request.date());
        stopList.setMenuItem(menuItem);

        return mapToResponse(stopListRepo.save(stopList));
    }

    @Override
    public List<StopListResponse> getAllStopLists() {
        return stopListRepo.findAll().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public StopListResponse getStopListById(Long id) {
        StopList stopList = stopListRepo.findById(id).orElseThrow(() ->
                new RuntimeException("Стоп-лист не найден"));
        return mapToResponse(stopList);
    }

    @Override
    public StopListResponse updateStopList(Long id, StopListRequest request) {
        StopList stopList = stopListRepo.findById(id).orElseThrow(() ->
                new RuntimeException("Стоп-лист не найден"));

        stopList.setReason(request.reason());
        stopList.setDate(request.date());

        return mapToResponse(stopListRepo.save(stopList));
    }

    @Override
    public SimpleResponse deleteStopList(Long id) {
        if (!stopListRepo.existsById(id)) {
            throw new RuntimeException("Стоп-лист не найден");
        }
        stopListRepo.deleteById(id);
        return SimpleResponse.builder()
                .status(HttpStatus.OK)
                .message("Блюдо убрано из стоп-листа")
                .build();
    }

    private StopListResponse mapToResponse(StopList stopList) {
        return StopListResponse.builder()
                .id(stopList.getId())
                .reason(stopList.getReason())
                .date(stopList.getDate())
                .menuItemName(stopList.getMenuItem() != null ? stopList.getMenuItem().getName() : null)
                .build();
    }
}