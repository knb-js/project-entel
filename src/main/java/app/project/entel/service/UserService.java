package app.project.entel.service;

import app.project.entel.dto.UserDTO;
import app.project.entel.entity.UserEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserService {

    boolean addUserMapping(UserDTO userDTO);

    List<UserEntity> getAllUser(@Param("id") String id);

}
