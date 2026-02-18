package peaksoft.restaurantproject.api;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import peaksoft.restaurantproject.dto.SimpleResponse;
import peaksoft.restaurantproject.dto.menuItem.MenuItemRequest;
import peaksoft.restaurantproject.dto.menuItem.MenuItemResponse;
import peaksoft.restaurantproject.service.MenuItemService;

import java.util.List;

@RestController
@RequestMapping("/api/menu-items")
@RequiredArgsConstructor
public class MenuItemApi {

    private final MenuItemService menuItemService;

    @PostMapping("/restaurant/{restaurantId}/subcategory/{subCategoryId}")
    public MenuItemResponse saveMenuItem(@PathVariable Long restaurantId,
                                         @PathVariable Long subCategoryId,
                                         @RequestBody @Valid MenuItemRequest request) {
        return menuItemService.saveMenuItem(restaurantId, subCategoryId, request);
    }

    @GetMapping
    public List<MenuItemResponse> getAllMenuItems() {
        return menuItemService.getAllMenuItems();
    }

    @GetMapping("/{id}")
    public MenuItemResponse getMenuItemById(@PathVariable Long id) {
        return menuItemService.getMenuItemById(id);
    }

    @PutMapping("/{id}")
    public MenuItemResponse updateMenuItem(@PathVariable Long id, @RequestBody @Valid MenuItemRequest request) {
        return menuItemService.updateMenuItem(id, request);
    }

    @DeleteMapping("/{id}")
    public SimpleResponse deleteMenuItem(@PathVariable Long id) {
        return menuItemService.deleteMenuItem(id);
    }

    @GetMapping("/search")
    public List<MenuItemResponse> searchByName(@RequestParam String name) {
        return menuItemService.searchByName(name);
    }
}