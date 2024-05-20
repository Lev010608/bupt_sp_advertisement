package com.example.mapper;

import com.example.entity.Classes;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 操作classes相关数据接口
*/
public interface ClassesMapper {

    /**
      * 新增
    */
    int insert(Classes classes);

    /**
      * 删除
    */
    int deleteById(Integer id);

    /**
      * 修改
    */
    int updateById(Classes classes);

    /**
      * 根据ID查询
    */
    Classes selectById(Integer id);

    /**
      * 查询所有
    */
    List<Classes> selectAll(Classes classes);
    /**
     * 根据teacherId查询所有
     */
    List<Classes> selectAllByTeacherId(Integer teacherId);
    /**
     * 根据专业查询
     */
    List<Classes> selectByMajorId(@Param("majorId") Integer majorId);

}