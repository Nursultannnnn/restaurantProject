package peaksoft.restaurantproject.service.serviceImpl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import peaksoft.restaurantproject.entity.MenuItem;
import peaksoft.restaurantproject.entity.Restaurant;
import peaksoft.restaurantproject.entity.SubCategory;
import peaksoft.restaurantproject.repository.MenuItemRepo;
import peaksoft.restaurantproject.repository.RestaurantRepo;
import peaksoft.restaurantproject.repository.SubCategoryRepo;
import peaksoft.restaurantproject.service.MenuItemService;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class MenuItemServiceImpl implements MenuItemService {

    private final MenuItemRepo menuItemRepo;
    private final RestaurantRepo restaurantRepo;
    private final SubCategoryRepo subCategoryRepo;

    @Override
    public MenuItem saveMenuItem(Long restaurantId, Long subCategoryId, MenuItem menuItem) {
        Restaurant restaurant = restaurantRepo.findById(restaurantId).orElseThrow();
        SubCategory subCategory = subCategoryRepo.findById(subCategoryId).orElseThrow();

        // Привязываем к обоим родителям
        menuItem.setRestaurant(restaurant);
        menuItem.setSubCategory(subCategory);

        return menuItemRepo.save(menuItem);
    }

    @Override
    public List<MenuItem> getAllMenuItems() {
        return List.of();
    }

    @Override
    public MenuItem getMenuItemById(Long id) {
        return null;
    }

    @Override
    public MenuItem updateMenuItem(Long id, MenuItem menuItem) {
        return null;
    }

    @Override
    public String deleteMenuItem(Long id) {
        return "";
    }

    @Override
    public List<MenuItem> searchByName(String name) {
        return List.of();
    }

    // Метод для поиска (если он есть в интерфейсе)
//    @Override
//    public List<MenuItem> searchByName(String name) {
//        return menuItemRepo.findAllByNameContainingIgnoreCase(name);
//    }
}