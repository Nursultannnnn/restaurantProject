package peaksoft.restaurantproject.api;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import peaksoft.restaurantproject.dto.SimpleResponse;
import peaksoft.restaurantproject.dto.stopList.StopListRequest;
import peaksoft.restaurantproject.dto.stopList.StopListResponse;
import peaksoft.restaurantproject.service.StopListService;

import java.util.List;

@RestController
@RequestMapping("/api/stop-lists")
@RequiredArgsConstructor
public class StopListApi {

    private final StopListService stopListService;
    @PreAuthorize("hasAnyRole('ADMIN', 'CHEF')")
    @PostMapping("/menu-item/{menuItemId}")
    public StopListResponse saveStopList(@PathVariable Long menuItemId,
                                         @RequestBody @Valid StopListRequest request) {
        return stopListService.saveStopList(menuItemId, request);
    }
    @PreAuthorize("hasAnyRole('ADMIN', 'CHEF', 'WAITER')")
    @GetMapping
    public List<StopListResponse> getAllStopLists() {
        return stopListService.getAllStopLists();
    }
    @PreAuthorize("hasAnyRole('ADMIN', 'CHEF', 'WAITER')")
    @GetMapping("/{id}")
    public StopListResponse getStopListById(@PathVariable Long id) {
        return stopListService.getStopListById(id);
    }
    @PreAuthorize("hasAnyRole('ADMIN', 'CHEF')")
    @PutMapping("/{id}")
    public StopListResponse updateStopList(@PathVariable Long id,
                                           @RequestBody @Valid StopListRequest request) {
        return stopListService.updateStopList(id, request);
    }
    @PreAuthorize("hasAnyRole('ADMIN', 'CHEF')")
    @DeleteMapping("/{id}")
    public SimpleResponse deleteStopList(@PathVariable Long id) {
        return stopListService.deleteStopList(id);
    }
}