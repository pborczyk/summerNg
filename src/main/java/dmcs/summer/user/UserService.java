package dmcs.summer.user;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

     void createUser(RegisterUserDto registerUserDto) {
         User newUser = User.builder()
                 .username(registerUserDto.getUsername())
                 .password(passwordEncoder.encode(registerUserDto.getPassword()))
                 .build();
         userRepository.save(newUser);
    }
}
