package peaksoft.restaurantproject.service.serviceImpl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import peaksoft.restaurantproject.config.jwt.JwtService;
import peaksoft.restaurantproject.dto.auth.AuthResponse;
import peaksoft.restaurantproject.dto.auth.SignInRequest;
import peaksoft.restaurantproject.dto.auth.SignUpRequest;
import peaksoft.restaurantproject.entity.User;
import peaksoft.restaurantproject.repository.UserRepo;
import peaksoft.restaurantproject.service.AuthService;


@Service
@Transactional
@RequiredArgsConstructor
public class AuthServiceImpl  implements AuthService {

private final UserRepo  userRepo;
private final PasswordEncoder passwordEncoder;
private final JwtService  jwtService;


    @Override
    public AuthResponse signUp(SignUpRequest signUpRequest) {
       if( userRepo.getUserByEmail(signUpRequest.email()).isPresent() ) {
           throw new RuntimeException("Email already exists");
       }
        User user = User.builder()
                .firstName(signUpRequest.firstName())
                .lastName(signUpRequest.lastName())
                .email(signUpRequest.email())
                .password(passwordEncoder.encode(signUpRequest.password()))
                .role(signUpRequest.role())
               .build();
       userRepo.save(user);
       String token = jwtService.generateToken(user);

        return AuthResponse.builder()
                .email(user.getEmail())
                .token(token)
                .role(user.getRole())
                .build();
    }

    @Override
    public AuthResponse signIn(SignInRequest signInRequest) {
        User user = userRepo.getUserByEmail(signInRequest.email()).orElseThrow(
                () -> new RuntimeException("Email not found")
        );
        if (!passwordEncoder.matches(signInRequest.password(), user.getPassword())) {
throw new BadCredentialsException("Incorrect password or email");
        }
       String token =  jwtService.generateToken(user);

        return AuthResponse.builder()
                .email(user.getEmail())
                .role(user.getRole())
                .token(token)
                .build();
    }
}
