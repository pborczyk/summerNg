package dmcs.summer.config;

import dmcs.summer.user.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.Collections;

@Component
public class InitialDataLoader implements ApplicationListener<ContextRefreshedEvent> {

    private boolean isAlreadySetup = false;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {

        if (isAlreadySetup) {
            return;
        }

        if(userRepository.findAll().iterator().hasNext()) {
            isAlreadySetup = true;
            return;
        }

        Role userRole = Role.builder()
                .roleName("ROLE_USER")
                .privilege(Privilege.ADD_ENTRY)
                .privilege(Privilege.DELETE_OWN_POSTS)
                .privilege(Privilege.COMMENT)
                .privilege(Privilege.RANK)
                .build();

        Role adminRole = Role.builder()
                .roleName("ROLE_ADMIN")
                .privilege(Privilege.ADD_ENTRY)
                .privilege(Privilege.DELETE_OWN_POSTS)
                .privilege(Privilege.DELETE_ALL_POSTS)
                .privilege(Privilege.COMMENT)
                .privilege(Privilege.RANK)
                .build();

        roleRepository.save(userRole);
        roleRepository.save(adminRole);

        User admin = User.builder()
                .username("admin")
                .password(passwordEncoder.encode("admin"))
                .entries(Collections.emptyList())
                .role(userRole)
                .role(adminRole)
                .build();

        User solBadguy = User.builder()
                .username("sol_badguy")
                .password(passwordEncoder.encode("gunflame"))
                .entries(Collections.emptyList())
                .role(userRole)
                .build();

        userRepository.save(admin);
        userRepository.save(solBadguy);

        isAlreadySetup = true;
    }
}