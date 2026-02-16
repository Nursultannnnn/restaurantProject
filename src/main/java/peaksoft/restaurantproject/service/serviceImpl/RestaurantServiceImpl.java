package peaksoft.restaurantproject.service.serviceImpl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
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
    public Restaurant saveRestaurant(Restaurant restaurant) {
        return null;
    }

    @Override
    public List<Restaurant> getAllRestaurants() {
        return List.of();
    }

    @Override
    public Restaurant getRestaurantById(Long id) {
        return null;
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
