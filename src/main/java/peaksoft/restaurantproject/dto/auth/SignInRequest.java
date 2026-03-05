package peaksoft.restaurantproject.dto.auth;

public record SignInRequest(
        String email,
        String password) {
}
