package app.project.entel.service.impl;

import app.project.entel.dto.UserDTO;
import app.project.entel.entity.UserEntity;
import app.project.entel.mapper.UserMapper;
import app.project.entel.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@Log4j2
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    List<UserEntity>listUser;
    @Override
    public boolean addUserMapping(UserDTO userDTO) {

            try{
                UserEntity user = new UserEntity();
                user.setEmail(userDTO.getEmail());
                if(listUser==null) listUser = new ArrayList<>();
                String id = UUID.randomUUID().toString().toUpperCase().substring(0,12);
                user.setId(id);
                listUser.add(user);
                userMapper.addUser(user);
                log.info("Se ingresa el usuario correctamente ✔️");
                return true;
            }catch (Exception ex){
                log.error("ERROR , no se ha podido ingresar el usuario ❌", ex);
                return false;
            }
    }

    @Override
    public List<UserEntity> getAllUser(String id) {
        return userMapper.getAllUser(id);
    }
}
