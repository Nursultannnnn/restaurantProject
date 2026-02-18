package peaksoft.restaurantproject.service.serviceImpl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import peaksoft.restaurantproject.dto.SimpleResponse;
import peaksoft.restaurantproject.dto.restaurant.RestaurantRequest;
import peaksoft.restaurantproject.dto.restaurant.RestaurantResponse;
import peaksoft.restaurantproject.entity.Restaurant;
import peaksoft.restaurantproject.repository.RestaurantRepo;
import peaksoft.restaurantproject.service.RestaurantService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class RestaurantServiceImpl implements RestaurantService {

    private final RestaurantRepo restaurantRepo;

    @Override
    public RestaurantResponse saveRestaurant(RestaurantRequest restaurantRequest) {
        Restaurant restaurant = new Restaurant();
        restaurant.setName(restaurantRequest.name());
        restaurant.setLocation(restaurantRequest.location());
        restaurant.setRestType(restaurantRequest.restType());
        restaurant.setService(restaurantRequest.service());
        restaurant.setNumberOfEmployees(0);

        Restaurant savedRestaurant = restaurantRepo.save(restaurant);
        return mapToResponse(savedRestaurant);
    }

    @Override
    public List<RestaurantResponse> getAllRestaurants() {
        return restaurantRepo.findAll().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public RestaurantResponse getRestaurantById(Long id) {
        Restaurant restaurant = restaurantRepo.findById(id).orElseThrow(() ->
                new RuntimeException("Restaurant with id: " + id + " not found"));
        return mapToResponse(restaurant);
    }

    @Override
    public RestaurantResponse updateRestaurant(Long id, RestaurantRequest restaurantRequest) {
        Restaurant restaurant = restaurantRepo.findById(id).orElseThrow(() ->
                new RuntimeException("Restaurant with id: " + id + " not found"));

        restaurant.setName(restaurantRequest.name());
        restaurant.setLocation(restaurantRequest.location());
        restaurant.setRestType(restaurantRequest.restType());
        restaurant.setService(restaurantRequest.service());

        return mapToResponse(restaurantRepo.save(restaurant));
    }

    @Override
    public SimpleResponse deleteRestaurant(Long id) {
        if (!restaurantRepo.existsById(id)) {
            return SimpleResponse.builder()
                    .status(HttpStatus.NOT_FOUND)
                    .message("Restaurant with id: " + id + " not found")
                    .build();
        }
        restaurantRepo.deleteById(id);
        return SimpleResponse.builder()
                .status(HttpStatus.OK)
                .message("Restaurant with id: " + id + " successfully deleted")
                .build();
    }

    private RestaurantResponse mapToResponse(Restaurant restaurant) {
        return RestaurantResponse.builder()
                .id(restaurant.getId())
                .name(restaurant.getName())
                .location(restaurant.getLocation())
                .restType(restaurant.getRestType())
                .numberOfEmployees(restaurant.getNumberOfEmployees())
                .service(restaurant.getService())
                .build();
    }
    @Override
    public Integer getCurrentEmployees(Long restaurantId) {
        Restaurant restaurant = restaurantRepo.findById(restaurantId)
                .orElseThrow(() -> new RuntimeException("Restaurant with id: " + restaurantId + " not found"));

        return restaurant.getUsers() != null
                ? restaurant.getUsers().size()
                : 0;
    }
}

