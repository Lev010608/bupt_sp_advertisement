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
     * 查询课件关联的班级ID
     */
    @Select("SELECT class_id FROM lesson_class WHERE lesson_id = #{lessonId}")
    List<Integer> selectClassIdsByLessonId(@Param("lessonId") Integer lessonId);




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


    /**
     * 查询校级、院级、班级课程
     */
    @Select({
            "<script>",
            "SELECT lesson.*, college.name AS collegeName, major.name AS majorName",
            "FROM lesson",
            "LEFT JOIN college ON lesson.college_id = college.id",
            "LEFT JOIN major ON lesson.major_id = major.id",
            "LEFT JOIN lesson_class ON lesson.id = lesson_class.lesson_id",
            "WHERE (lesson_class.class_id IS NULL OR lesson_class.class_id != #{classId})",
            "<if test='collegeId != null'>",
            "AND lesson.college_id = #{collegeId}",
            "</if>",
            "<if test='majorId != null'>",
            "AND lesson.major_id = #{majorId}",
            "</if>",
            "ORDER BY lesson.id DESC",
            "</script>"
    })
    List<Lesson> selectLessonsForClass(@Param("classId") Integer classId, @Param("collegeId") Integer collegeId, @Param("majorId") Integer majorId);

    /**
     * 根据学生所属班级查讯课件
     */
    List<Lesson> selectPageByClassId(@Param("classId") Integer classId, @Param("name") String name);


    /**
     * 教师查询课件
     */
    List<Lesson> selectSchoolLevelLessons(@Param("name") String name);

    List<Lesson> selectSchoolLevelLessonsForClass(@Param("classId") Integer classId, @Param("name") String name);

    List<Lesson> selectCollegeLevelLessons(@Param("name") String name);

    List<Lesson> selectCollegeLevelLessonsForClass(@Param("classId") Integer classId, @Param("name") String name);

    List<Lesson> selectMajorLevelLessons(@Param("name") String name);

    List<Lesson> selectMajorLevelLessonsForClass(@Param("classId") Integer classId, @Param("name") String name);



}