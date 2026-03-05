package peaksoft.restaurantproject.api;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import peaksoft.restaurantproject.dto.auth.AuthResponse;
import peaksoft.restaurantproject.dto.auth.SignInRequest;
import peaksoft.restaurantproject.dto.auth.SignUpRequest;
import peaksoft.restaurantproject.service.AuthService;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationApi {

    private final AuthService authService;

@PostMapping("/signUp")
    public AuthResponse signUp(@RequestBody SignUpRequest signUpRequest){
return authService.signUp(signUpRequest);
    }
@PostMapping("/signIn")
    public AuthResponse signIn(@RequestBody SignInRequest signInRequest){
    return authService.signIn(signInRequest);
    }
}
