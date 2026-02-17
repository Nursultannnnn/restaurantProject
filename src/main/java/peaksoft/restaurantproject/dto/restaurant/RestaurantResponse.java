package peaksoft.restaurantproject.dto.restaurant;
import lombok.Builder;
import peaksoft.restaurantproject.enums.RestType;
@Builder
public record RestaurantResponse(
        Long id,
        String name,
        String location,
        RestType restType,
        int numberOfEmployees,
        int service)
{
}
