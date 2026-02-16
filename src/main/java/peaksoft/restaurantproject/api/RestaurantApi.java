package peaksoft.restaurantproject.api;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import peaksoft.restaurantproject.entity.Restaurant;
import peaksoft.restaurantproject.service.RestaurantService;

import java.util.List;

@RestController
@RequestMapping("/api/restaurants") // Общий путь для всех методов этого контроллера
@RequiredArgsConstructor
public class RestaurantApi {

    private final RestaurantService restaurantService;

    @PostMapping
    public Restaurant saveRestaurant(@RequestBody Restaurant restaurant) {
        return restaurantService.saveRestaurant(restaurant);
    }

    @GetMapping
    public List<Restaurant> getAllRestaurants() {
        return restaurantService.getAllRestaurants();
    }

    @GetMapping("/{id}")
    public Restaurant getRestaurantById(@PathVariable Long id) {
        return restaurantService.getRestaurantById(id);
    }

    @PutMapping("/{id}")
    public Restaurant updateRestaurant(@PathVariable Long id, @RequestBody Restaurant restaurant) {
        return restaurantService.updateRestaurant(id, restaurant);
    }

    @DeleteMapping("/{id}")
    public String deleteRestaurant(@PathVariable Long id) {
        return restaurantService.deleteRestaurant(id);
    }
}