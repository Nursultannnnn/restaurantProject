package peaksoft.restaurantproject.service;

import peaksoft.restaurantproject.dto.SimpleResponse;
import peaksoft.restaurantproject.dto.user.UserRequest;
import peaksoft.restaurantproject.dto.user.UserResponse;
import java.util.List;

public interface UserService {
    SimpleResponse registration(UserRequest request);
    SimpleResponse assignUserToRestaurant(Long userId, Long restaurantId);
    SimpleResponse rejectUser(Long userId);
    UserResponse createEmployeeByAdmin(Long restaurantId, UserRequest request);
    List<UserResponse> getAll();
    UserResponse getById(Long id);
    UserResponse update(Long id, UserRequest request);
    SimpleResponse delete(Long id);
}
