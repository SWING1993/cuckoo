package com.cuckoo.domain;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMapper {

    @Insert("INSERT INTO users (username,password,phone,created,updated) VALUES (#{username},#{password},#{phone},#{created},#{updated})")
    void addUser(User user);

    @Delete("DELETE FROM users WHERE id = #{id}")
    void deleteUserById(Integer id);

    @Select("SELECT * FROM users")
    List<User> getAllUsers();

    @Select("SELECT * FROM users WHERE id = #{id}")
    User getUserById(Integer id);

    @Select("SELECT * FROM users WHERE phone = #{phone}")
    User getUserByPhone(String phone);

    @Select("SELECT * FROM users WHERE username = #{username}")
    User getUserByUsername(String username);

}
