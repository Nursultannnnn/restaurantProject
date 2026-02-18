package peaksoft.restaurantproject.dto.stopList;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

public record StopListRequest(
        @NotBlank(message = "Причина обязательна (например: 'Закончился лосось')")
        String reason,

        @NotNull(message = "Укажите дату стоп-листа")
        LocalDate date
) {
}