package peaksoft.restaurantproject.dto.subCategory;

import lombok.Builder;

@Builder
public record SubCategoryResponse(
        Long id,
        String name,
        Long categoryId,
        String categoryName
) {
}