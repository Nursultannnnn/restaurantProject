package peaksoft.restaurantproject.dto.menuItem;

import lombok.Builder;

@Builder
public record MenuItemResponse(
        Long id,
        String name,
        String image,
        int price,
        String description,
        boolean isVegetarian,
        Long restaurantId,
        String subCategoryName
) {
}