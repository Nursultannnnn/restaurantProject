package peaksoft.restaurantproject.dto.menuItem;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record MenuItemRequest(
        @NotBlank(message = "Название блюда обязательно")
        String name,

        String image,

        @Min(value = 0, message = "Цена не может быть отрицательной")
        int price,

        @NotBlank(message = "Описание обязательно")
        String description,

        @NotNull(message = "Укажите, вегетарианское ли это блюдо (true/false)")
        Boolean isVegetarian
) {
}