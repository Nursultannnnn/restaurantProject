package peaksoft.restaurantproject.service;

import peaksoft.restaurantproject.dto.SimpleResponse;
import peaksoft.restaurantproject.dto.menuItem.MenuItemRequest;
import peaksoft.restaurantproject.dto.menuItem.MenuItemResponse;

import java.util.List;

public interface MenuItemService {
    MenuItemResponse saveMenuItem(Long restaurantId, Long subCategoryId, MenuItemRequest request);
    List<MenuItemResponse> getAllMenuItems();
    MenuItemResponse getMenuItemById(Long id);
    MenuItemResponse updateMenuItem(Long id, MenuItemRequest request);
    SimpleResponse deleteMenuItem(Long id);
    List<MenuItemResponse> searchByName(String name);
}