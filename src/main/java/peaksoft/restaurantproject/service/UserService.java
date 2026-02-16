package peaksoft.restaurantproject.service;

import peaksoft.restaurantproject.entity.User;

import java.util.List;

public interface UserService {
    User saveUser(User user);
    List<User> getAllUsers();
    User getUserById(Long id);
    User updateUser(Long id, User user);
    String deleteUser(Long id);
}
