package com.example.service;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import com.example.common.enums.RecommendEnum;
import com.example.entity.Lesson;
import com.example.mapper.LessonMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;


@Service
public class LessonService {

    @Resource
    private LessonMapper lessonMapper;



    /**
     * 新增
     */
    public void add(Lesson lesson) {
        if (lesson.getCollegeId() == null && lesson.getMajorId() == null) {
            lesson.setSchoolLevelflag(1);
        } else {
            lesson.setSchoolLevelflag(0);
        }
        lessonMapper.insert(lesson);
        if (lesson.getClassIds() != null) {
            for (Integer classId : lesson.getClassIds()) {
                lessonMapper.insertLessonClass(lesson.getId(), classId);
            }
        }
    }

    /**
     * 为课件添加班级ID
     */
    public void addClassToLesson(Integer lessonId, Integer classId) {
        lessonMapper.insertLessonClass(lessonId, classId);
    }

    /**
     * 教师新增课件时关联班级
     */
    public void addLessonInClass(Lesson lesson) {
        lessonMapper.insert(lesson);
        if (lesson.getClassIds() != null) {
            for (Integer classId : lesson.getClassIds()) {
                lessonMapper.insertLessonClass(lesson.getId(), classId);
            }
        }
    }





    /**
     * 删除
     */
    public void deleteById(Integer id) {
        lessonMapper.deleteById(id);
    }

    /**
     * 批量删除
     */
    public void deleteBatch(List<Integer> ids) {
        for (Integer id : ids) {
            lessonMapper.deleteById(id);
        }
    }

    /**
     * 修改
     */
    public void updateById(Lesson lesson) {
        if (lesson.getCollegeId() != null || lesson.getMajorId() != null) {
            lesson.setSchoolLevelflag(0);
        } else {
            lesson.setSchoolLevelflag(1);
        }
        lessonMapper.updateById(lesson);
        lessonMapper.deleteLessonClassByLessonId(lesson.getId());
        if (lesson.getClassIds() != null) {
            for (Integer classId : lesson.getClassIds()) {
                lessonMapper.insertLessonClass(lesson.getId(), classId);
            }
        }
    }

    /**
     * 根据ID查询
     */
    public Lesson selectById(Integer id) {
        return lessonMapper.selectById(id);
    }

    /**
     * 根据班级ID查询课件
     */
    public List<Lesson> selectByClassId(Integer classId) {
        return lessonMapper.selectByClassId(classId);
    }

    /**
     * 查询所有
     */
    public List<Lesson> selectAll(Lesson lesson) {
        return lessonMapper.selectAll(lesson);
    }
    
    /**
     * 分页查询
     */
    public PageInfo<Lesson> selectPage(Lesson lesson, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Lesson> list = lessonMapper.selectAll(lesson);
        return PageInfo.of(list);
    }
    
    /**
     * 根据课件类型分页查询
     */
    public PageInfo<Lesson> selectByType(String type, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Lesson> list = lessonMapper.selectByType(type);
        return PageInfo.of(list);
    }

    /**
     * 查询校级、院级、班级课程
     */
    public List<Lesson> getLessonsForClass(Integer classId, Integer collegeId, Integer majorId) {
        return lessonMapper.selectLessonsForClass(classId, collegeId, majorId);
    }

    /**
     * 根据学生所属班级查讯课件selectPageByClassId
     */
    public PageInfo<Lesson> selectPageByClassId(Integer classId, String name, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Lesson> list = lessonMapper.selectPageByClassId(classId, name);
        return PageInfo.of(list);
    }

    public PageInfo<Lesson> selectSchoolLevelLessons(Integer classId, String name, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Lesson> lessons;
        if (classId != null) {
            lessons = lessonMapper.selectSchoolLevelLessonsForClass(classId, name);
        } else {
            lessons = lessonMapper.selectSchoolLevelLessons(name);
        }
        return PageInfo.of(lessons);
    }

    public PageInfo<Lesson> selectCollegeLevelLessons(Integer classId, String name, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Lesson> lessons;
        if (classId != null) {
            lessons = lessonMapper.selectCollegeLevelLessonsForClass(classId, name);
        } else {
            lessons = lessonMapper.selectCollegeLevelLessons(name);
        }
        return PageInfo.of(lessons);
    }

    public PageInfo<Lesson> selectMajorLevelLessons(Integer classId, String name, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Lesson> lessons;
        if (classId != null) {
            lessons = lessonMapper.selectMajorLevelLessonsForClass(classId, name);
        } else {
            lessons = lessonMapper.selectMajorLevelLessons(name);
        }
        return PageInfo.of(lessons);
    }


}