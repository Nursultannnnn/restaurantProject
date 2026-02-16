package peaksoft.restaurantproject.service;

import peaksoft.restaurantproject.entity.MenuItem;
import java.util.List;

public interface MenuItemService {
    MenuItem saveMenuItem(Long restaurantId, Long subCategoryId, MenuItem menuItem);
    List<MenuItem> getAllMenuItems();
    MenuItem getMenuItemById(Long id);
    MenuItem updateMenuItem(Long id, MenuItem menuItem);
    String deleteMenuItem(Long id);
    List<MenuItem> searchByName(String name);
}