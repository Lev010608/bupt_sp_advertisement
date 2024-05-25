package com.example.mapper;

import com.example.entity.Lesson;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * 操作lesson相关数据接口
 */
public interface LessonMapper {

    /**
     * 新增
     */
    int insert(Lesson lesson);
    /**
     * 插入课件与班级的关联
     */
    void insertLessonClass(@Param("lessonId") Integer lessonId, @Param("classId") Integer classId);

    /**
     * 删除
     */
    int deleteById(Integer id);

    /**
     * 修改
     */
    int updateById(Lesson lesson);

    /**
     * 根据ID查询
     */
    Lesson selectById(Integer id);

    /**
     * 根据班级ID查询课件
     */
    List<Lesson> selectByClassId(@Param("classId") Integer classId);

    /**
     * 查询所有
     */
    List<Lesson> selectAll(Lesson lesson);

    /**
     * 根据课件类型查询
     */
    List<Lesson> selectByType(@Param("type") String type);



    /**
     * 删除课件的所有班级关联
     */
    void deleteLessonClassByLessonId(@Param("lessonId") Integer lessonId);
    
}