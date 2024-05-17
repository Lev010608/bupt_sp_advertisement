package com.example.mapper;

import com.example.entity.Major;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 操作major相关数据接口
*/
public interface MajorMapper {

    /**
      * 新增
    */
    int insert(Major major);

    /**
      * 删除
    */
    int deleteById(Integer id);

    /**
      * 修改
    */
    int updateById(Major major);

    /**
      * 根据ID查询
    */
    Major selectById(Integer id);

    /**
      * 查询所有
    */
    List<Major> selectAll(Major major);
    /**
     * 根据所属学院查询
     */
    List<Major> selectByCollegeId(@Param("collegeId") Integer collegeId);

}