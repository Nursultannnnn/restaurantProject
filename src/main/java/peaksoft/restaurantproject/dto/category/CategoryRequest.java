package peaksoft.restaurantproject.dto.category;

import jakarta.validation.constraints.NotBlank;

public record CategoryRequest(
        @NotBlank(message = "Название категории не может быть пустым")
        String name
) {
}
