package peaksoft.restaurantproject.service;

import peaksoft.restaurantproject.entity.Restaurant;

import java.util.List;

public interface RestaurantService {
    Restaurant saveRestaurant(Restaurant restaurant);
    List<Restaurant> getAllRestaurants();
    Restaurant getRestaurantById(Long id);
    Restaurant updateRestaurant(Long id, Restaurant restaurant);
    String deleteRestaurant(Long id);
}
