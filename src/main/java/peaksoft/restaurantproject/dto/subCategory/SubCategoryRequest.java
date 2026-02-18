package peaksoft.restaurantproject.dto.subCategory;

import jakarta.validation.constraints.NotBlank;

public record SubCategoryRequest(
        @NotBlank(message = "Название подкатегории не может быть пустым")
        String name
) {
}