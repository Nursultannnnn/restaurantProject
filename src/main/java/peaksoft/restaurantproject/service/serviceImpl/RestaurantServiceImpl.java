package peaksoft.restaurantproject.service.serviceImpl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import peaksoft.restaurantproject.dto.restaurant.RestaurantRequest;
import peaksoft.restaurantproject.dto.restaurant.RestaurantResponse;
import peaksoft.restaurantproject.entity.Restaurant;
import peaksoft.restaurantproject.repository.RestaurantRepo;
import peaksoft.restaurantproject.service.RestaurantService;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class RestaurantServiceImpl implements RestaurantService {
    private final RestaurantRepo restaurantRepo;

    @Override
    public RestaurantResponse saveRestaurant(RestaurantRequest restaurantRequest) {
        // 1. Создаем объект Entity из данных Request
        Restaurant restaurant = new Restaurant();
        restaurant.setName(restaurantRequest.name());
        restaurant.setLocation(restaurantRequest.location());
        restaurant.setRestType(restaurantRequest.restType());
        restaurant.setService(restaurantRequest.service());

        // 2. Сохраняем Entity в базу
        Restaurant savedRestaurant = restaurantRepo.save(restaurant);

        // 3. Превращаем сохраненную Entity обратно в Response (DTO), чтобы вернуть клиенту
        return new RestaurantResponse(
                savedRestaurant.getId(),
                savedRestaurant.getName(),
                savedRestaurant.getLocation(),
                savedRestaurant.getRestType(),
                savedRestaurant.getNumberOfEmployees(),
                savedRestaurant.getService()
        );
    }

    @Override
    public List<Restaurant> getAllRestaurants() {
        return restaurantRepo.findAll(); // Верни все найденные рестораны
    }

    @Override
    public Restaurant getRestaurantById(Long id) {
        return restaurantRepo.findById(id).orElseThrow(() ->
                new RuntimeException("Restaurant with id: " + id + " not found"));
    }

    @Override
    public Restaurant updateRestaurant(Long id, Restaurant restaurant) {
        return null;
    }

    @Override
    public String deleteRestaurant(Long id) {
        return "";
    }
}
