package com.pandatype.auth.mapper;

import com.pandatype.auth.entities.UserEntity;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface UserMapper {
    void insertUserEntity(UserEntity userEntity);

    UserEntity selectUserEntityByEmail(String email);
}