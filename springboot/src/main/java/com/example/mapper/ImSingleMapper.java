package com.example.mapper;

import com.example.entity.ImSingle;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImSingleMapper {

	@Select("SELECT id, content, fromuser, fromavatar, time, type, touser, toavatar, readed FROM imsingle WHERE (fromuser = #{fromUser} AND touser = #{toUser}) OR (fromuser = #{toUser} AND touser = #{fromUser})")
	List<ImSingle> findByUsername(@Param("fromUser") String fromUser, @Param("toUser") String toUser);

	@Select("SELECT id, content, fromuser, fromavatar, time, type, touser, toavatar, readed FROM imsingle WHERE touser = #{toUser} AND readed = 0")
	List<ImSingle> findByToUsername(@Param("toUser") String toUser);

	@Select("SELECT id, content, fromuser, fromavatar, time, type, touser, toavatar, readed FROM imsingle")
	List<ImSingle> findAll();

	@Insert("INSERT INTO imsingle (content, fromuser, fromavatar, time, type, touser, toavatar, readed) VALUES (#{content}, #{fromuser}, #{fromavatar}, #{time}, #{type}, #{touser}, #{toavatar}, #{readed})")
	@Options(useGeneratedKeys = true, keyProperty = "id")
	void insert(ImSingle imSingle);

	@Update("UPDATE imsingle SET content=#{content}, fromuser=#{fromuser}, fromavatar=#{fromavatar}, time=#{time}, type=#{type}, touser=#{touser}, toavatar=#{toavatar}, readed=#{readed} WHERE id=#{id}")
	void updateByPrimaryKey(ImSingle imSingle);
}
