package peaksoft.restaurantproject.service.serviceImpl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import peaksoft.restaurantproject.dto.SimpleResponse;
import peaksoft.restaurantproject.dto.user.UserRequest;
import peaksoft.restaurantproject.dto.user.UserResponse;
import peaksoft.restaurantproject.entity.Restaurant;
import peaksoft.restaurantproject.entity.User;
import peaksoft.restaurantproject.enums.Role;
import peaksoft.restaurantproject.repository.RestaurantRepo;
import peaksoft.restaurantproject.repository.UserRepo;
import peaksoft.restaurantproject.service.UserService;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;
    private final RestaurantRepo restaurantRepo;

    @Override
    public SimpleResponse registration(UserRequest request) {

        validateUser(request);
        User user = mapToEntity(request);
        mapToResponse(userRepo.save(user));

        return new SimpleResponse("заявка успешно отправлена ",HttpStatus.OK);
    }

    @Override
    public SimpleResponse assignUserToRestaurant(Long userId, Long restaurantId) {
        Restaurant restaurant = restaurantRepo.findById(restaurantId).orElseThrow(() ->
                new RuntimeException("Ресторан не найден"));

        // Проверка лимита (15 сотрудников)
        if (restaurant.getUsers() != null && restaurant.getUsers().size() >= 15) {
            throw new RuntimeException("В ресторане нет вакансий (максимум 15 человек)");
        }

        User user = userRepo.findById(userId).orElseThrow(() ->
                new RuntimeException("Пользователь не найден"));

        // Принимаем на работу
        user.setRestaurant(restaurant);
        userRepo.save(user);

        return SimpleResponse.builder()
                .status(HttpStatus.OK)
                .message("Сотрудник " + user.getFirstName() + " успешно принят в ресторан " + restaurant.getName())
                .build();
    }

    @Override
    public SimpleResponse rejectUser(Long userId) {
        if (!userRepo.existsById(userId)) {
            throw new RuntimeException("Пользователь не найден");
        }
        userRepo.deleteById(userId);
        return SimpleResponse.builder()
                .status(HttpStatus.OK)
                .message("Заявка отклонена, пользователь удален")
                .build();
    }

    @Override
    public UserResponse createEmployeeByAdmin(Long restaurantId, UserRequest request) {
        Restaurant restaurant = restaurantRepo.findById(restaurantId).orElseThrow(() ->
                new RuntimeException("Ресторан не найден"));

        // Проверка лимита (15 сотрудников)
        if (restaurant.getUsers() != null && restaurant.getUsers().size() >= 15) {
            throw new RuntimeException("В ресторане нет вакансий (максимум 15 человек)");
        }

        validateUser(request); // Проверяем возраст, стаж и email

        User user = mapToEntity(request);
        user.setRestaurant(restaurant); // Сразу привязываем к ресторану

        return mapToResponse(userRepo.save(user));
    }

    @Override
    public List<UserResponse> getAll() {
        return userRepo.findAll().stream()
                .map(this::mapToResponse)
                .collect(Collectors
                        .toList());
    }

    @Override
    public UserResponse getById(Long id) {
        User user = userRepo.findById(id).orElseThrow(() ->
                new RuntimeException("Пользователь не найден"));
        return mapToResponse(user);
    }

    @Override
    public UserResponse update(Long id, UserRequest request) {
        User user = userRepo.findById(id).orElseThrow(() ->
                new RuntimeException("Пользователь не найден"));

        // Если email меняется, проверяем, не занят ли новый
        if (!user.getEmail().equals(request.email()) && userRepo.existsByEmail(request.email())) {
            throw new RuntimeException("Email " + request.email() + " уже занят");
        }
        // ДОБАВЛЯЕМ ПРОВЕРКУ ДЛЯ НОМЕРА ПРИ ОБНОВЛЕНИИ
        if (!user.getPhoneNumber().equals(request.phoneNumber()) && userRepo.existsByPhoneNumber(request.phoneNumber())) {
            throw new RuntimeException("Номер телефона " + request.phoneNumber() + " уже зарегистрирован");
        }

        user.setFirstName(request.firstName());
        user.setLastName(request.lastName());
        user.setDateOfBirth(request.dateOfBirth());
        user.setEmail(request.email());
        user.setPassword(request.password());
        user.setPhoneNumber(request.phoneNumber());
        user.setRole(request.role());
        user.setExperience(request.experience());

        return mapToResponse(userRepo.save(user));
    }

    @Override
    public SimpleResponse delete(Long id) {
        if (!userRepo.existsById(id)) {
            throw new RuntimeException("Пользователь не найден");
        }
        userRepo.deleteById(id);
        return SimpleResponse.builder()
                .status(HttpStatus.OK)
                .message("Пользователь удален")
                .build();
    }

    // ==========================================
    // Вспомогательные методы (чтобы код был чистым)
    // ==========================================

    private void validateUser(UserRequest request) {
        // Проверка уникальности email
        if (userRepo.existsByEmail(request.email())) {
            throw new RuntimeException("Email " + request.email() + " уже занят");
        }

        // ДОБАВЛЯЕМ ПРОВЕРКУ НОМЕРА ТЕЛЕФОНА
        if (userRepo.existsByPhoneNumber(request.phoneNumber())) {
            throw new RuntimeException("Номер телефона " + request.phoneNumber() + " уже зарегистрирован");
        }

        // Вычисляем возраст
        int age = Period.between(request.dateOfBirth(), LocalDate.now()).getYears();

        if (request.role() == Role.CHEF) {
            if (age < 25 || age > 45) {
                throw new RuntimeException("Повар должен быть в возрасте от 25 до 45 лет");
            }
            if (request.experience() < 2) {
                throw new RuntimeException("Стаж работы повара должен быть не менее 2 лет");
            }
        } else if (request.role() == Role.WAITER) {
            if (age < 18 || age > 30) {
                throw new RuntimeException("Официант должен быть в возрасте от 18 до 30 лет");
            }
            if (request.experience() < 1) {
                throw new RuntimeException("Стаж работы официанта должен быть не менее 1 года");
            }
        }
    }

    private User mapToEntity(UserRequest request) {
        User user = new User();
        user.setFirstName(request.firstName());
        user.setLastName(request.lastName());
        user.setDateOfBirth(request.dateOfBirth());
        user.setEmail(request.email());
        user.setPassword(request.password());
        user.setPhoneNumber(request.phoneNumber());
        user.setRole(request.role());
        user.setExperience(request.experience());
        return user;
    }

    private UserResponse mapToResponse(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .dateOfBirth(user.getDateOfBirth())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .role(user.getRole())
                .experience(user.getExperience())
                .build();
    }
}