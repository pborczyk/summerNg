package dmcs.summer.user;

import lombok.Data;

@Data
public class UserInfoDto {
    private long id;
    private String username;

    public static UserInfoDto fromUser(User user) {
        UserInfoDto dto = new UserInfoDto();
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        return dto;
    }
}
