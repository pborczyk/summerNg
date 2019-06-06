package dmcs.summer.user;

import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class UserInfoDto {
    private long id;
    private String username;
    private List<String> privileges;

    public static UserInfoDto fromUser(User user) {
        UserInfoDto dto = new UserInfoDto();
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setPrivileges(user.getRoles()
                .stream()
                .flatMap(role -> role.getPrivileges().stream())
                .map(Enum::name)
                .collect(Collectors.toList()));
        return dto;
    }
}
