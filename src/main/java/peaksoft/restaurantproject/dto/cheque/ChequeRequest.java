package peaksoft.restaurantproject.dto.cheque;

import jakarta.validation.constraints.NotEmpty;
import java.util.List;

public record ChequeRequest(
        @NotEmpty(message = "В чеке должно быть хотя бы одно блюдо")
        List<Long> menuItemIds
) {
}