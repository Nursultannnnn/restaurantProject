package peaksoft.restaurantproject.dto.stopList;

import lombok.Builder;
import java.time.LocalDate;

@Builder
public record StopListResponse(
        Long id,
        String reason,
        LocalDate date,
        String menuItemName
) {
}