package com.example.mapper;

import com.example.entity.Course;
import org.apache.ibatis.annotations.Param;
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

    @Select("select * from course where recommend = '否' and type = #{type} order by id desc limit 8")
    List<Course> selectTop8(String type);
    /**
     * 查询前8个课程置于首页
     */
    @Select("select * from course where recommend = '否' order by id desc limit 8")
    List<Course> selectFresh8();

    /**
     * 推荐课程上限
     */
    @Select("SELECT COUNT(*) FROM course WHERE recommend = '是'")
    int countRecommend();

    /**
     * 取消推荐课程
     */
    @Update("UPDATE course SET recommend = '否' WHERE id = (SELECT id FROM (SELECT id FROM course WHERE recommend = '是' ORDER BY recommendTime LIMIT 1) AS temp)")
    void cancelEarliestRecommend();

    /**
     * 根据栏目名称查询所有课程
     */
    List<Course> selectByChannelName(Course course);

    /**
     * 获取所有独特的栏目名称
     */
    @Select("SELECT DISTINCT channel FROM course WHERE channel IS NOT NULL ORDER BY channel")
    List<String> selectDistinctChannels();
    /**
     * 标签页重新查询
     */
    List<Course> selectByChannel(@Param("channel") String channel);
}