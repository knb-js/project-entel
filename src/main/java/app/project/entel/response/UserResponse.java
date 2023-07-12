package app.project.entel.response;

import app.project.entel.dto.UserDTO;
import app.project.entel.entity.UserEntity;
import lombok.Data;

import java.util.List;

@Data
public class UserResponse {

    private List<UserEntity> users;
}
