package peaksoft.restaurantproject.api;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import peaksoft.restaurantproject.dto.SimpleResponse;
import peaksoft.restaurantproject.dto.user.UserRequest;
import peaksoft.restaurantproject.dto.user.UserResponse;
import peaksoft.restaurantproject.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserApi {

    private final UserService userService;

    // 1. Подача заявки (Кандидат регистрируется сам, без ресторана)
    // URL: POST http://localhost:8080/api/users/register
    @PostMapping("/register")
    public SimpleResponse register(@RequestBody @Valid UserRequest request) {
        return userService.registration(request);
    }

    // 2. Принятие на работу (Админ назначает юзера в ресторан)
    // URL: POST http://localhost:8080/api/users/1/assign/1  (где 1 - userId, вторая 1 - restaurantId)
    @PostMapping("/{userId}/assign/{restaurantId}")
    public SimpleResponse assignUser(@PathVariable Long userId, @PathVariable Long restaurantId) {
        return userService.assignUserToRestaurant(userId, restaurantId);
    }

    // 3. Отказ кандидату (Админ удаляет заявку)
    // URL: DELETE http://localhost:8080/api/users/1/reject
    @DeleteMapping("/{userId}/reject")
    public SimpleResponse rejectUser(@PathVariable Long userId) {
        return userService.rejectUser(userId);
    }

    // 4. Прямой найм (Админ сам создает сотрудника и сразу кидает в ресторан)
    // URL: POST http://localhost:8080/api/users/restaurant/1 (где 1 - restaurantId)
    @PostMapping("/restaurant/{restaurantId}")
    public UserResponse createEmployee(@PathVariable Long restaurantId, @RequestBody @Valid UserRequest request) {
        return userService.createEmployeeByAdmin(restaurantId, request);
    }

    // ==========================================
    // Стандартные CRUD операции
    // ==========================================

    @GetMapping
    public List<UserResponse> getAllUsers() {
        return userService.getAll();
    }

    @GetMapping("/{id}")
    public UserResponse getUserById(@PathVariable Long id) {
        return userService.getById(id);
    }

    @PutMapping("/{id}")
    public UserResponse updateUser(@PathVariable Long id, @RequestBody @Valid UserRequest request) {
        return userService.update(id, request);
    }

    @DeleteMapping("/{id}")
    public SimpleResponse deleteUser(@PathVariable Long id) {
        return userService.delete(id);
    }
}