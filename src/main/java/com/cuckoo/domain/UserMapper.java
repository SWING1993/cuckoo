package com.cuckoo.domain;

import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {

    @Insert("INSERT INTO user_table (username,password,phone,created,updated) VALUES (#{username},#{password},#{phone},NOW(),NOW())")
    void addUser(User user);

    @Delete("DELETE FROM user_table WHERE id = #{id}")
    void deleteUserById(Integer id);

    @Select("SELECT * FROM user_table")
    List<User> getAllUsers();

    @Select("SELECT * FROM user_table WHERE id = #{id}")
    User getUserById(Integer id);

    @Select("SELECT * FROM user_table WHERE phone = #{phone}")
    User getUserByPhone(String phone);

    @Select("SELECT * FROM user_table WHERE username = #{username}")
    User getUserByUsername(String username);

    @Update("UPDATE user_table SET avatar=#{avatar}, updated = NOW() WHERE id=#{id}")
    void updateAvatarById(@Param("id") Integer id,@Param("avatar") String avatar);

}
