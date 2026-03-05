package peaksoft.restaurantproject.service;

import org.springframework.web.bind.annotation.RequestBody;
import peaksoft.restaurantproject.dto.auth.AuthResponse;
import peaksoft.restaurantproject.dto.auth.SignInRequest;
import peaksoft.restaurantproject.dto.auth.SignUpRequest;

public interface AuthService {
    AuthResponse signUp(SignUpRequest signUpRequest);
    AuthResponse signIn(SignInRequest signInRequest);
}
