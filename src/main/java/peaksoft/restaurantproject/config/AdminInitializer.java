package peaksoft.restaurantproject.config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import peaksoft.restaurantproject.entity.User;
import peaksoft.restaurantproject.enums.Role;
import peaksoft.restaurantproject.repository.UserRepo;

import java.time.LocalDate;

@Component
@RequiredArgsConstructor
public class AdminInitializer implements CommandLineRunner {

    private final UserRepo userRepo;

    @Override
    public void run(String... args) throws Exception {
        // Проверяем, есть ли уже такой email в базе, чтобы не создавать дубликатов при перезапуске
        if (!userRepo.existsByEmail("admin@gmail.com")) {
            User admin = new User();
            admin.setFirstName("Super");
            admin.setLastName("Admin");
            admin.setDateOfBirth(LocalDate.of(1990, 1, 1));
            admin.setEmail("admin@gmail.com");
            admin.setPassword("admin123"); // В реальных проектах тут будет зашифрованный пароль
            admin.setPhoneNumber("+996777000111"); // Строго по твоему паттерну из валидации
            admin.setRole(Role.ADMIN);
            admin.setExperience(5);

            // Сохраняем админа в БД
            userRepo.save(admin);
            System.out.println("✅ Дефолтный Админ успешно создан! (Email: admin@gmail.com, Пароль: admin123)");
        }
    }
}