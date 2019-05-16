package dmcs.summer.user;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public void register(@RequestBody RegisterUserDto registerUserDto) {
        userService.createUser(registerUserDto);
    }

    @GetMapping
    public UserInfoDto getMe() {
        return null;
    }
}
