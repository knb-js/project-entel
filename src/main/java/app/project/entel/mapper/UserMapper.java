package app.project.entel.mapper;

import app.project.entel.entity.UserEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMapper {

    @Select("select   " +
            "id as idUser,  " +
            "name as email,  " +
            "created_at as dateCreate " +
            "from   " +
            "usuario where id = #{id}")
    List<UserEntity> getAllUser(@Param("id") String id);

    @Select("select  " +
            "id as idUser, " +
            "name as email, " +
            "created_at as dateCreate " +
            "from usuario  " +
            "where name = #{email}")
    List<UserEntity> findByUser(@Param("email") String email);

    @Insert("INSERT INTO usuario (id,name) " +
            "VALUES (#{userEntity.idUser}, #{userEntity.email})")
    Integer addUser(@Param("userEntity") UserEntity userEntity);

}
