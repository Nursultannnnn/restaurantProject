package peaksoft.restaurantproject.service.serviceImpl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import peaksoft.restaurantproject.dto.SimpleResponse;
import peaksoft.restaurantproject.dto.menuItem.MenuItemRequest;
import peaksoft.restaurantproject.dto.menuItem.MenuItemResponse;
import peaksoft.restaurantproject.entity.MenuItem;
import peaksoft.restaurantproject.entity.Restaurant;
import peaksoft.restaurantproject.entity.SubCategory;
import peaksoft.restaurantproject.repository.MenuItemRepo;
import peaksoft.restaurantproject.repository.RestaurantRepo;
import peaksoft.restaurantproject.repository.SubCategoryRepo;
import peaksoft.restaurantproject.service.MenuItemService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class MenuItemServiceImpl implements MenuItemService {

    private final MenuItemRepo menuItemRepo;
    private final RestaurantRepo restaurantRepo;
    private final SubCategoryRepo subCategoryRepo;

    @Override
    public MenuItemResponse saveMenuItem(Long restaurantId, Long subCategoryId, MenuItemRequest request) {
        Restaurant restaurant = restaurantRepo.findById(restaurantId).orElseThrow(() ->
                new RuntimeException("Ресторан не найден"));
        SubCategory subCategory = subCategoryRepo.findById(subCategoryId).orElseThrow(() ->
                new RuntimeException("Подкатегория не найдена"));

        MenuItem menuItem = new MenuItem();
        menuItem.setName(request.name());
        menuItem.setImage(request.image());
        menuItem.setPrice(request.price());
        menuItem.setDescription(request.description());
        menuItem.setVegetarian(request.isVegetarian());

        menuItem.setRestaurant(restaurant);
        menuItem.setSubCategory(subCategory);

        return mapToResponse(menuItemRepo.save(menuItem));
    }

    @Override
    public List<MenuItemResponse> getAllMenuItems() {
        return menuItemRepo.findAll().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public MenuItemResponse getMenuItemById(Long id) {
        MenuItem menuItem = menuItemRepo.findById(id).orElseThrow(() ->
                new RuntimeException("Блюдо не найдено"));
        return mapToResponse(menuItem);
    }

    @Override
    public MenuItemResponse updateMenuItem(Long id, MenuItemRequest request) {
        MenuItem menuItem = menuItemRepo.findById(id).orElseThrow(() ->
                new RuntimeException("Блюдо не найдено"));

        menuItem.setName(request.name());
        menuItem.setImage(request.image());
        menuItem.setPrice(request.price());
        menuItem.setDescription(request.description());
        menuItem.setVegetarian(request.isVegetarian());

        return mapToResponse(menuItemRepo.save(menuItem));
    }

    @Override
    public SimpleResponse deleteMenuItem(Long id) {
        if (!menuItemRepo.existsById(id)) {
            throw new RuntimeException("Блюдо не найдено");
        }
        menuItemRepo.deleteById(id);
        return SimpleResponse.builder()
                .status(HttpStatus.OK)
                .message("Блюдо успешно удалено")
                .build();
    }

    @Override
    public List<MenuItemResponse> searchByName(String name) {
        return menuItemRepo.findAllByNameContainingIgnoreCase(name).stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    private MenuItemResponse mapToResponse(MenuItem menuItem) {
        return MenuItemResponse.builder()
                .id(menuItem.getId())
                .name(menuItem.getName())
                .image(menuItem.getImage())
                .price(menuItem.getPrice())
                .description(menuItem.getDescription())
                .isVegetarian(menuItem.isVegetarian())
                .restaurantId(menuItem.getRestaurant() != null ? menuItem.getRestaurant().getId() : null)
                .subCategoryName(menuItem.getSubCategory() != null ? menuItem.getSubCategory().getName() : null)
                .build();
    }
}