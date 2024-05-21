package com.example.mapper;

import com.example.entity.ImSingle;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface ImSingleMapper extends Mapper<ImSingle> {

//	@Select("SELECT * FROM imsingle WHERE (fromuser = #{fromUser} AND touser = #{toUser}) OR (fromuser = #{toUser} AND touser = #{fromUser})")
	List<ImSingle> findByUsername(String fromUser, String toUser);

//	@Select("SELECT * FROM imsingle WHERE touser = #{toUser} AND readed = 0")
	List<ImSingle> findByToUsername(String toUser);
}