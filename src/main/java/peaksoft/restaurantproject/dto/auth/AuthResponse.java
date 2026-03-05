package peaksoft.restaurantproject.dto.auth;

import lombok.Builder;
import peaksoft.restaurantproject.enums.Role;
@Builder
public record AuthResponse(
        String email,
        String token,
        Role role
) {
}
