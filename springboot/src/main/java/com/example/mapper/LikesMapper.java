package com.example.mapper;

import com.example.entity.Likes;
import org.apache.ibatis.annotations.*;

public interface LikesMapper {

    @Select("SELECT * FROM likess WHERE information_id = #{informationId} AND user_id = #{userId} AND user_type = #{userType}")
    Likes findLikesByInformationAndUser(@Param("informationId") Integer informationId, @Param("userId") Integer userId, @Param("userType") String userType);

    @Insert("INSERT INTO likess (information_id, user_id, user_type) VALUES (#{informationId}, #{userId}, #{userType})")
    int insert(Likes likes);

    @Delete("DELETE FROM likess WHERE id = #{id}")
    int deleteById(Integer id);
}
