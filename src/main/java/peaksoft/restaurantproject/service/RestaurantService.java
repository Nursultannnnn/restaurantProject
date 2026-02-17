package peaksoft.restaurantproject.service;

import peaksoft.restaurantproject.dto.SimpleResponse;
import peaksoft.restaurantproject.dto.restaurant.RestaurantRequest;
import peaksoft.restaurantproject.dto.restaurant.RestaurantResponse;

import java.util.List;

public interface RestaurantService {
    RestaurantResponse saveRestaurant(RestaurantRequest restaurantRequest);
    List<RestaurantResponse> getAllRestaurants();
    RestaurantResponse getRestaurantById(Long id);
    RestaurantResponse updateRestaurant(Long id, RestaurantRequest restaurantRequest);
    SimpleResponse deleteRestaurant(Long id);
}

//package peaksoft.restaurantproject.service;
//
//import peaksoft.restaurantproject.dto.restaurant.RestaurantRequest;
//import peaksoft.restaurantproject.dto.restaurant.RestaurantResponse;
//import peaksoft.restaurantproject.entity.Restaurant;
//
//import java.util.List;
//
//public interface RestaurantService {
//    RestaurantResponse saveRestaurant(RestaurantRequest restaurantRequest);
//    List<Restaurant> getAllRestaurants();
//    Restaurant getRestaurantById(Long id);
//    Restaurant updateRestaurant(Long id, Restaurant restaurant);
//    String deleteRestaurant(Long id);
//}
