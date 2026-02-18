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

    @PostMapping("/register")
    public SimpleResponse register(@RequestBody @Valid UserRequest request) {
        return userService.registration(request);
    }

    @PostMapping("/{userId}/assign/{restaurantId}")
    public SimpleResponse assignUser(@PathVariable Long userId, @PathVariable Long restaurantId) {
        return userService.assignUserToRestaurant(userId, restaurantId);
    }

    @DeleteMapping("/{userId}/reject")
    public SimpleResponse rejectUser(@PathVariable Long userId) {
        return userService.rejectUser(userId);
    }

    @PostMapping("/restaurant/{restaurantId}")
    public UserResponse createEmployee(@PathVariable Long restaurantId, @RequestBody @Valid UserRequest request) {
        return userService.createEmployeeByAdmin(restaurantId, request);
    }

    @GetMapping("/requests")
    public List<UserResponse> getAllRequests() {
        return userService.getAllRequests();
    }

    @GetMapping("/restaurant/{restaurantId}/employees")
    public List<UserResponse> getAllEmployees(@PathVariable Long restaurantId) {
        return userService.getAllEmployees(restaurantId);
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