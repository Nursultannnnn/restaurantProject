package peaksoft.restaurantproject.service;

import peaksoft.restaurantproject.dto.restaurant.RestaurantRequest;
import peaksoft.restaurantproject.dto.restaurant.RestaurantResponse;
import peaksoft.restaurantproject.entity.Restaurant;

import java.util.List;

public interface RestaurantService {
    RestaurantResponse saveRestaurant(RestaurantRequest restaurantRequest);
    List<Restaurant> getAllRestaurants();
    Restaurant getRestaurantById(Long id);
    Restaurant updateRestaurant(Long id, Restaurant restaurant);
    String deleteRestaurant(Long id);
}
