package peaksoft.restaurantproject.dto.restaurant;

import peaksoft.restaurantproject.enums.RestType;

public record RestaurantRequest(
        String name,
        String location,
        RestType restType,
        int service) {}
