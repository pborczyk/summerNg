package dmcs.summer.user;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        dmcs.summer.user.User user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username));

        return User
                .withUsername(user.getUsername())
                .password(user.getPassword())
                .authorities(Collections.emptySet())
                .build();


    }
}
