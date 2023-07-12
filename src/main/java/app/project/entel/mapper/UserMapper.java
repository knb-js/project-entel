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
            "id,  " +
            "name,  " +
            "created_at  " +
            "from   " +
            "usuario where id = 'AB267309-3C4'")
    List<UserEntity> getAllUser(@Param("id") String id);

    @Insert("INSERT INTO usuario (id,name) " +
            "VALUES (#{userEntity.id}, #{userEntity.email})")
    Integer addUser(@Param("userEntity") UserEntity userEntity);

}
