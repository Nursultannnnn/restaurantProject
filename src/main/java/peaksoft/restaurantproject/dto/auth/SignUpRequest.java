package peaksoft.restaurantproject.dto.auth;

import peaksoft.restaurantproject.enums.Role;

public record SignUpRequest(
        String firstName,
        String lastName,
        String email,
        String password,
        Role role
) {
}
