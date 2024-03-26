package com.example.mapper;

import com.example.entity.Course;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * 操作course相关数据接口
*/
public interface CourseMapper {

    /**
      * 新增
    */
    int insert(Course course);

    /**
      * 删除
    */
    int deleteById(Integer id);

    /**
      * 修改
    */
    int updateById(Course course);

    /**
      * 根据ID查询
    */
    Course selectById(Integer id);

    /**
      * 查询所有
    */
    List<Course> selectAll(Course course);

    /**
     * 查询所有推荐课程
     */
    @Select("select * from course where recommend = '是'")
    List<Course> getRecommend();

    @Select("SELECT COUNT(*) FROM course WHERE recommend = '是'")
    int countRecommend();

    @Update("UPDATE course SET recommend = '否' WHERE id = (SELECT id FROM (SELECT id FROM course WHERE recommend = '是' ORDER BY recommendTime LIMIT 1) AS temp)")
    void cancelEarliestRecommend();


}