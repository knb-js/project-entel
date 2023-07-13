package app.project.entel.service.impl;

import app.project.entel.dto.UserDTO;
import app.project.entel.entity.UserEntity;
import app.project.entel.mapper.UserMapper;
import app.project.entel.service.UserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@Log4j2
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;


    @Override
    public boolean addUserMapping(UserDTO userDTO) {

            try{
                UserEntity user = new UserEntity();
                user.setEmail(userDTO.getEmail());
                String id = UUID.randomUUID().toString().toUpperCase().substring(0,12);
                user.setIdUser(id);
                userMapper.addUser(user);
                log.info("Se ingresa el usuario correctamente ✔️");
                return true;
            }catch (Exception ex){
                log.error("ERROR , no se ha podido ingresar el usuario ❌ ", ex);
                return false;
            }
    }

    @Override
    public List<UserEntity> getAllUser(String id) {

        try {
            List<UserEntity> user = userMapper.getAllUser(id);
            user.stream().forEach(u->{
                Date now = new Date();
                SimpleDateFormat ff = new SimpleDateFormat("yyyy-MM-dd");
                String fechaFormateada = ff.format(now);
                u.setDateCreate(fechaFormateada);
                u.getDateCreate();
            });
            log.info("Usuario obtenido con éxito! ✔️ ");
            return user;
        }catch (Exception ex){
            log.error("ERROR, No se obtuvo el usuario. ❌", ex);
            return null;
        }

    }

    @Override
    public List<UserEntity> findByUser(String email) {

        try {
            List<UserEntity> user2 = userMapper.findByUser(email);
            user2.stream().forEach(u2->{
                Date now = new Date();
                SimpleDateFormat ff = new SimpleDateFormat("yyyy-MM-dd");
                String fechaFormateada = ff.format(now);
                u2.setDateCreate(fechaFormateada);
                u2.getDateCreate();
            });
            log.info("Usuario obtenido con éxito! ✔️");
            return user2;

        }catch (Exception ex){
            log.error("ERROR, No se obtuvo el usuario. ❌", ex);
            return null;
        }

    }

}
