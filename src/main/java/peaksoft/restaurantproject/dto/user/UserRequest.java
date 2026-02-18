package peaksoft.restaurantproject.dto.user;

import jakarta.validation.constraints.*;
import peaksoft.restaurantproject.enums.Role;
import java.time.LocalDate;

public record UserRequest(
        @NotBlank(message = "Имя обязательно")
        String firstName,

        @NotBlank(message = "Фамилия обязательна")
        String lastName,

        @NotNull(message = "Дата рождения обязательна")
        LocalDate dateOfBirth,

        @Email(message = "Некорректный формат email")
        @NotBlank(message = "Email обязателен")
        String email,

        @Size(min = 4, message = "Пароль должен быть длиннее 4 символов")
        @NotBlank(message = "Пароль обязателен")
        String password,

        @Pattern(regexp = "^\\+996\\d{9}$", message = "Номер должен быть формата +996XXXXXXXXX")
        String phoneNumber,

        @NotNull(message = "Роль обязательна")
        Role role,

        @Min(value = 0, message = "Опыт не может быть отрицательным")
        int experience
) {
}