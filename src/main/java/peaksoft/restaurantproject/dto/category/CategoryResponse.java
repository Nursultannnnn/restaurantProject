package peaksoft.restaurantproject.dto.category;

import lombok.Builder;

@Builder
public record CategoryResponse(
        Long id,
        String name
) {
}
